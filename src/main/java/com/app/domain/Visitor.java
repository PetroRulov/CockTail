package com.app.domain;

import java.io.Serializable;

/**
 * Created by prulov on 14.10.2016.
 */
public class Visitor implements Serializable {

    private long id_code;
    private String identify;
    private String surName;
    private String name;
    private String telfax;
    private String address;
    private String eMail;


    public Visitor(){}

    public Visitor(long id_code, String surName, String name, String telfax, String address, String email){
        this.id_code = id_code;
        this.surName = surName;
        this.name = name;
        this.telfax = telfax;
        this.eMail = email;
        this.address = address;
    }

    public long getId_code() {
        return id_code;
    }

    public void setId_code(long id_code) {
        this.id_code = id_code;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelfax() {
        return telfax;
    }

    public void setTelfax(String telfax) {
        this.telfax = telfax;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String visitorInfoShow(){
        return getSurName()+", "+getName()+", code #"+getId_code()+", identify number # "+getIdentify();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        if (getId_code() != visitor.getId_code()) return false;
        if (getSurName() != null ? !getSurName().equals(visitor.getSurName()) : visitor.getSurName() != null) return false;
        if (getName() != null ? !getName().equals(visitor.getName()) : visitor.getName() != null) return false;
        if (getTelfax() != null ? !getTelfax().equals(visitor.getTelfax()) : visitor.getTelfax() != null) return false;
        if (getAddress() != null ? !getAddress().equals(visitor.getAddress()) : visitor.getAddress() != null) return false;
        return geteMail() != null ? geteMail().equals(visitor.geteMail()) : visitor.geteMail() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId_code() ^ (getId_code() >>> 32));
        result = 31 * result + (getSurName() != null ? getSurName().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getTelfax() != null ? getTelfax().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (geteMail() != null ? geteMail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id_code=" + id_code +
                ", identify=" + identify +
                ", surName='" + surName + '\'' +
                ", name='" + name + '\'' +
                ", telfax='" + telfax + '\'' +
                ", address='" + address + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
