package com.app.view.panels;

import com.app.model.Shop;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by prulov on 14.10.2016.
 */
public class HeadPanel {

    private Shop shop;
    private JPanel hP;

    public HeadPanel(Shop shop) {
        this.shop = shop;
        this.hP = createHeadPieceUI();
    }

    private JPanel createHeadPieceUI() {

        hP = new JPanel();
        hP.setLayout(new GridBagLayout());
        hP.setBackground(Color.WHITE);

        URL imageURL = getClass().getClassLoader().getResource("shopHP.gif");
        ImageIcon icon = new ImageIcon(imageURL);
        JLabel picture = new JLabel(icon);
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.LEFT);
        picture.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        hP.add(picture, new GridBagConstraints(0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        return hP;

    }

    public JPanel gethP() {
        return hP;
    }
}
