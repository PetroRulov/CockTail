package com.app.view.panels;

import com.app.control.AddItemToOrder;
import com.app.control.DisplayVisitor;
import com.app.control.OrderControl;
import com.app.control.RemoveLastItemFromOrder;
import com.app.domain.Product;
import com.app.domain.Water;
import com.app.domain.enumerations.OrderStatus;
import com.app.domain.enumerations.PaymentTermsType;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.ObjectComboBoxModel;
import com.app.view.ProductComboBoxModel;
import com.app.view.ShopUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by prulov on 14.10.2016.
 */
public class OrderPanelUI {

    private Shop shop;
    private Service serv;
    private ShopUI shGUI;
    private AddItemToOrder addITOControl;
    private RemoveLastItemFromOrder removeLIFOControl;
    private OrderControl ordControl;

    private JPanel orderPanel;

    // data
    private List<Product> goods = new ArrayList<>();
    private List<OrderStatus> statusTypes;
    private List<PaymentTermsType> paymentType;

    //control fields
    private JTextField tfDate;

    private JTextField tfID;
    private JTextField tfVID;
    private JTextField tfQuant;
    private JTextField tfOrderCost;
    private JTextField tfPrePmnt;

    private JTextArea area;
    private JTextArea visitorArea;

    private JComboBox combo;
    private JComboBox comboStatus;
    private JComboBox comboPaymentType;


    public OrderPanelUI(Shop shop, Service serv){
        this.shop = shop;
        this.serv = serv;
        this.addITOControl = new AddItemToOrder(shop, serv, this);
        this.removeLIFOControl = new RemoveLastItemFromOrder(shop, serv, this);
        this.ordControl = new OrderControl(shop, this, shGUI, serv);
        this.orderPanel = createOrderPanel();
    }

    private JPanel createOrderPanel() {

        orderPanel = new JPanel();
        orderPanel.setLayout(new GridBagLayout());
        orderPanel.setBackground(Color.ORANGE);
        orderPanel.setFont(new Font("Garamond", Font.BOLD, 20));

        goods = shop.getIdbI().getProducts();
        statusTypes = createStatusTypesList();
        paymentType = createPaymentTypeList();

        Border etched = BorderFactory.createEtchedBorder();
        Border matte = BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK);
        Border bevel = BorderFactory.createRaisedSoftBevelBorder();
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border titled = BorderFactory.createTitledBorder(bevel, "NEW ORDER", TitledBorder.CENTER,
                TitledBorder.CENTER, new Font("Garamond", Font.BOLD, 20), Color.DARK_GRAY);

        orderPanel.setBorder(titled);

        URL imageURL = getClass().getClassLoader().getResource("orderNew.png");
        ImageIcon icon = new ImageIcon(imageURL);
        JLabel picture = new JLabel(icon);
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.LEFT);
        picture.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        orderPanel.add(picture, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        area = new JTextArea(5, 20);
        area.setEditable(false);
        area.setFont(new Font("Garamond", Font.BOLD, 20));
        area.setForeground(Color.BLACK);
        orderPanel.add(area, new GridBagConstraints(2, 0, 10, 2, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel orderID = new JLabel("Orders #: ");
        orderID.setFont(new Font("Garamond", Font.BOLD, 20));
        orderID.setForeground(Color.BLACK);
        orderPanel.add(orderID, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfID = new JTextField();
        tfID.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfID.setForeground(Color.BLACK);
        tfID.setColumns(12);
        tfID.setHorizontalAlignment(JTextField.RIGHT);
        tfID.setText(String.valueOf(shop.getIdbI().getOrders().size() + 1));
        tfID.setEditable(false);
        orderPanel.add(tfID, new GridBagConstraints(1, 2, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel orderValue = new JLabel("Order's value");
        orderValue.setFont(new Font("Garamond", Font.BOLD, 20));
        orderValue.setForeground(Color.BLACK);
        orderPanel.add(orderValue, new GridBagConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfOrderCost = new JTextField();
        tfOrderCost.setFont(new Font("Garamond", Font.BOLD, 20));
        tfOrderCost.setForeground(Color.BLACK);
        tfOrderCost.setColumns(15);
        tfOrderCost.setHorizontalAlignment(JTextField.RIGHT);
        tfOrderCost.setEditable(false);
        tfOrderCost.setText("0.00");
        orderPanel.add(tfOrderCost, new GridBagConstraints(5, 2, 3, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel prePmnt = new JLabel("Amount of prepay");
        prePmnt.setFont(new Font("Garamond", Font.BOLD, 20));
        prePmnt.setForeground(Color.BLACK);
        orderPanel.add(prePmnt, new GridBagConstraints(8, 2, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfPrePmnt = new JTextField();
        tfPrePmnt.setFont(new Font("Garamond", Font.BOLD, 20));
        tfPrePmnt.setForeground(Color.BLACK);
        tfPrePmnt.setColumns(15);
        tfPrePmnt.setHorizontalAlignment(JTextField.RIGHT);
        tfPrePmnt.setEditable(true);
        tfPrePmnt.setText("0.00");
        orderPanel.add(tfPrePmnt, new GridBagConstraints(9, 2, 3, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel date = new JLabel("Date (dd.MM.yyyy): ");
        date.setFont(new Font("Garamond", Font.BOLD, 20));
        date.setForeground(Color.BLACK);
        orderPanel.add(date, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfDate = new JTextField();
        tfDate.setFont(new Font("Garamond", Font.BOLD, 20));
        tfDate.setForeground(Color.BLACK);
        tfDate.setColumns(6);
        tfDate.setHorizontalAlignment(JTextField.RIGHT);
        tfDate.setText(dateFormat(new Date(System.currentTimeMillis())));
        tfDate.setEditable(false);
        orderPanel.add(tfDate, new GridBagConstraints(1, 3, 2, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel visitorID = new JLabel("Visitor's code #: ");
        visitorID.setFont(new Font("Garamond", Font.BOLD, 20));
        visitorID.setForeground(Color.BLACK);
        orderPanel.add(visitorID, new GridBagConstraints(3, 3, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfVID = new JTextField();
        tfVID.setFont(new Font("Garamond", Font.ITALIC, 20));
        tfVID.setForeground(Color.BLACK);
        tfVID.setColumns(12);
        tfVID.setHorizontalAlignment(JTextField.RIGHT);
        tfVID.setText(String.valueOf(shop.getIdbI().getVisitors().size()));
        orderPanel.add(tfVID, new GridBagConstraints(4, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        visitorArea = new JTextArea(1,1);
        visitorArea.setEditable(false);
        visitorArea.setFont(new Font("Garamond", Font.BOLD, 20));
        visitorArea.setForeground(Color.BLACK);
        orderPanel.add(visitorArea, new GridBagConstraints(6, 3, 6, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel orderStatus = new JLabel("Order's status:");
        orderStatus.setFont(new Font("Garamond", Font.BOLD, 20));
        orderStatus.setForeground(Color.BLACK);
        orderPanel.add(orderStatus, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        comboStatus = new JComboBox(new ObjectComboBoxModel<OrderStatus>(statusTypes));
        comboStatus.setFont(new Font("Garamond", Font.BOLD, 20));
        comboStatus.setForeground(Color.BLACK);
        comboStatus.setSelectedItem(OrderStatus.RECIEVED);
        orderPanel.add(comboStatus, new GridBagConstraints(1, 4, 3, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel paymentTypes = new JLabel("Term of payment:");
        paymentTypes.setFont(new Font("Garamond", Font.BOLD, 20));
        paymentTypes.setForeground(Color.BLACK);
        orderPanel.add(paymentTypes, new GridBagConstraints(4, 4, 1, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        comboPaymentType = new JComboBox(new ObjectComboBoxModel<PaymentTermsType>(paymentType));
        comboPaymentType.setFont(new Font("Garamond", Font.BOLD, 20));
        comboPaymentType.setForeground(Color.BLACK);
        comboPaymentType.setSelectedItem(PaymentTermsType.UNPAID);
        orderPanel.add(comboPaymentType, new GridBagConstraints(5, 4, 3, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton confirmBuyer = new JButton("Confirm the Visitor");
        confirmBuyer.setFont(new Font("Garamond", Font.BOLD, 20));
        orderPanel.add(confirmBuyer, new GridBagConstraints(8, 4, 2, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        confirmBuyer.setBorder(empty);
        confirmBuyer.setForeground(Color.BLACK);
        confirmBuyer.setBackground(Color.DARK_GRAY);
        confirmBuyer.addActionListener(new DisplayVisitor(shop, serv, this));

        JLabel choose = new JLabel("Choose the good: ");
        choose.setFont(new Font("Garamond", Font.BOLD, 20));
        choose.setForeground(Color.BLACK);
        orderPanel.add(choose, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        combo = new JComboBox(new ProductComboBoxModel(goods));

        combo.setFont(new Font("Garamond", Font.BOLD, 20));
        combo.setForeground(Color.BLACK);
        orderPanel.add(combo, new GridBagConstraints(1, 5, 10, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JLabel quant = new JLabel("Quantity: ");
        quant.setFont(new Font("Garamond", Font.BOLD, 20));
        quant.setForeground(Color.BLACK);
        orderPanel.add(quant, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        tfQuant = new JTextField();
        tfQuant.setFont(new Font("Garamond", Font.BOLD, 20));
        tfQuant.setForeground(Color.BLACK);
        tfQuant.setText("1");
        tfQuant.setHorizontalAlignment(JTextField.RIGHT);
        orderPanel.add(tfQuant, new GridBagConstraints(1, 6, 3, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton addProduct = new JButton("Add Item to Order");
        addProduct.setFont(new Font("Garamond", Font.BOLD, 20));
        addProduct.setForeground(Color.BLACK);
        addProduct.setBackground(Color.YELLOW);
        orderPanel.add(addProduct, new GridBagConstraints(4, 6, 3, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        addProduct.addActionListener(addITOControl);

        JButton removeLast = new JButton("Remove last item");
        removeLast.setFont(new Font("Garamond", Font.BOLD, 20));
        removeLast.setForeground(Color.BLACK);
        removeLast.setBackground(Color.RED);
        orderPanel.add(removeLast, new GridBagConstraints(8, 6, 3, 1, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        removeLast.addActionListener(removeLIFOControl);

        JLabel checkOrder = new JLabel("PLEASE, CHECK AGAIN ALL THE POSITIONS OF THE ORDER AND PUSH THE ORDER BUTTON SET BELOW");
        checkOrder.setFont(new Font("Garamond", Font.BOLD, 24));
        checkOrder.setForeground(Color.BLACK);
        checkOrder.setBackground(Color.ORANGE);
        orderPanel.add(checkOrder, new GridBagConstraints(0, 7, 15, 2, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));

        JButton formOrder = new JButton("- MAKE ORDER -");
        formOrder.setFont(new Font("Garamond", Font.BOLD, 24));
        formOrder.setForeground(Color.BLACK);
        formOrder.setBackground(Color.GREEN);
        orderPanel.add(formOrder, new GridBagConstraints(0, 9, 4, 2, 0, 0, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0));
        formOrder.addActionListener(ordControl);

        return orderPanel;
    }

    public JPanel getOrderPanel() {
        return orderPanel;
    }

    public String dateFormat(Date d){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(d);
    }

    public JTextField getTfDate() {
        return tfDate;
    }

    public String getDate(){
        return getTfDate().getText();
    }

    public JTextField getTfID() {
        return tfID;
    }

    public String getOrderID(){
        return getTfID().getText();
    }

    public JTextField getTfVID() {
        return tfVID;
    }

    public String getVisitorID(){
        return getTfVID().getText();
    }

    public JComboBox getComboStatus() {
        return comboStatus;
    }

    public OrderStatus getSelectedOrderStatus(){
        return (OrderStatus) getComboStatus().getSelectedItem();
    }

    public JComboBox getComboPaymentType() {
        return comboPaymentType;
    }

    public PaymentTermsType getSelectedPaymentTermType(){
        return (PaymentTermsType) getComboPaymentType().getSelectedItem();
    }

    public JComboBox getCombo() {
        return combo;
    }

    public Water getSelectedWater(){
        return (Water) getCombo().getSelectedItem();
    }

    public JTextField getTfQuant(){return tfQuant;}

    public String getQuantity(){
        return getTfQuant().getText();
    }

    public JTextField getTfOrderCost() {
        return tfOrderCost;
    }

    public void setTextOrderValue(BigDecimal sum){
        getTfOrderCost().setText(String.valueOf(sum));
    }

    public JTextField getTfPrePmnt() {
        return tfPrePmnt;
    }

    public String getPrepayment(){
        return getTfPrePmnt().getText().trim();
    }

    public JTextArea getArea() {
        return area;
    }

    public void setTextArea(String str){
        StringBuilder s = new StringBuilder();
        int j = 1;
        for (int i = 0; i < serv.getOrderItems().size(); i++) {
            s.append(j + ". " + serv.getOrderItems().get(i).productInOrderInfo());
            j++;
        }
        getArea().setText(s.toString());
    }

    public JTextArea getVisitorArea() {
        return visitorArea;
    }

    public void setVisitortAreaText(String str){
        getVisitorArea().setText(str);
    }

    private List<OrderStatus> createStatusTypesList() {
        statusTypes = new ArrayList<>();
        for(OrderStatus obj : OrderStatus.values()){
            statusTypes.add(obj);
        }
        return statusTypes;
    }

    private List<PaymentTermsType> createPaymentTypeList() {
        paymentType = new ArrayList<>();
        for(PaymentTermsType obj : PaymentTermsType.values()){
            paymentType.add(obj);
        }
        return paymentType;
    }
}

