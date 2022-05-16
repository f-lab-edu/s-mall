package com.flabedu.small.small.data;

import lombok.Getter;

import java.util.Date;

@Getter
public class Member {
    int id;
    String userId;
    String password;
    long failCount;
    String memberType;
    Date registrationDate;
    Date modifiedDate;

    public Member(int id, String userId, String password, long failCount, String memberType, Date registrationDate, Date modifiedDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.failCount = failCount;
        this.memberType = memberType;
        this.registrationDate = registrationDate;
        this.modifiedDate = modifiedDate;
    }
}
