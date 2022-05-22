package com.flabedu.small.small.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class Item {
    final long itemId;
    final String name;
    final String nameEng;
    final String gender;
    final long price;
    final String registerUserID;
    final LocalDateTime registerDate;
    final String modifiedUserID;
    final LocalDateTime modifiedDate;
}
