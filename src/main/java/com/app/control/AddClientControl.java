package com.app.control;

import com.app.domain.Client;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;
import com.app.view.panels.AddNewClientPanelUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by prulov on 14.10.2016.
 */
public class AddClientControl implements ActionListener, Observer {

    private Shop shop;
    private Service serv;
    private AddNewClientPanelUI ancPUI;
    private ShopUI shGUI;


    public AddClientControl(Shop shop, AddNewClientPanelUI ancPUI, ShopUI shGUI, Service serv){

        this.shop = shop;
        this.serv = serv;
        this.ancPUI = ancPUI;
        this.shGUI = shGUI;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Client guest = null;
        if(clientDataAreCorrect() && isPresent()){
            JOptionPane.showConfirmDialog(null, "This Client is already present in the database \n Try again, please.", "Clients Database", JOptionPane.PLAIN_MESSAGE);
        }else if(clientDataAreCorrect() && !isPresent()){
            guest = setClient();
            shop.addNewClient(guest);

            JOptionPane.showConfirmDialog(null, "New Client was successfully added in the database!", "Clients Database", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct Client's data and try again!",
                    "Clients Database", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof Client) {
            shGUI.clientsTableShow();
        }else{
            System.out.println(this.toString() + " notified.");
        }
    }

    private Client setClient(){
        return new Client(Integer.parseInt(ancPUI.getClientID()), ancPUI.getSurName(), ancPUI.getName(),
                ancPUI.getDay()+"/"+ancPUI.getMonth()+"/"+ancPUI.getYear(), ancPUI.getSex(), ancPUI.getEmail());
    }

    private boolean isPresent(){

        for(Client c : shop.getIdbI().getClts()){
            if(c.getId_client() == Integer.parseInt(ancPUI.getClientID())){
                return true;
            }
        }
        return false;
    }

    private boolean clientDataAreCorrect(){

        if(ancPUI.getSurName() != null && ! ancPUI.getSurName().isEmpty() &&
                ancPUI.getName() != null && ! ancPUI.getName().isEmpty() &&
                ancPUI.getDay() != null && ! ancPUI.getDay().isEmpty() &&
                Integer.parseInt(ancPUI.getDay()) > 0 && Integer.parseInt(ancPUI.getDay()) <= 31 &&
                ancPUI.getMonth() != null && ! ancPUI.getMonth().isEmpty() &&
                Integer.parseInt(ancPUI.getMonth()) > 0 && Integer.parseInt(ancPUI.getMonth()) <= 12 &&
                ancPUI.getYear() != null && ! ancPUI.getYear().isEmpty() &&
                Integer.parseInt(ancPUI.getYear()) > 1915 && Integer.parseInt(ancPUI.getMonth()) <= 1995 &&
                ancPUI.getSex() != null && ! ancPUI.getSex().isEmpty() &&
                ancPUI.getEmail() != null && ! ancPUI.getEmail().isEmpty()){
            return true;
        }
        return false;
    }
}

