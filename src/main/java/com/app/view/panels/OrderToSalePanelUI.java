package com.app.view.panels;

import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by prulov on 14.10.2016.
 */
public class OrderToSalePanelUI {

    private Shop shop;
    private Service serv;
    private ShopUI shGUI;

    private JPanel oToSPanel;

    public OrderToSalePanelUI (Shop shop, Service serv){
        this.shop = shop;
        this.serv = serv;
        this.oToSPanel = createOTOSPanel();
    }

    private JPanel createOTOSPanel() {

        oToSPanel = new JPanel();
        oToSPanel.setLayout(new GridBagLayout());
        oToSPanel.setBackground(Color.ORANGE);
        oToSPanel.setFont(new Font("Garamond", Font.BOLD, 20));

        Border etched = BorderFactory.createEtchedBorder();
        Border matte = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK);
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border titled = BorderFactory.createTitledBorder(matte, "SET SALE FROM ORDER", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Garamond", Font.BOLD, 20), Color.DARK_GRAY);

        oToSPanel.setBorder(titled);

        return oToSPanel;
    }

    public JPanel getoToSPanel() {
        return oToSPanel;
    }
}

