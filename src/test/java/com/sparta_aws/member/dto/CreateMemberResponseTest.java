package com.sparta_aws.member.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class CreateMemberResponseTest {

    @Test
    void response_dto_생성_테스트() {

        // given
        CreateMemberResponse response =
                new CreateMemberResponse("CHOE-HOEHYEONG-MIN", "30", "INFJ");

        // when은 없다..


        // then
        assertThat(response.getName()).isEqualTo("CHOE-HOEHYEONG-MIN");
        assertThat(response.getAge()).isEqualTo("30");
        assertThat(response.getMBTI()).isEqualTo("INFJ");
    }

}