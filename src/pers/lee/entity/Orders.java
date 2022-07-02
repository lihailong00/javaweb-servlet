package pers.lee.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Orders {
    private int oId;
    private int uId;
    private String oTime;
    private StringBuilder oItems;  // 数据库中是varchar类型，因此在dao中需要转换
    private double totalPrice;

    public Orders() {
    }

    public Orders(int oId, int uId, String oTime, StringBuilder oItems, double totalPrice) {
        this.oId = oId;
        this.uId = uId;
        this.oTime = oTime;
        this.oItems = oItems;
        this.totalPrice = totalPrice;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getoTime() {
        return oTime;
    }

    public void setoTime(String oTime) {
        this.oTime = oTime;
    }

    public StringBuilder getoItems() {
        return oItems;
    }

    public void setoItems(StringBuilder oItems) {
        this.oItems = oItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
