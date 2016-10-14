package com.app.view.panels;

import com.app.control.AddClientControl;
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
public class AddNewClientPanelUI {

    private Shop shop;
    private Service serv;
    private ShopUI shGUI;

    private JPanel ncPanel;

    // contol fields
    private JFormattedTextField tfClID; // Client's ID for Client's Database
    private JTextField tfSurName;
    private JTextField tfName;

    private JTextField tfDay;
    private JTextField tfMonth;
    private JTextField tfYear;

    private JTextField tfSex;
    private JTextField tfEmail;


    public AddNewClientPanelUI(Shop shop, Service serv){
        this.shop = shop;
        this.serv = serv;
        this.ncPanel = createAddNewClientPanel();
    }

    private JPanel createAddNewClientPanel() {

        ncPanel = new JPanel();
        ncPanel.setLayout(new GridBagLayout());
        ncPanel.setBackground(Color.WHITE);

        Border matte = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE);
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border titled = BorderFactory.createTitledBorder(matte, "ADD NEW CLIENT", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Garamond", Font.BOLD, 24), Color.BLUE);
        ncPanel.setBorder(titled);

        NumberFormat nf = NumberFormat.getInstance();

        URL imageURL = getClass().getClassLoader().getResource("addClient.gif");
        ImageIcon icon = new ImageIcon(imageURL);
        JLabel picture = new JLabel(icon);
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.LEFT);
        //picture.setIcon(icon);
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        ncPanel.add(picture, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel id = new JLabel("Client's ID #: ");
        id.setFont(new Font("Garamond", Font.ITALIC, 20));
        id.setForeground(Color.BLUE);
        ncPanel.add(id, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfClID = new JFormattedTextField(nf);
        tfClID.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfClID.setForeground(Color.BLACK);
        tfClID.setColumns(12);
        tfClID.setValue(shop.getIdbI().getClts().size());
        tfClID.setHorizontalAlignment(JTextField.RIGHT);
        tfClID.setEditable(false);
        ncPanel.add(tfClID, new GridBagConstraints(1, 1, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel surName = new JLabel("Client's Surname: ");
        surName.setFont(new Font("Garamond", Font.ITALIC, 20));
        surName.setForeground(Color.BLUE);
        ncPanel.add(surName, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfSurName = new JTextField();
        tfSurName.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfSurName.setForeground(Color.BLACK);
        ncPanel.add(tfSurName, new GridBagConstraints(1, 2, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel yName = new JLabel("Name: ");
        yName.setFont(new Font("Garamond", Font.ITALIC, 20));
        yName.setForeground(Color.BLUE);
        yName.setHorizontalAlignment(JLabel.CENTER);
        ncPanel.add(yName, new GridBagConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfName = new JTextField();
        tfName.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfName.setForeground(Color.BLACK);
        tfName.setColumns(12);
        ncPanel.add(tfName, new GridBagConstraints(5, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel age = new JLabel("Client's date of birth: ");
        age.setFont(new Font("Garamond", Font.ITALIC, 20));
        age.setForeground(Color.BLUE);
        ncPanel.add(age, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfDay = new JTextField();
        tfDay.setForeground(Color.BLACK);
        tfDay.setHorizontalAlignment(JTextField.RIGHT);
        tfDay.setColumns(2);
        tfDay.setText("00");
        ncPanel.add(tfDay, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfMonth = new JTextField();
        tfMonth.setForeground(Color.BLACK);
        tfMonth.setHorizontalAlignment(JTextField.RIGHT);
        tfMonth.setColumns(2);
        tfMonth.setText("00");
        ncPanel.add(tfMonth, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfYear = new JTextField();
        tfYear.setForeground(Color.BLACK);
        tfYear.setHorizontalAlignment(JTextField.RIGHT);
        tfYear.setColumns(4);
        tfYear.setText("0000");
        ncPanel.add(tfYear, new GridBagConstraints(3, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel sex = new JLabel("Sex: ");
        sex.setFont(new Font("Garamond", Font.ITALIC, 20));
        sex.setForeground(Color.BLUE);
        sex.setHorizontalAlignment(JLabel.RIGHT);
        ncPanel.add(sex, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfSex = new JTextField();
        tfSex.setForeground(Color.BLACK);
        tfSex.setColumns(12);
        ncPanel.add(tfSex, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel eMail = new JLabel("E-mail: ");
        eMail.setFont(new Font("Garamond", Font.ITALIC, 20));
        eMail.setForeground(Color.BLUE);
        eMail.setHorizontalAlignment(JLabel.RIGHT);
        ncPanel.add(eMail, new GridBagConstraints(4, 4, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfEmail = new JTextField();
        tfEmail.setForeground(Color.BLACK);
        tfEmail.setColumns(16);
        ncPanel.add(tfEmail, new GridBagConstraints(5, 4, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton addNewClient = new JButton("Add new Client");
        addNewClient.setFont(new Font("Garamond", Font.BOLD, 20));
        ncPanel.add(addNewClient, new GridBagConstraints(1, 5, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        addNewClient.setBorder(empty);
        addNewClient.setForeground(Color.BLUE);
        addNewClient.addActionListener(new AddClientControl(shop, this, shGUI, serv));

        return ncPanel;
    }

    public JPanel getNcPanel() {
        return ncPanel;
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

    public JTextField getTfDay() {
        return tfDay;
    }

    public String getDay(){
        return getTfDay().getText();
    }

    public JTextField getTfMonth() {
        return tfMonth;
    }

    public String getMonth(){
        return getTfMonth().getText();
    }

    public JTextField getTfYear() {
        return tfYear;
    }

    public String getYear(){
        return getTfYear().getText();
    }


    public JTextField getTfSex() {
        return tfSex;
    }

    public String getSex(){

        return getTfSex().getText();
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public String getEmail(){

        return getTfEmail().getText();
    }

    public JFormattedTextField getTfClID() {
        return tfClID;
    }

    public String getClientID(){

        return getTfClID().getText();
    }
}

