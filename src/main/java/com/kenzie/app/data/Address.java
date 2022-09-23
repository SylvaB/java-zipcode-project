package com.kenzie.app.data;

public class Address {
    //declare properties
    private String recipient;
    private String numberStreet;
    private String city;
    private String state;
    private String zipCode;

    //define constructor(s)
    public Address(String recipient,String numberStreet,String city,String state,String zipCode){
        this.recipient = recipient;
        this.numberStreet = numberStreet;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address(){
        this("","","","","");
    }

    //define methods - getter and setter

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getNumberStreet() {
        return numberStreet;
    }

    public void setNumberStreet(String numberStreet) {
        this.numberStreet = numberStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "recipient='" + recipient + '\'' +
                ", numberStreet='" + numberStreet + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
