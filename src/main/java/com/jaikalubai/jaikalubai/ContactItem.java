package com.jaikalubai.jaikalubai;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("contactitems")
public class ContactItem {

    @Id
    private int serialNumber;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCatagoty() {
        return catagoty;
    }

    public void setCatagoty(String catagoty) {
        this.catagoty = catagoty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String catagoty;
    private String date;


    public ContactItem(int serialNumber, String firstName, String lastName, String mobileNumber, String catagoty, String date) {
        super();
        this.serialNumber = serialNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.catagoty = catagoty;
        this.date = date;
    }



}
