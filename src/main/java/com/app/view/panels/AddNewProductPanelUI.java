package com.app.view.panels;

import com.app.control.AddProductControl;
import com.app.domain.enumerations.Tare;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ObjectComboBoxModel;
import com.app.view.ShopUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.List;
import java.net.URL;
import java.util.*;

/**
 * Created by prulov on 14.10.2016.
 */
public class AddNewProductPanelUI {

    private Shop shop;
    private Service serv;
    private ShopUI shGUI;
    private Color greenColor;
    private Color redColor;

    private JPanel npPanel;

    private java.util.List<Tare> tares;

    private JTextField tfID;
    private JTextField tfType;
    private JTextField tfName;
    private JTextField tfVolume;
    private JTextField tfQuant;
    private JTextField tfValue;
    private JTextField tfPrice;

    private JComboBox comboTares;


    public AddNewProductPanelUI(Shop shop, Service serv){
        this.shop = shop;
        this.serv = serv;
        this.greenColor = new Color(0, 210, 0);
        this.redColor = new Color(180, 0, 20);
        this.npPanel = createAddNewProductPanel();
    }

    private JPanel createAddNewProductPanel() {

        npPanel = new JPanel();
        npPanel.setLayout(new GridBagLayout());
        npPanel.setBackground(Color.WHITE);

        tares = createTareTypesList();

        Border matte = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE);
        Border buton = BorderFactory.createMatteBorder(2, 2, 2, 2, greenColor);
        Border titled = BorderFactory.createTitledBorder(matte, "ADD NEW PRODUCT", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Garamond", Font.BOLD, 24), greenColor);
        npPanel.setBorder(titled);

        URL imageURL = getClass().getClassLoader().getResource("kokteli.jpg");
        ImageIcon icon = new ImageIcon(imageURL);
        JLabel picture = new JLabel(icon);
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.LEFT);
        picture.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        npPanel.add(picture, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel type = new JLabel("Type of drink: ");
        type.setFont(new Font("Garamond", Font.ITALIC, 20));
        type.setForeground(greenColor);
        npPanel.add(type, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfType = new JTextField();
        tfType.setFont(new Font("Garamond", Font.BOLD, 20));
        tfType.setForeground(redColor);
        tfType.setHorizontalAlignment(JTextField.RIGHT);
        npPanel.add(tfType, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel id = new JLabel("Identificational code: ");
        id.setFont(new Font("Garamond", Font.ITALIC, 20));
        id.setForeground(greenColor);
        id.setHorizontalAlignment(JLabel.RIGHT);
        npPanel.add(id, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfID = new JTextField();
        tfID.setFont(new Font("Garamond", Font.BOLD, 20));
        tfID.setForeground(redColor);
        tfID.setHorizontalAlignment(JTextField.RIGHT);
        tfID.setEditable(false);
        tfID.setText(String.valueOf(shop.getIdbI().getProducts().size() + 1));
        npPanel.add(tfID, new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel name = new JLabel("Name of drink: ");
        name.setFont(new Font("Garamond", Font.ITALIC, 20));
        name.setForeground(greenColor);
        name.setHorizontalAlignment(JLabel.RIGHT);
        npPanel.add(name, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfName = new JTextField();
        tfName.setFont(new Font("Garamond", Font.BOLD, 20));
        tfName.setForeground(redColor);
        tfName.setHorizontalAlignment(JTextField.RIGHT);
        npPanel.add(tfName, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel tare = new JLabel("Choose the Tare: ");
        tare.setFont(new Font("Garamond", Font.ITALIC, 20));
        tare.setForeground(greenColor);
        tare.setHorizontalAlignment(JLabel.RIGHT);
        npPanel.add(tare, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        comboTares = new JComboBox(new ObjectComboBoxModel<Tare>(tares));
        comboTares.setFont(new Font("Garamond", Font.BOLD, 20));
        comboTares.setForeground(redColor);
        comboTares.setSelectedItem(Tare.GLASS);
        npPanel.add(comboTares, new GridBagConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel volume = new JLabel("Volume, L: ");
        volume.setFont(new Font("Garamond", Font.ITALIC, 20));
        volume.setForeground(greenColor);
        volume.setHorizontalAlignment(JLabel.RIGHT);
        npPanel.add(volume, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfVolume = new JTextField();
        tfVolume.setFont(new Font("Garamond", Font.BOLD, 20));
        tfVolume.setForeground(redColor);
        tfVolume.setHorizontalAlignment(JTextField.RIGHT);
        tfVolume.setText("0.0");
        npPanel.add(tfVolume, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel quant = new JLabel("Quantity: ");
        quant.setFont(new Font("Garamond", Font.ITALIC, 20));
        quant.setForeground(greenColor);
        quant.setHorizontalAlignment(JLabel.RIGHT);
        npPanel.add(quant, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfQuant = new JTextField();
        tfQuant.setFont(new Font("Garamond", Font.BOLD, 20));
        tfQuant.setForeground(redColor);
        tfQuant.setHorizontalAlignment(JTextField.RIGHT);
        npPanel.add(tfQuant, new GridBagConstraints(3, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel value = new JLabel("Value, UAH: ");
        value.setFont(new Font("Garamond", Font.ITALIC, 20));
        value.setForeground(greenColor);
        value.setHorizontalAlignment(JLabel.RIGHT);
        npPanel.add(value, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfValue = new JTextField();
        tfValue.setFont(new Font("Garamond", Font.BOLD, 20));
        tfValue.setForeground(redColor);
        tfValue.setHorizontalAlignment(JTextField.RIGHT);
        tfValue.setText("00.00");
        npPanel.add(tfValue, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel price = new JLabel("Price, UAH: ");
        price.setFont(new Font("Garamond", Font.ITALIC, 20));
        price.setForeground(greenColor);
        price.setHorizontalAlignment(JLabel.RIGHT);
        npPanel.add(price, new GridBagConstraints(2, 4, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfPrice = new JTextField();
        tfPrice.setFont(new Font("Garamond", Font.BOLD, 20));
        tfPrice.setForeground(redColor);
        tfPrice.setHorizontalAlignment(JTextField.RIGHT);
        tfPrice.setText("00.00");
        npPanel.add(tfPrice, new GridBagConstraints(3, 4, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton addNewProduct = new JButton("Add new Product");
        addNewProduct.setFont(new Font("Garamond", Font.BOLD, 20));
        npPanel.add(addNewProduct, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        addNewProduct.setBorder(buton);
        addNewProduct.setBackground(Color.yellow);
        addNewProduct.setForeground(Color.BLUE);
        addNewProduct.addActionListener(new AddProductControl(shop, this, shGUI, serv));

        return npPanel;
    }

    public JPanel getNPPanel() {
        return npPanel;
    }

    public JTextField getTfID() {
        return tfID;
    }

    public String getProductID(){
        return getTfID().getText();
    }

    public JTextField getTfType() {
        return tfType;
    }

    public String getDrink(){
        return getTfType().getText();
    }

    public JTextField getTfName() {
        return tfName;
    }

    public String getName(){
        return getTfName().getText();
    }

    private java.util.List<Tare> createTareTypesList() {
        tares = new ArrayList<>();
        for(Tare obj : Tare.values()){
            tares.add(obj);
        }
        return tares;
    }

    public JComboBox getComboTares() {
        return comboTares;
    }

    public Tare getSelectedTareType(){
        return (Tare) getComboTares().getSelectedItem();
    }

    public JTextField getTfVolume() {
        return tfVolume;
    }

    public String getVolume(){
        return getTfVolume().getText();
    }

    public JTextField getTfQuant() {
        return tfQuant;
    }

    public String getQuantity(){
        return getTfQuant().getText();
    }

    public JTextField getTfValue() {
        return tfValue;
    }

    public String getValue(){
        return getTfValue().getText();
    }

    public JTextField getTfPrice() {
        return tfPrice;
    }

    public String getPrice(){
        return getTfPrice().getText();
    }

}

