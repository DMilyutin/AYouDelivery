package ru.youdelivery.Helperss;

public class Custors {
    public String number;
    public String address;
    public String phone;

    public Custors(String number, String address, String phone) {
        this.number = number;
        this.address = address;
        this.phone = phone;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
