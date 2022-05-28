package com.flabedu.small.small.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class Member {
    private final int id;
    private final String userId;
    private final String password;
    private final long failCount;
    private final String memberType;
    private final LocalDateTime registrationDate;
    private final LocalDateTime modifiedDate;
}
