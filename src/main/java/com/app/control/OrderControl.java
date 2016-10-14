package com.app.control;

import com.app.domain.Order;
import com.app.domain.Product;
import com.app.domain.Visitor;
import com.app.domain.Water;
import com.app.domain.enumerations.OrderStatus;
import com.app.domain.enumerations.PaymentTermsType;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;
import com.app.view.panels.OrderPanelUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by prulov on 14.10.2016.
 */
public class OrderControl implements ActionListener, Observer {

    private Shop shop;
    private Service serv;
    private OrderPanelUI opUI;
    private ShopUI shGUI;

    public OrderControl(Shop shop, OrderPanelUI opUI, ShopUI shGUI, Service serv){
        this.shop = shop;
        this.serv = serv;
        this.opUI = opUI;
        this.shGUI = shGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        BigDecimal prepmnt = null;
        if(isDigit(opUI.getPrepayment())){
            prepmnt = new BigDecimal(opUI.getPrepayment());
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct amount of prepayment and try again!",
                    "Error message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        Visitor guest = null;
        if(visitorIsPresent()){
            guest = setTransactionVisitor();
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct data about Visitor and try again!",
                    "Clients Database", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        long id = Long.parseLong(opUI.getOrderID());
        String date = opUI.getDate();
        OrderStatus ordSt = opUI.getSelectedOrderStatus();
        PaymentTermsType pTT = opUI.getSelectedPaymentTermType();

        for(Product product : serv.getOrderItems()){
            Water wat = (Water) product;
            int count = product.getCount();

            if(wat.getQuant() >= count){
                shop.getIdbI().soldWaterMinus(wat, count);
                JOptionPane.showConfirmDialog(null, "Order execution is possible!", "Order's execution possibility", JOptionPane.PLAIN_MESSAGE);
                Order neo = new Order(id, date, ordSt, pTT, prepmnt, wat, count, guest);
                BigDecimal income = neo.calcIncome(count);
                neo.setIncome(income);
                shop.addNewOrderInJournal(neo);


            }else{
                JOptionPane.showConfirmDialog(null, "Order execution is NOT possible! \n Please, try again with new quantity of item",
                        "Order's execution possibility", JOptionPane.OK_CANCEL_OPTION);
            }
            serv.clearOrderItems();
        }
    }

    private Visitor setTransactionVisitor(){
        Visitor customer = null;
        for(Visitor v : shop.getIdbI().getVisitors()){
            if(v.getId_code() == Integer.parseInt(opUI.getVisitorID())){
                customer = v;
                break;
            }
        }
        return customer;
    }

    private boolean visitorIsPresent(){
        for(Visitor v : shop.getIdbI().getVisitors()){
            if(v.getId_code() == Integer.parseInt(opUI.getVisitorID())){
                return true;
            }
        }
        return false;
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Order) {
            shGUI.ordersJournalShow();
        }else{
            System.out.println(this.toString() + " notified.");
        }
    }
}
