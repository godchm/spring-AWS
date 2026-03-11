package com.sparta_aws.member.service;


import com.sparta_aws.member.dto.CreateMemberRequest;
import com.sparta_aws.member.dto.CreateMemberResponse;
import com.sparta_aws.member.dto.GetOneMemberResponse;
import com.sparta_aws.member.entity.Member;
import com.sparta_aws.member.repository.MemberRepository;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7);
    private final S3Template s3Template;

    // 멤버 생성
    @Transactional
    public CreateMemberResponse save(CreateMemberRequest request) {

        Member member=new Member(
                request.getName(),
                request.getAge(),
                request.getMBTI()
        );
        Member memberSaved=memberRepository.save(member);

        return new CreateMemberResponse(
                memberSaved.getName(),
                memberSaved.getAge(),
                memberSaved.getMBTI()
        );
    }

    @Transactional(readOnly = true)
    public GetOneMemberResponse getOne(Long memberId) {
        Member member=memberRepository.findById(memberId).orElseThrow(
                ()-> new IllegalArgumentException("등록된 멤버가 없다.")
        );
        return new GetOneMemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMBTI()
        );
    }


    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public String upload(Long memberId, MultipartFile file) {
        try {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("등록된 멤버가 없다."));
            String key = "uploads/"+memberId + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Template.upload(bucket, key, file.getInputStream());


            member.updateProfileImageKey(key);

            return key;

        } catch (IOException e) {
            throw new IllegalArgumentException("파일 업로드 실패", e);
        }
    }

    @Transactional(readOnly = true)
    public URL getDownloadUrl(Long memberId) {

        String key = memberRepository.findById(memberId)
                .orElseThrow()
                .getProfileImageKey();

        return s3Template.createSignedGetURL(bucket, key, PRESIGNED_URL_EXPIRATION);
    }
}
