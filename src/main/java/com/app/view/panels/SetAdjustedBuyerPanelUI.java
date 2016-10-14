package com.app.view.panels;

import com.app.control.SoldByBuyerControl;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by prulov on 14.10.2016.
 */
public class SetAdjustedBuyerPanelUI {

    private Shop shop;
    private Service serv;
    private ShopUI shGUI;
    private JPanel buyerPanel;

    // control fields
    private JFormattedTextField buyerID;


    public SetAdjustedBuyerPanelUI(Shop shop, Service serv, ShopUI shGUI){
        this.shop = shop;
        this.serv = serv;
        this.shGUI = shGUI;
        this.buyerPanel = createSetBuyerPanel();
    }

    private JPanel createSetBuyerPanel() {

        buyerPanel = new JPanel();
        buyerPanel.setLayout(new GridBagLayout());
        buyerPanel.setBackground(Color.WHITE);

        NumberFormat nf = NumberFormat.getInstance();

        Border matte = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.YELLOW);
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border titled = BorderFactory.createTitledBorder(matte, "ADJUST PERIOD", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Garamond", Font.BOLD, 24), Color.ORANGE);
        buyerPanel.setBorder(titled);

        JLabel set = new JLabel("Set buyer's ID");
        set.setFont(new Font("Garamond", Font.BOLD, 20));
        set.setForeground(Color.ORANGE);
        buyerPanel.add(set, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel first = new JLabel("Buyer's ID");
        first.setFont(new Font("Garamond", Font.ITALIC, 20));
        first.setForeground(Color.ORANGE);
        buyerPanel.add(first, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        buyerID = new JFormattedTextField(nf);;
        buyerID.setFont(new Font("Garamond", Font.BOLD, 20));
        buyerID.setForeground(Color.BLUE);
        buyerID.setHorizontalAlignment(JTextField.RIGHT);
        buyerID.setColumns(12);
        buyerID.setValue(901);
        buyerPanel.add(buyerID, new GridBagConstraints(1, 1, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton display = new JButton("Display sales");
        display.setFont(new Font("Garamond", Font.BOLD, 20));
        buyerPanel.add(display, new GridBagConstraints(1, 2, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        display.setBorder(empty);
        display.setForeground(Color.BLUE);
        display.addActionListener(new SoldByBuyerControl(shop, this, shGUI, serv, getBuyersID()));

        return buyerPanel;
    }

    public JFormattedTextField getBuyerID() {
        return buyerID;
    }

    public int getBuyersID(){
        return Integer.parseInt(getBuyerID().getText());
    }

    public JPanel getBuyerPanel() {
        return buyerPanel;
    }
}
