package model;

import java.util.Calendar;

public class Customer {
private String id;
private String name;
private String phone;
private Calendar birth;

    public Customer(String id, String name, String phone, Calendar birth) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return String.format("%d/%d/%d", birth.get(Calendar.DATE), birth.get(Calendar.MONTH) + 1, birth.get(Calendar.YEAR));
    }

    public void setBirth(Calendar birth) {
        this.birth = birth;
    }
    public String toSaveString() {
        return id+","+name+","+phone+","+birth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name= " + name  +
                ", phone= " + phone  +
                ", birth= " + getBirth() +
                '}';
    }
}
