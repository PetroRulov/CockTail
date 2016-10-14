package com.app.control;

import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;
import com.app.view.panels.SetAdjustedBuyerPanelUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by prulov on 14.10.2016.
 */
public class SoldByBuyerControl implements ActionListener {

    private Shop shop;
    private Service serv;
    private SetAdjustedBuyerPanelUI buyerUI;
    private ShopUI shGUI;
    private int buyerID;

    public SoldByBuyerControl(Shop shop, SetAdjustedBuyerPanelUI buyerUI, ShopUI shGUI, Service serv, int buyerID){

        this.shop = shop;
        this.serv = serv;
        this.buyerUI = buyerUI;
        this.shGUI = shGUI;
        this.buyerID = buyerUI.getBuyersID();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        shGUI.adjustedByBuyerSalesTableShow();
    }
}

