package com.app.domain;

import com.app.domain.enumerations.Tare;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by prulov on 14.10.2016.
 */
public abstract class AbstractDrink implements Product, Serializable {

    protected long id_water;
    protected String drink;
    protected String name;
    protected Tare tare;
    protected double volume;
    protected int quant;
    protected BigDecimal value;
    protected BigDecimal price;
    protected int count;

    public AbstractDrink(){}

    public AbstractDrink(long id_water, String drink, String name, Tare tare, double volume, int quant,
                         BigDecimal value, BigDecimal price){

        this.id_water = id_water;
        this.drink = drink;
        this.name = name;
        this.tare = tare;
        this.volume = volume;
        this.quant = quant;
        this.value = value;
        this.price = price;
        this.count = 0;
    }

    public long getId_water() {
        return id_water;
    }

    public void setId_water(long id_water) {
        this.id_water = id_water;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tare getTare() {
        return tare;
    }

    public void setTare(Tare tare) {
        this.tare = tare;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public BigDecimal getValue() {
        return value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getPrice() {return price.setScale(2, BigDecimal.ROUND_HALF_UP);}

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "id #" + getId_water() + " " + getDrink() + " " + getName() + ", " + getTare() + " " + getVolume() +
                "L, price: UAH " + getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString() + ", " + getQuant() + " item(s) left";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDrink)) return false;

        AbstractDrink that = (AbstractDrink) o;

        if (getId_water() != that.getId_water()) return false;
        if (Double.compare(that.getVolume(), getVolume()) != 0) return false;
        if (getQuant() != that.getQuant()) return false;
        if (getCount() != that.getCount()) return false;
        if (!getDrink().equals(that.getDrink())) return false;
        if (!getName().equals(that.getName())) return false;
        if (getTare() != that.getTare()) return false;
        if (!getValue().equals(that.getValue())) return false;
        return getPrice().equals(that.getPrice());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getId_water() ^ (getId_water() >>> 32));
        result = 31 * result + getDrink().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getTare().hashCode();
        temp = Double.doubleToLongBits(getVolume());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getQuant();
        result = 31 * result + getValue().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getCount();
        return result;
    }

    public abstract String productInOrderInfo();

}
