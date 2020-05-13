package com.example.bkt2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MonAnModel implements Serializable {
    int id;
    String img_thumbnail;
    String tenmon;
    int gia;

    public MonAnModel(int id, String img_thumbnail, String tenmon, int gia) {
        this.id = id;
        this.img_thumbnail = img_thumbnail;
        this.tenmon = tenmon;
        this.gia = gia;
    }

    public MonAnModel() {

    }

    public String getImg_thumbnail() {
        return img_thumbnail;
    }

    public void setImg_thumbnail(String img_thumbnail) {
        this.img_thumbnail = img_thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

}
