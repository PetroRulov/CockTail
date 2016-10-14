package com.app.domain;

import com.app.domain.enumerations.WaysOfSale;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prulov on 14.10.2016.
 */
public class Sale implements Serializable {

    private long id_sale;
    private String date = dateFormat(new Date(System.currentTimeMillis()));
    private Client guest;
    private Water wat;
    private int quant;
    private BigDecimal income;
    private WaysOfSale wos;
    private long orderID;

    public Sale(){}

    public Sale(long id_sale, String date, Client guest, Water wat, int quant){

        this.id_sale = id_sale;
        this.date = date;
        this.guest = guest;
        this.wat = wat;
        this.quant = quant;
        this.income = calcIncome();
    }

    public long getId_sale() {
        return id_sale;
    }

    public void setId_sale(long id_sale) {
        this.id_sale = id_sale;
    }

    private BigDecimal calcIncome(){
        BigDecimal inCome = this.wat.getPrice().multiply(new BigDecimal(getQuant()));

        if(inCome.compareTo(new BigDecimal("500")) == -1){
            income = inCome;
        }else if((inCome.compareTo(new BigDecimal("500")) == 0 || inCome.compareTo(new BigDecimal("500")) == 1) &&
                inCome.compareTo(new BigDecimal("1000")) == -1){
            income = inCome.multiply(new BigDecimal(0.95));
        }else{
            income = inCome.multiply(new BigDecimal(0.9)) ;
        }
        return income.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client getGuest() {
        return guest;
    }

    public void setGuest(Client guest) {
        this.guest = guest;
    }

    public Water getWat() {
        return wat;
    }

    public void setWat(Water wat) {
        this.wat = wat;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public WaysOfSale getWos() {
        return wos;
    }

    public void setWos(WaysOfSale wos) {
        this.wos = wos;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    private String dateFormat(Date d){

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(d);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id_sale=" + id_sale +
                ", date='" + date + '\'' +
                ", guest=" + guest +
                ", wat=" + wat +
                ", quant=" + quant +
                ", income=" + income +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;

        Sale sale = (Sale) o;

        if (getId_sale() != sale.getId_sale()) return false;
        if (getQuant() != sale.getQuant()) return false;
        if (getOrderID() != sale.getOrderID()) return false;
        if (!getDate().equals(sale.getDate())) return false;
        if (!getGuest().equals(sale.getGuest())) return false;
        if (!getWat().equals(sale.getWat())) return false;
        if (!getIncome().equals(sale.getIncome())) return false;
        return getWos() == sale.getWos();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId_sale() ^ (getId_sale() >>> 32));
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getGuest().hashCode();
        result = 31 * result + getWat().hashCode();
        result = 31 * result + getQuant();
        result = 31 * result + getIncome().hashCode();
        result = 31 * result + getWos().hashCode();
        result = 31 * result + (int) (getOrderID() ^ (getOrderID() >>> 32));
        return result;
    }
}
