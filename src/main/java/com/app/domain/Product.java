package com.app.domain;

import com.app.domain.enumerations.Tare;

import java.math.BigDecimal;

/**
 * Created by prulov on 14.10.2016.
 */
public interface Product {

    //basic
    long getId_water();
    String getDrink();
    String getName();
    Tare getTare();
    double getVolume();
    int getQuant();
    BigDecimal getValue();
    BigDecimal getPrice();





    void setCount(int count);

    String productInOrderInfo();

    int getCount();
}
