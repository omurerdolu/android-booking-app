package com.mehmetomurerdolu.booking.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class EstateEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer estateId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "price_period")
    private String pricePeriod;

    @ColumnInfo(name = "photo")
    private String photo;

    @ColumnInfo(name = "number_of_room")
    private Integer numberOfRoom;

    @ColumnInfo(name = "heating_type")
    private String heatingType;

    @ColumnInfo(name = "floor")
    private Integer floor;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "district")
    private String district;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "details")
    private String details;

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(Integer numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
