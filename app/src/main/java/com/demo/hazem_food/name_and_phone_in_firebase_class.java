package com.demo.hazem_food;

public class name_and_phone_in_firebase_class {
    String key;
    String name;
    String phone;
    String  address;



    public name_and_phone_in_firebase_class() {
    }
    public name_and_phone_in_firebase_class(String key, String name, String phone, String address) {
        this.key = key;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getKey() {
        return key;
    }
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


}
