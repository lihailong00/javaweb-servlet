package pers.lee.entity;

public class Food {
    int fId;
    String fName;
    double fPrice;
    String fType;

    public Food() {
    }

    public Food(int fId, String fName, double fPrice, String fType) {
        this.fId = fId;
        this.fName = fName;
        this.fPrice = fPrice;
        this.fType = fType;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public double getfPrice() {
        return fPrice;
    }

    public void setfPrice(double fPrice) {
        this.fPrice = fPrice;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }
}
