package com.sparta_aws.member.dto;

import lombok.Getter;

@Getter
public class GetOneMemberResponse {
    private final Long id;
    private final String name;
    private final String age;
    private final String MBTI;

    public GetOneMemberResponse(Long id, String name, String age, String mbti) {
        this.id = id;
        this.name = name;
        this.age = age;
        MBTI = mbti;
    }
}
