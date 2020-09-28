package com.avijit.healthcareforcovid19.model;

/**
 * Created by Avijit Acharjee on 9/28/2020 at 9:49 AM.
 * Email: avijitach@gmail.com.
 */
public class Donor {
    public String id;
    public String name;
    public String bloodGroup;
    public String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
