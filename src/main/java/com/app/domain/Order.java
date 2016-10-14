package com.app.domain;

import com.app.domain.enumerations.OrderStatus;
import com.app.domain.enumerations.PaymentTermsType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prulov on 14.10.2016.
 */
public class Order implements Serializable {

    private long id_order;
    private String date = dateFormat(new Date(System.currentTimeMillis()));
    private OrderStatus oSt;
    private PaymentTermsType payTT;
    private BigDecimal prepayment;
    private Water water;
    private int quantity;
    private Visitor client;
    private BigDecimal income;


    public Order(){}

    public Order(long id_order, String date, OrderStatus oSt, PaymentTermsType payTT, BigDecimal prepayment,
                 Water water, int quantity, Visitor client){
        this.id_order = id_order;
        this.date = date;
        this.oSt = oSt;
        this.payTT = payTT;
        this.prepayment = prepayment;
        this.water = water;
        this.quantity = quantity;
        this.client = client;
        this.income = calcIncome(water.getCount());
    }

    private String dateFormat(Date d){

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(d);
    }

    public BigDecimal calcIncome(int count){

        BigDecimal inCome = this.water.getPrice().multiply(new BigDecimal(count));

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

    public long getId_order() {
        return id_order;
    }

    public void setId_order(long id_order) {
        this.id_order = id_order;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OrderStatus getoSt() {
        return oSt;
    }

    public void setoSt(OrderStatus oSt) {
        this.oSt = oSt;
    }

    public PaymentTermsType getPayTT() {
        return payTT;
    }

    public void setPayTT(PaymentTermsType payTT) {
        this.payTT = payTT;
    }

    public BigDecimal getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(BigDecimal prepayment) {
        this.prepayment = prepayment.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Visitor getClient() {
        return client;
    }

    public void setClient(Visitor client) {
        this.client = client;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId_order() != order.getId_order()) return false;
        if (getQuantity() != order.getQuantity()) return false;
        if (!getDate().equals(order.getDate())) return false;
        if (getoSt() != order.getoSt()) return false;
        if (getPayTT() != order.getPayTT()) return false;
        if (!getPrepayment().equals(order.getPrepayment())) return false;
        if (!getWater().equals(order.getWater())) return false;
        if (!getClient().equals(order.getClient())) return false;
        return getIncome().equals(order.getIncome());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId_order() ^ (getId_order() >>> 32));
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getoSt().hashCode();
        result = 31 * result + getPayTT().hashCode();
        result = 31 * result + getPrepayment().hashCode();
        result = 31 * result + getWater().hashCode();
        result = 31 * result + getQuantity();
        result = 31 * result + getClient().hashCode();
        result = 31 * result + getIncome().hashCode();
        return result;
    }
}

