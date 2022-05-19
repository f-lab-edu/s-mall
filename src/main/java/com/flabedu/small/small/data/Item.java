package com.flabedu.small.small.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class Item {
    long itemId;
    String name;
    String nameEng;
    String gender;
    long price;
    String registerUserID;
    LocalDateTime registerDate;
    String modifiedUserID;
    LocalDateTime modifiedDate;

}
