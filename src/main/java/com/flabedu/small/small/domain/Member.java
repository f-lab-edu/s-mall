package com.flabedu.small.small.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class Member {
    final int id;
    final String userId;
    final String password;
    final long failCount;
    final String memberType;
    final LocalDateTime registrationDate;
    final LocalDateTime modifiedDate;
}
