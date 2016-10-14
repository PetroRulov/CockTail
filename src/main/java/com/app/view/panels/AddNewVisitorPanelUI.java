package com.app.view.panels;

import com.app.control.AddVisitorControl;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.net.URL;
import java.text.NumberFormat;

/**
 * Created by prulov on 14.10.2016.
 */
public class AddNewVisitorPanelUI {

    private Shop shop;
    private Service serv;
    private ShopUI shGUI;

    private JPanel nvPanel;

    // contol fields
    private JTextField tfSurName;
    private JTextField tfName;
    private JTextField tfTel;
    private JTextField tfAddress;
    private JTextField tfEmail;

    private JFormattedTextField tfVlID; // Visitor's ID for Client's Database
    private JTextField tfIN;

    public AddNewVisitorPanelUI(Shop shop, Service serv){
        this.shop = shop;
        this.serv = serv;
        this.nvPanel = createAddNewVisitorPanel();
    }

    private JPanel createAddNewVisitorPanel() {

        nvPanel = new JPanel();
        nvPanel.setLayout(new GridBagLayout());
        nvPanel.setBackground(Color.WHITE);

        Border matte = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.GREEN);
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border titled = BorderFactory.createTitledBorder(matte, "ADD NEW SITE-VISITOR", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Garamond", Font.BOLD, 24), Color.GREEN);
        nvPanel.setBorder(titled);

        NumberFormat nf = NumberFormat.getInstance();

        URL imageURL = getClass().getClassLoader().getResource("addClient.gif");
        ImageIcon icon = new ImageIcon(imageURL);
        JLabel picture = new JLabel(icon);
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.LEFT);
        //picture.setIcon(icon);
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        nvPanel.add(picture, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel id = new JLabel("Visitor's ID #: ");
        id.setFont(new Font("Garamond", Font.ITALIC, 20));
        id.setForeground(Color.GREEN);
        nvPanel.add(id, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfVlID = new JFormattedTextField(nf);
        tfVlID.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfVlID.setForeground(Color.BLACK);
        tfVlID.setColumns(12);
        tfVlID.setValue(shop.getIdbI().getVisitors().size() + 1);
        tfVlID.setHorizontalAlignment(JTextField.RIGHT);
        tfVlID.setEditable(false);
        nvPanel.add(tfVlID, new GridBagConstraints(1, 1, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel ident = new JLabel("Identify #: ");
        ident.setFont(new Font("Garamond", Font.ITALIC, 20));
        ident.setForeground(Color.GREEN);
        nvPanel.add(ident, new GridBagConstraints(4, 1, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfIN = new JTextField();
        tfIN.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfIN.setForeground(Color.BLACK);
        tfIN.setColumns(12);
        tfIN.setText(String.valueOf(shop.getIdbI().getVisitors().size() + 1));
        tfIN.setHorizontalAlignment(JTextField.RIGHT);
        tfIN.setEditable(true);
        nvPanel.add(tfIN, new GridBagConstraints(5, 1, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));


        JLabel surName = new JLabel("Visitor's Surname: ");
        surName.setFont(new Font("Garamond", Font.ITALIC, 20));
        surName.setForeground(Color.GREEN);
        nvPanel.add(surName, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfSurName = new JTextField();
        tfSurName.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfSurName.setForeground(Color.BLACK);
        tfSurName.setColumns(12);
        nvPanel.add(tfSurName, new GridBagConstraints(1, 2, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel yName = new JLabel("Name: ");
        yName.setFont(new Font("Garamond", Font.ITALIC, 20));
        yName.setForeground(Color.GREEN);
        yName.setHorizontalAlignment(JLabel.RIGHT);
        nvPanel.add(yName, new GridBagConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfName = new JTextField();
        tfName.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfName.setForeground(Color.BLACK);
        tfName.setColumns(12);
        nvPanel.add(tfName, new GridBagConstraints(5, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel tel = new JLabel("Visitor's tel/fax: ");
        tel.setFont(new Font("Garamond", Font.ITALIC, 20));
        tel.setForeground(Color.GREEN);
        tel.setHorizontalAlignment(JLabel.RIGHT);
        nvPanel.add(tel, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfTel = new JTextField();
        tfTel.setForeground(Color.BLACK);
        tfTel.setHorizontalAlignment(JTextField.RIGHT);
        tfTel.setColumns(6);
        tfTel.setText("+380(00) 000-00-00");
        nvPanel.add(tfTel, new GridBagConstraints(1, 3, 5, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel address = new JLabel("Visitor's address: ");
        address.setFont(new Font("Garamond", Font.ITALIC, 20));
        address.setForeground(Color.GREEN);
        address.setHorizontalAlignment(JLabel.RIGHT);
        nvPanel.add(address, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfAddress = new JTextField();
        tfAddress.setForeground(Color.BLACK);
        tfAddress.setColumns(6);
        nvPanel.add(tfAddress, new GridBagConstraints(1, 4, 5, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel eMail = new JLabel("E-mail: ");
        eMail.setFont(new Font("Garamond", Font.ITALIC, 20));
        eMail.setForeground(Color.GREEN);
        eMail.setHorizontalAlignment(JLabel.RIGHT);
        nvPanel.add(eMail, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfEmail = new JTextField();
        tfEmail.setForeground(Color.BLACK);
        tfEmail.setColumns(16);
        nvPanel.add(tfEmail, new GridBagConstraints(1, 5, 5, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton addNewVisitor = new JButton("Add new Visitor");
        addNewVisitor.setFont(new Font("Garamond", Font.BOLD, 20));
        nvPanel.add(addNewVisitor, new GridBagConstraints(1, 6, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        addNewVisitor.setBorder(empty);
        addNewVisitor.setForeground(Color.GREEN);
        addNewVisitor.addActionListener(new AddVisitorControl(shop, this, shGUI, serv));

        return nvPanel;
    }

    public JPanel getNvPanel() {
        return nvPanel;
    }

    public JTextField getTfSurName() {
        return tfSurName;
    }

    public String getSurName(){
        return getTfSurName().getText();
    }

    public JTextField getTfName() {
        return tfName;
    }

    public String getName(){
        return getTfName().getText();
    }

    public JTextField getTfTelFax() {
        return tfTel;
    }

    public String getTelFax(){
        return getTfTelFax().getText();
    }

    public JTextField getTfAddress() {
        return tfAddress;
    }

    public String getAddress(){

        return getTfAddress().getText();
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public String getEmail(){

        return getTfEmail().getText();
    }

    public JFormattedTextField getTfVlID() {
        return tfVlID;
    }

    public String getVisitorID(){

        return getTfVlID().getText();
    }

    public JTextField getTfIN() {
        return tfIN;
    }

    public String getIdentify(){
        return getTfIN().getText();
    }
}

