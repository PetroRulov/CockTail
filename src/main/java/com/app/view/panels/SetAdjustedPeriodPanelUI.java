package com.app.view.panels;

import com.app.control.SoldByPeriodControl;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ShopUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prulov on 14.10.2016.
 */
public class SetAdjustedPeriodPanelUI {

    private Shop shop;
    private Service serv;
    private ShopUI shGUI;
    private JPanel periodPanel;

    // control fields
    private JTextField firstDate;
    private JTextField endDate;


    public SetAdjustedPeriodPanelUI(Shop shop, Service serv, ShopUI shGUI){
        this.shop = shop;
        this.serv = serv;
        this.shGUI = shGUI;
        this.periodPanel = createPeriodtPanel();
    }

    private JPanel createPeriodtPanel() {

        periodPanel = new JPanel();
        periodPanel.setLayout(new GridBagLayout());
        periodPanel.setBackground(Color.BLUE);

        Border matte = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.YELLOW);
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border titled = BorderFactory.createTitledBorder(matte, "ADJUST PERIOD", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Garamond", Font.BOLD, 24), Color.ORANGE);
        periodPanel.setBorder(titled);

        URL imageURL = getClass().getClassLoader().getResource("background.gif");
        ImageIcon icon = new ImageIcon(imageURL);
        JLabel picture = new JLabel(icon);
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.LEFT);
        //picture.setIcon(icon);
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        periodPanel.add(picture, new GridBagConstraints(0, 0, 1, 2, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel set = new JLabel("Set adjusted period to be displayed");
        set.setFont(new Font("Garamond", Font.BOLD, 20));
        set.setForeground(Color.ORANGE);
        periodPanel.add(set, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel first = new JLabel("Initial date");
        first.setFont(new Font("Garamond", Font.ITALIC, 20));
        first.setForeground(Color.ORANGE);
        periodPanel.add(first, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        firstDate = new JTextField();
        firstDate.setFont(new Font("Garamond", Font.ITALIC, 20));
        firstDate.setForeground(Color.BLUE);
        firstDate.setHorizontalAlignment(JTextField.RIGHT);
        firstDate.setColumns(24);
        firstDate.setText(dateFormat(new Date(System.currentTimeMillis())));
        periodPanel.add(firstDate, new GridBagConstraints(1, 2, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel end = new JLabel("End date");
        end.setFont(new Font("Garamond", Font.ITALIC, 20));
        end.setForeground(Color.ORANGE);
        periodPanel.add(end, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        endDate = new JTextField();
        endDate.setFont(new Font("Garamond", Font.ITALIC, 20));
        endDate.setForeground(Color.BLUE);
        endDate.setHorizontalAlignment(JTextField.RIGHT);
        endDate.setColumns(24);
        endDate.setText(dateFormat(new Date(System.currentTimeMillis())));
        periodPanel.add(endDate, new GridBagConstraints(1, 3, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton display = new JButton("Display sales");
        display.setFont(new Font("Garamond", Font.BOLD, 20));
        periodPanel.add(display, new GridBagConstraints(1, 4, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        display.setBorder(empty);
        display.setForeground(Color.BLUE);
        display.addActionListener(new SoldByPeriodControl(shop, this, shGUI, serv, getInitialDate(), getFinishDate()));

        return periodPanel;
    }

    public String dateFormat(Date d){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(d);
    }

    public JTextField getFirstDate() {
        return firstDate;
    }

    public String getInitialDate(){
        return getFirstDate().getText();
    }

    public JTextField getEndDate() {
        return endDate;
    }

    public String getFinishDate(){
        return getEndDate().getText();
    }

    public JPanel getPeriodPanel() {
        return periodPanel;
    }
}