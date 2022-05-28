package com.flabedu.small.small.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class Item {
    private final long itemId;
    private final String name;
    private final String nameEng;
    private final String gender;
    private final long price;
    private final String registerUserID;
    private final LocalDateTime registerDate;
    private final String modifiedUserID;
    private final LocalDateTime modifiedDate;
}
