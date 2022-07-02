package pers.lee.entity;

public class User {
    private int uId;
    private String uName;
    private String uPw;
    private String uSchool;
    private String uDepartment;

    public User() {
    }

    public User(int uId, String uName, String uPw, String uSchool, String uDepartment) {
        this.uId = uId;
        this.uName = uName;
        this.uPw = uPw;
        this.uSchool = uSchool;
        this.uDepartment = uDepartment;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPw() {
        return uPw;
    }

    public void setuPw(String uPw) {
        this.uPw = uPw;
    }

    public String getuSchool() {
        return uSchool;
    }

    public void setuSchool(String uSchool) {
        this.uSchool = uSchool;
    }

    public String getuDepartment() {
        return uDepartment;
    }

    public void setuDepartment(String uDepartment) {
        this.uDepartment = uDepartment;
    }
}
