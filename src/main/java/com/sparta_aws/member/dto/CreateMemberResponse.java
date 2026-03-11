package com.sparta_aws.member.dto;

import lombok.Getter;

@Getter
public class CreateMemberResponse {
    private final String name;
    private final String age;
    private final String MBTI;

    public CreateMemberResponse(String name, String age, String mbti) {
        this.name = name;
        this.age = age;
        MBTI = mbti;
    }
}
