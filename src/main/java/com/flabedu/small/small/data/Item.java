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

    public Item(String name, String nameEng, String gender, long price, String registerUserID, Date registerDate, String modifiedUserID, Date modifiedDate) {
        this.name = name;
        this.nameEng = nameEng;
        this.gender = gender;
        this.price = price;
        this.registerUserID = registerUserID;
        this.registerDate = registerDate;
        this.modifiedUserID = modifiedUserID;
        this.modifiedDate = modifiedDate;
    }
}
