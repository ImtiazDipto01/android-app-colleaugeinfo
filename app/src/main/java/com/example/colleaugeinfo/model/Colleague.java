package com.example.colleaugeinfo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "colleague")
public class Colleague {

    @PrimaryKey
    @NonNull
    private String id ;
    private String name;
    private String designation ;
    private String address ;
    private String phone ;
    private String officeName ;

    public Colleague(String id, String name, String designation, String address, String phone, String officeName) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.address = address;
        this.phone = phone;
        this.officeName = officeName ;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAddress() {
        return address;
    }
    public String getOfficeName() {
        return officeName;
    }
    public String getPhone() {
        return phone;
    }
}
