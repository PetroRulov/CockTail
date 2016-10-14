package com.app.domain;

import java.io.Serializable;

/**
 * Created by prulov on 14.10.2016.
 */
public class Client implements Serializable {

    private long id_client;
    private String surName;
    private String name;
    private String dateOfBirth;
    private String sex;
    private String eMail;

    public Client(){}

    public Client(long id_client, String surName, String name, String dateOfBirth, String sex, String eMail){

        this.id_client = id_client;
        this.surName = surName;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.eMail = eMail;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String clientInfoShow(){

        return  getName()+" "+getSurName()+", ID # "+getId_client()+", e-mail: "+geteMail();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id = " + id_client + ", surName " + surName +
                ", name " + name + ", date of birth " + dateOfBirth + ", sex " + sex + ", e-mail: " + eMail + "}";
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Client){
            Client guest = (Client) obj;
            return (id_client > 0 && id_client == guest.getId_client() && surName != null && surName.equals(guest.getSurName()) &&
                    name.equals(guest.getName()) && dateOfBirth.equals(guest.getDateOfBirth()) &&
                    sex.equals(guest.getSex()) && eMail.equals(guest.geteMail()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId_client() ^ (getId_client() >>> 32));
        result = 31 * result + getSurName().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getSex().hashCode();
        result = 31 * result + geteMail().hashCode();
        return result;
    }
}
