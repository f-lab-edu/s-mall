package com.flabedu.small.small.dao;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Member {
    private Long id;
    private String userId;
    private String password;
    private long failCount;
    private String memberType;
    private LocalDateTime registrationDate;
    private LocalDateTime modifiedDate;
}
