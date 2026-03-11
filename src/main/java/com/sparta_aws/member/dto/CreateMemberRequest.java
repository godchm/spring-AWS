package com.sparta_aws.member.dto;

import lombok.Getter;

@Getter
public class CreateMemberRequest {


    private String name;
    private String age;
    private String MBTI;
}
