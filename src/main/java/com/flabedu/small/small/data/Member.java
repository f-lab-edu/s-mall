package com.flabedu.small.small.data;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class Member {
    int id;
    String userId;
    String password;
    long failCount;
    String memberType;
    LocalDateTime registrationDate;
    LocalDateTime modifiedDate;
}
