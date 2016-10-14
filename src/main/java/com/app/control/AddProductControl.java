package com.app.control;

import com.app.domain.Product;
import com.app.domain.Water;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;
import com.app.view.panels.AddNewProductPanelUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by prulov on 14.10.2016.
 */
public class AddProductControl implements ActionListener, Observer {

    private Shop shop;
    private Service serv;
    private AddNewProductPanelUI anpPUI;
    private ShopUI shGUI;

    public AddProductControl(Shop shop, AddNewProductPanelUI anpPUI, ShopUI shGUI, Service serv){
        this.shop = shop;
        this.serv = serv;
        this.anpPUI = anpPUI;
        this.shGUI = shGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Double volume = 0.0;
        if(isDigit(anpPUI.getVolume())){
            volume = Double.parseDouble(anpPUI.getVolume());
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct volume of the tare and try again!",
                    "Error message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        int quantity = 0;
        if(isInt(anpPUI.getQuantity())){
            quantity = Integer.parseInt(anpPUI.getQuantity());
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct Quantity and try again!",
                    "Error message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        BigDecimal value = null;
        if(isDigit(anpPUI.getValue())){
            value = new BigDecimal(anpPUI.getValue());
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct Value and try again!",
                    "Error message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        BigDecimal price = null;
        if(isDigit(anpPUI.getPrice())){
            price = new BigDecimal(anpPUI.getPrice());
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct Price and try again!",
                    "Error message", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        Product product = null;
        if(productsDataAreCorrect() && isPresent()){
            JOptionPane.showConfirmDialog(null, "This Product is already present in Stock \n Try again, please.", "STOCK", JOptionPane.PLAIN_MESSAGE);
        }else if(productsDataAreCorrect() && !isPresent()){
            product = (Product) new Water(Long.parseLong(anpPUI.getProductID()), anpPUI.getDrink(), anpPUI.getName(),
                    anpPUI.getSelectedTareType(), volume, quantity, value, price);
            product.setCount(0);

            shop.addNewProduct(product);

            JOptionPane.showConfirmDialog(null, "New Product was successfully added in the Stock!", "STOCK", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showConfirmDialog(null, "ERROR: Please, input correct Product's data and try again!",
                    "Visitor's Database", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    }

    private boolean productsDataAreCorrect() {
        if(anpPUI.getProductID() != null && !anpPUI.getProductID().isEmpty() &&
                anpPUI.getDrink() != null && !anpPUI.getDrink().isEmpty() &&
                anpPUI.getName() != null && !anpPUI.getName().isEmpty() &&
                anpPUI.getVolume() != null && !anpPUI.getVolume().isEmpty() &&
                anpPUI.getQuantity() != null && !anpPUI.getQuantity().isEmpty() &&
                anpPUI.getValue() != null && !anpPUI.getValue().isEmpty() &&
                anpPUI.getPrice() != null && !anpPUI.getPrice().isEmpty()
                ){
            return true;
        }
        return false;
    }

    private boolean isPresent(){
        for(Product prod : shop.getIdbI().getProducts()){
            if(prod.getId_water() == Integer.parseInt(anpPUI.getProductID())){
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
        if (arg instanceof Product) {
            shGUI.priceListShow();
        }else{
            System.out.println(this.toString() + " notified.");
        }
    }
}

