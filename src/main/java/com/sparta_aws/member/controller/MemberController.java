package com.sparta_aws.member.controller;



import com.sparta_aws.member.dto.*;
import com.sparta_aws.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    // 팀원 정보 생성
    @PostMapping("/members")
    public ResponseEntity<?> createMember(@RequestBody CreateMemberRequest request) {

        log.info("[API-LOG] 팀원 생성");
        try {
            CreateMemberResponse result = memberService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (Exception e) {
            log.error("[API-ERROR] 팀원 생성 오류 발생.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    // 팀원 정보 조회
    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable Long memberId) {

        log.info("[API-LOG] 팀원 정보 조회");
        try {
            GetOneMemberResponse result = memberService.getOne(memberId);
            return ResponseEntity.status(HttpStatus.OK).body(result);

        } catch (Exception e) {
            log.error("[API-ERROR] 팀원 조회 오류 발생.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/api/members/{memberId}/profile-image")
    public ResponseEntity<FileUploadResponse> upload(
            @PathVariable Long memberId,
            @RequestParam("file") MultipartFile file) {
        String key = memberService.upload(memberId, file);
        return ResponseEntity.ok(new FileUploadResponse(key));
    }

    @GetMapping("/api/members/{memberId}/profile-image")
    public ResponseEntity<FileDownloadUrlResponse> getDownloadUrl(@PathVariable Long memberId) {
        URL url = memberService.getDownloadUrl(memberId);
        return ResponseEntity.ok(new FileDownloadUrlResponse(url.toString()));
    }
}