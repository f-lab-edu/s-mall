package com.flabedu.small.small.data;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class Member {
    int id;
    String userId;
    String password;
    long failCount;
    String memberType;
    LocalDateTime registrationDate;
    LocalDateTime modifiedDate;

    public Member(int id, String userId, String password, long failCount, String memberType, LocalDateTime registrationDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.failCount = failCount;
        this.memberType = memberType;
        this.registrationDate = registrationDate;
        this.modifiedDate = modifiedDate;
    }
}
