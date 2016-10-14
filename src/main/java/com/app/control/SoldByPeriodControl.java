package com.app.control;

import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;
import com.app.view.panels.SetAdjustedPeriodPanelUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by prulov on 14.10.2016.
 */
public class SoldByPeriodControl implements ActionListener {

    private Shop shop;
    private Service serv;
    private SetAdjustedPeriodPanelUI periodUI;
    private ShopUI shGUI;
    private String initDate;
    private String endDate;


    public SoldByPeriodControl(Shop shop, SetAdjustedPeriodPanelUI periodUI, ShopUI shGUI, Service serv, String initDate, String endDate){
        this.shop = shop;
        this.serv = serv;
        this.periodUI = periodUI;
        this.shGUI = shGUI;
        this.initDate = periodUI.getInitialDate();
        this.endDate = periodUI.getFinishDate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        shGUI.adjustedByDateSalesTableShow();
    }
}
