package com.app.domain;

import com.app.domain.enumerations.Tare;

import java.math.BigDecimal;

/**
 * Created by prulov on 14.10.2016.
 */
public class Water extends AbstractDrink {

    public Water() {
    }

    public Water(long id_water, String drink, String name, Tare tare, double volume, int quant, BigDecimal value, BigDecimal price) {
        super(id_water, drink, name, tare, volume, quant, value, price);
    }

    public String productInOrderInfo(){
        return getDrink()+", "+getName()+", "+getTare()+", "+getVolume()+", "+
                getPrice()+" - " + getCount() +" items,"+ "\n";
    }

}
