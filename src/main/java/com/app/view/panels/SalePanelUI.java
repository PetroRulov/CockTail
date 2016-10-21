package com.app.view.panels;

import com.app.control.DisplayClient;
import com.app.control.SaleControl;
import com.app.domain.Product;
import com.app.domain.Water;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ProductComboBoxModel;
import com.app.view.ShopUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by prulov on 14.10.2016.
 */
public class SalePanelUI {

    private Shop shop;
    private Service serv;
    private ShopUI shGUI;

    private JPanel salePanel;

    // data
    private List<Product> goods = new ArrayList<>();

    //control fields
    private JTextField tfDate;

    private JTextField tfIDSale;
    private JFormattedTextField tfBID;
    private JFormattedTextField tfQuant;

    private JTextArea area;

    private JComboBox combo;

    public SalePanelUI(Shop shop, Service serv){
        this.shop = shop;
        this.serv = serv;
        this.salePanel = createSalePanel();
    }

    private JPanel createSalePanel() {

        salePanel = new JPanel();
        salePanel.setLayout(new GridBagLayout());
        salePanel.setBackground(Color.BLACK);
        salePanel.setFont(new Font("Garamond", Font.BOLD, 20));

        Border etched = BorderFactory.createEtchedBorder();
        Border matte = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.ORANGE);
        Border bevel = BorderFactory.createRaisedBevelBorder();
        Border colorBevel = BorderFactory.createBevelBorder(0, Color.ORANGE, Color.YELLOW);
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border titled = BorderFactory.createTitledBorder(matte, "SALE TRANSACTION", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Garamond", Font.BOLD, 20), Color.ORANGE);

        salePanel.setBorder(titled);

        goods = shop.getIdbI().getProducts();

        NumberFormat nf = NumberFormat.getInstance();

        URL imageURL = getClass().getClassLoader().getResource("background.gif");
        ImageIcon icon = new ImageIcon(imageURL);
        JLabel picture = new JLabel(icon);
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.LEFT);
        //picture.setIcon(icon);
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        salePanel.add(picture, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel date = new JLabel("Date (dd.MM.yyyy): ");
        date.setFont(new Font("Garamond", Font.BOLD, 20));
        date.setForeground(Color.ORANGE);
        salePanel.add(date, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfDate = new JTextField();
        tfDate.setFont(new Font("Garamond", Font.BOLD, 20));
        tfDate.setForeground(Color.BLACK);
        tfDate.setColumns(6);
        tfDate.setHorizontalAlignment(JTextField.RIGHT);
        tfDate.setText(dateFormat(new Date(System.currentTimeMillis())));
        salePanel.add(tfDate, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel idSale = new JLabel("Sale's identify #: ");
        idSale.setFont(new Font("Garamond", Font.ITALIC, 20));
        idSale.setForeground(Color.ORANGE);
        salePanel.add(idSale, new GridBagConstraints(4, 1, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfIDSale = new JFormattedTextField(nf);
        tfIDSale.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfIDSale.setForeground(Color.BLACK);
        tfIDSale.setColumns(12);
        tfIDSale.setHorizontalAlignment(JTextField.RIGHT);
        tfIDSale.setText(String.valueOf(shop.getIdbI().getSales().size() + 1));
        tfIDSale.setEditable(false);
        salePanel.add(tfIDSale, new GridBagConstraints(5, 1, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel id = new JLabel("Customer's identify #: ");
        id.setFont(new Font("Garamond", Font.ITALIC, 20));
        id.setForeground(Color.ORANGE);
        salePanel.add(id, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfBID = new JFormattedTextField(nf);
        tfBID.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfBID.setForeground(Color.BLACK);
        tfBID.setColumns(12);
        tfBID.setHorizontalAlignment(JTextField.RIGHT);
        tfBID.setValue(1);
        salePanel.add(tfBID, new GridBagConstraints(1, 2, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        area = new JTextArea(1, 1);
        area.setEditable(false);
        area.setFont(new Font("Garamond", Font.BOLD, 20));
        area.setForeground(Color.BLACK);
        salePanel.add(area, new GridBagConstraints(4, 2, 7, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel choose = new JLabel("Choose the good: ");
        choose.setFont(new Font("Garamond", Font.BOLD, 20));
        choose.setForeground(Color.ORANGE);
        salePanel.add(choose, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        combo = new JComboBox(new ProductComboBoxModel(goods));

        combo.setFont(new Font("Garamond", Font.BOLD, 20));
        combo.setForeground(Color.BLACK);
        salePanel.add(combo, new GridBagConstraints(1, 4, 10, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel quant = new JLabel("Quantity: ");
        quant.setFont(new Font("Garamond", Font.BOLD, 20));
        quant.setForeground(Color.ORANGE);
        salePanel.add(quant, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfQuant = new JFormattedTextField(nf);
        tfQuant.setFont(new Font("Garamond", Font.BOLD, 20));
        tfQuant.setForeground(Color.BLACK);
        tfQuant.setValue(1);
        tfQuant.setHorizontalAlignment(JTextField.RIGHT);
        salePanel.add(tfQuant, new GridBagConstraints(1, 5, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton confirmBuyer = new JButton("Confirm the Buyer");
        confirmBuyer.setFont(new Font("Garamond", Font.BOLD, 20));
        salePanel.add(confirmBuyer, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        confirmBuyer.setBorder(empty);
        confirmBuyer.setForeground(Color.RED);
        confirmBuyer.setBackground(Color.DARK_GRAY);
        confirmBuyer.addActionListener(new DisplayClient(shop, serv, this));

        JButton buy = new JButton("Buy");
        buy.setFont(new Font("Garamond", Font.BOLD, 20));
        salePanel.add(buy, new GridBagConstraints(1, 6, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        buy.setBorder(empty);
        buy.setForeground(Color.BLACK);
        buy.setBackground(Color.ORANGE);
        buy.addActionListener(new SaleControl(shop, this, shGUI, serv));

        return salePanel;
    }

    public JPanel getSalePanel() {
        return salePanel;
    }

    public String dateFormat(Date d){

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(d);
    }

    private JTextField getTfDate() {
        return tfDate;
    }

    public String getDate(){

        return getTfDate().getText();
    }

    private JTextField getTfIDSale() {
        return tfIDSale;
    }

    public String getIDSale(){
        return getTfIDSale().getText();
    }

    private JFormattedTextField getTfBID() {
        return tfBID;
    }

    public String getBuyerID(){

        return getTfBID().getText();
    }

    private JFormattedTextField getTfQuant() {
        return tfQuant;
    }

    public String getSaleQuantity(){

        return getTfQuant().getText();
    }

    private JTextArea getArea() {
        return area;
    }

    public void setTextArea(String str){
        getArea().setText(str);
    }

    private JComboBox getCombo() {
        return combo;
    }

    public Water getSelectedWater(){

        return (Water) getCombo().getSelectedItem();
    }

}

