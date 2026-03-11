package com.sparta_aws.member.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String age;
    private String MBTI;
    private String profileImageKey;


    public Member(String name, String age, String MBTI)
    {
        this.name=name;
        this.age=age;
        this.MBTI=MBTI;
    }
    public void updateProfileImageKey(String profileImageKey) {
        this.profileImageKey = profileImageKey;
    }

}
