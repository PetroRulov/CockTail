package com.app.control;

import com.app.domain.Visitor;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;
import com.app.view.panels.AddNewVisitorPanelUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by prulov on 14.10.2016.
 */
public class AddVisitorControl implements ActionListener, Observer {
    private Shop shop;
    private Service serv;
    private AddNewVisitorPanelUI anvPUI;
    private ShopUI shGUI;


    public AddVisitorControl(Shop shop, AddNewVisitorPanelUI anvPUI, ShopUI shGUI, Service serv){

        this.shop = shop;
        this.serv = serv;
        this.anvPUI = anvPUI;
        this.shGUI = shGUI;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Visitor guest = null;
        if(visitorsDataAreCorrect() && isPresent()){
            JOptionPane.showConfirmDialog(null, "This Visitor is already present in the database \n Try again, please.", "Visitor's Database", JOptionPane.PLAIN_MESSAGE);
        }else if(visitorsDataAreCorrect() && !isPresent()){
            guest = setVisitor();
            guest.setIdentify(anvPUI.getIdentify());
            shop.addNewVisitor(guest);

            JOptionPane.showConfirmDialog(null, "New Visitor was successfully added in the database!", "Visitor's Database", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct Visitor's data and try again!",
                    "Visitor's Database", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    }

    private Visitor setVisitor(){

        return new Visitor(Integer.parseInt(anvPUI.getVisitorID()), anvPUI.getSurName(), anvPUI.getName(),
                anvPUI.getTelFax(), anvPUI.getAddress(), anvPUI.getEmail());

    }

    private boolean isPresent(){

        for(Visitor v : shop.getIdbI().getVisitors()){
            if(v.getId_code() == Integer.parseInt(anvPUI.getVisitorID())){
                return true;
            }
        }
        return false;
    }

    private boolean visitorsDataAreCorrect(){

        if(anvPUI.getSurName() != null && ! anvPUI.getSurName().isEmpty() &&
                anvPUI.getName() != null && ! anvPUI.getName().isEmpty() &&
                anvPUI.getTelFax() != null && ! anvPUI.getTelFax().isEmpty() &&
                anvPUI.getAddress() != null && ! anvPUI.getAddress().isEmpty() &&
                anvPUI.getEmail() != null && ! anvPUI.getEmail().isEmpty()){
            return true;
        }
        return false;
    }


    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof Visitor) {
            shGUI.visitorsTableShow();
        }else{
            System.out.println(this.toString() + " notified.");
        }
    }
}

