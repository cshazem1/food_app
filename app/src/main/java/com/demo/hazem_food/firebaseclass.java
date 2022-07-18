package com.demo.hazem_food;

public class firebaseclass {
    String idkey;
    String customer_name;
    String customer_phone;
    String customer_Adrees;
    String order_name;
    String cost;
    String number;
    String total;

    public firebaseclass() {
    }

    public firebaseclass(String idkey, String customer_name, String customer_phone, String customer_Adrees, String order_name, String cost, String number, String total) {
        this.idkey = idkey;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_Adrees = customer_Adrees;
        this.order_name = order_name;
        this.cost = cost;
        this.number = number;
        this.total = total;
    }

    public String getIdkey() {
        return idkey;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public String getCustomer_Adrees() {
        return customer_Adrees;
    }

    public String getOrder_name() {
        return order_name;
    }

    public String getCost() {
        return cost;
    }

    public String getNumber() {
        return number;
    }

    public String getTotal() {
        return total;
    }
}
