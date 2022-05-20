package com.flabedu.small.small.data;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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
