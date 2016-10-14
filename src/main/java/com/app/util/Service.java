package com.app.util;

import com.app.model.Shop;
import com.app.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prulov on 14.10.2016.
 */
public class Service  {

    private Shop shop;
    private List<Product> orderItems;


    public Service(Shop shop){

        this.shop = shop;
        orderItems = new ArrayList<>();
    }

    public void addOrderItems(Product product){
        orderItems.add(product);
    }

    public void removeLastItem(){
        if(orderItems.size()>=2){
            orderItems.remove(orderItems.size() - 1);
        }else{
            orderItems.clear();
        }
    }

    public List<Product> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public void clearOrderItems() {
        orderItems.clear();
    }

}
