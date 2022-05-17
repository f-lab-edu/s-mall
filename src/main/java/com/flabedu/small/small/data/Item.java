package com.flabedu.small.small.data;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Item {
    long itemId;
    String name;
    String nameEng;
    String gender;
    long price;
    String registerUserID;
    Date registerDate;
    String modifiedUserID;
    Date modifiedDate;

}
