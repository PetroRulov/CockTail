package com.app.control;

import com.app.domain.Client;
import com.app.domain.Product;
import com.app.domain.Sale;
import com.app.domain.Water;
import com.app.domain.enumerations.WaysOfSale;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;
import com.app.view.panels.SalePanelUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by prulov on 14.10.2016.
 */
public class SaleControl implements ActionListener, Observer {

    private Shop shop;
    private Service serv;
    private SalePanelUI spUI;
    private ShopUI shGUI;


    public SaleControl(Shop shop, SalePanelUI spUI, ShopUI shGUI, Service serv){

        this.shop = shop;
        this.serv = serv;
        this.spUI = spUI;
        this.shGUI = shGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Long id = 0l;
        if(isLong(spUI.getIDSale())){
            id = Long.parseLong(spUI.getIDSale());
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: WRONG Sale's identify number!",
                    "Error message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        if(spUI.getSelectedWater()!=null && Integer.parseInt(spUI.getSaleQuantity())>0){

            String date = spUI.getDate();

            Client guest = null;
            if(clientDataAreCorrect() && clientIsPresent()){
                JOptionPane.showConfirmDialog(null, "This Client is already present in the database", "Clients Database", JOptionPane.PLAIN_MESSAGE);
                guest = setTransactionClient();
            }else if(clientDataAreCorrect() && !clientIsPresent()){
                guest = setDefaultClient();
            }else{
                JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct data about Client and try again!",
                        "Clients Database", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            Product product = spUI.getSelectedWater();

            int saleQuantity = 0;
            if(isInt(spUI.getSaleQuantity())){
                saleQuantity = Integer.parseInt(spUI.getSaleQuantity());
            }else{
                JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct Quantity and try again!",
                        "Error message", JOptionPane.PLAIN_MESSAGE);
                return;
            }

            if(product.getQuant() >= saleQuantity){
                shop.setNewProductsQuantity(product, saleQuantity);
                JOptionPane.showConfirmDialog(null, "Transaction is possible!", "Transaction possibility", JOptionPane.PLAIN_MESSAGE);
                Sale neo = new Sale(id, date, guest, product, saleQuantity);
                if(clientIsPresent()){
                    BigDecimal fin = neo.getIncome().multiply(new BigDecimal(0.9));
                    neo.setIncome(fin.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                WaysOfSale wos = WaysOfSale.SHOP;
                neo.setWos(wos);
                long orderID = 0;
                neo.setOrderID(orderID);
                shop.addSaleTransaction(neo);
            }else{
                JOptionPane.showConfirmDialog(null, "Transaction is NOT possible! \n Please, try again with new quantity of item",
                        "Transaction possibility", JOptionPane.OK_CANCEL_OPTION);
            }
        }else{
            JOptionPane.showConfirmDialog(null, "Transaction is NOT possible! \n Please, select item and correct quantity",
                    "Transaction possibility", JOptionPane.OK_CANCEL_OPTION);
        }
    }

    private boolean isLong(String str){
        try{
            Long.parseLong(str);
            return  true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }

    private Client setTransactionClient(){
        Client buyer = null;
        for(Client c : shop.getIdbI().getClts()){
            if(c.getId_client() == Integer.parseInt(spUI.getBuyerID())){
                buyer = c;
                break;
            }
        }
        return buyer;
    }

    private boolean clientIsPresent(){
        for(Client c : shop.getIdbI().getClts()){
            if(c.getId_client() == Integer.parseInt(spUI.getBuyerID()) && c.getId_client() != 0 ){
                return true;
            }
        }
        return false;
    }

    private boolean clientDataAreCorrect(){
        if(Integer.parseInt(spUI.getBuyerID()) >= 0){
            return true;
        }
        return false;
    }

    private Client setDefaultClient(){
        return new Client(0, "Unknown", "Unknown", "Unknown", "none", "Unknown");
    }

    private boolean isInt(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof Sale) {
            shGUI.salesTableShow();
        }else{
            System.out.println(this.toString() + " notified.");
        }
    }
}
