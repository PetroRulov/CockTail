package com.app.control;

import com.app.domain.Product;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.panels.OrderPanelUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by prulov on 14.10.2016.
 */
public class RemoveLastItemFromOrder implements ActionListener {
    private Shop shop;
    private Service serv;
    private List<Product> list;
    private OrderPanelUI opUI;

    public RemoveLastItemFromOrder(Shop shop, Service serv, OrderPanelUI opUI) {
        this.shop = shop;
        this.serv = serv;
        this.opUI = opUI;
        this.list = shop.getIdbI().getProducts();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        serv.removeLastItem();
        if(serv.getOrderItems().isEmpty()){
            opUI.setTextArea("");
            opUI.setTextOrderValue(BigDecimal.ZERO);
        }else{
            for (int i = 0; i < serv.getOrderItems().size(); i++) {
                opUI.setTextArea(serv.getOrderItems().get(i).productInOrderInfo());
            }
            BigDecimal outcome = new BigDecimal(0.0);
            for (Product prod : serv.getOrderItems()) {
                outcome = outcome.add(prod.getPrice().multiply(new BigDecimal(prod.getCount())));
            }
            opUI.setTextOrderValue(outcome);
        }
    }
}
