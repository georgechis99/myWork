package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        ITelephone georgesPhone;
        georgesPhone = new DeskPhone(123456);
        georgesPhone.powerOn();
        georgesPhone.callPhone(123456);
        georgesPhone.answer();

        georgesPhone = new MobilePhone(24565);
        georgesPhone.powerOn();
        georgesPhone.callPhone(24565);
        georgesPhone.answer();
    }
}
