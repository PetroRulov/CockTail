package com.app.view;

import com.app.control.*;
import com.app.model.Shop;
import com.app.util.Service;
import com.app.view.panels.*;
import com.app.view.tables.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;

/**
 * Created by prulov on 14.10.2016.
 */
public class ShopUI {

    private Shop shop;
    private Service serv;
    private JFrame frame;
    private JPanel panel;
    private String head = "* ALCOHOL SHOP - \"COCK TAIL\" - ALCOHOL SHOP *";

    // different panels and tables
    private HeadPanel hp;
    private SalePanelUI spUI;
    private OrderPanelUI ordpUI;
    private AddNewClientPanelUI anclPUI;
    private AddNewVisitorPanelUI anvsPUI;
    private SetAdjustedPeriodPanelUI perPUI;
    private SetAdjustedBuyerPanelUI buyerPUI;
    private OrderToSalePanelUI oTosPUI;
    private AddNewProductPanelUI anpPUI;
    private TableOfSales tsUI;
    private TableOfOrders toUI;
    private TableClient tclUI;
    private TableVisitors tvsUI;
    private TablePriceList tPL;
    private AdjustedByDateTableOfSales tSabyDate;
    private AdjusyedByBuyerTableOfSales tSbyBuyer;

    public ShopUI(Shop shop) {

        this.shop = shop;
        this.serv = new Service(shop);
        this.hp = new HeadPanel(shop);

        this.frame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame(head);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1360, 725));
        frame.setLocation(0, 0);

        frame.setJMenuBar(createMenuBar());

        panel = hp.gethP();
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }


    //create FileMenu
    private JMenuBar createMenuBar(){

        Font bigFont = new Font("Verdana", Font.BOLD, 21);
        Font font = new Font("Verdana", Font.BOLD, 18);
        Font fant = new Font("Verdana", Font.BOLD, 15);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("FILE");
        fileMenu.setFont(bigFont);
        menuBar.add(fileMenu);

        JMenu tables = new JMenu("DATA TABLES");
        tables.setFont(font);
        fileMenu.add(tables);
        fileMenu.addSeparator();

        JMenuItem clientsTable = new JMenuItem("Clients");
        clientsTable.setFont(fant);
        clientsTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        tables.add(clientsTable);
        tables.addSeparator();

        clientsTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clientsTableShow();
            }
        });

        JMenuItem salesTable = new JMenuItem("Sales History");
        salesTable.setFont(fant);
        salesTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        tables.add(salesTable);
        tables.addSeparator();

        salesTable.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                salesTableShow();
            }
        });

        JMenuItem adjustPeriod = new JMenuItem("Adjusted period Sales Table");
        adjustPeriod.setFont(fant);
        adjustPeriod.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        tables.add(adjustPeriod);
        tables.addSeparator();

        adjustPeriod.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                showAdjustPeriodGUI();
            }
        });

        JMenuItem adjustedBuyer = new JMenuItem("Adjusted by Buyer Sales Table");
        adjustedBuyer.setFont(fant);
        adjustedBuyer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
        tables.add(adjustedBuyer);
        tables.addSeparator();

        adjustedBuyer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                showAdjustBuyerGUI();
            }
        });

        JMenuItem priceList = new JMenuItem("Price-List");
        priceList.setFont(fant);
        priceList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
        tables.add(priceList);
        tables.addSeparator();

        priceList.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                priceListShow();
            }
        });

        JMenuItem orders = new JMenuItem("Orders Journal");
        orders.setFont(fant);
        orders.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.ALT_MASK));
        tables.add(orders);
        tables.addSeparator();

        orders.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                ordersJournalShow();
            }
        });

        JMenuItem visitors = new JMenuItem("Visitors");
        visitors.setFont(fant);
        visitors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.ALT_MASK));
        tables.add(visitors);
        tables.addSeparator();

        visitors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                visitorsTableShow();
            }
        });

        JMenu buyMenu = new JMenu("NEW SALE");
        buyMenu.setFont(font);
        fileMenu.add(buyMenu);
        fileMenu.addSeparator();

        buyMenu.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {

                showTransactionGUI();
            }
        });

        JMenu orderMenu = new JMenu("NEW ORDER");
        orderMenu.setFont(font);
        fileMenu.add(orderMenu);
        fileMenu.addSeparator();

        orderMenu.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {

                showOrderGUI();
            }
        });

        JMenu addCustomers = new JMenu("ADD NEW CUSTOMERS");
        addCustomers.setFont(font);
        fileMenu.add(addCustomers);
        fileMenu.addSeparator();

        JMenuItem addClient = new JMenuItem("Add new Client");
        addClient.setFont(fant);
        addCustomers.add(addClient);
        addCustomers.addSeparator();

        addClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showAddingNewClientGUI();
            }
        });

        JMenuItem addVisitor = new JMenuItem("Add new Visitor");
        addVisitor.setFont(fant);
        addCustomers.add(addVisitor);
        addCustomers.addSeparator();

        addVisitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showAddingNewVisitorGUI();
            }
        });

        JMenu operations = new JMenu("ORDER'S OPERATIONS");
        operations.setFont(font);
        fileMenu.add(operations);
        fileMenu.addSeparator();

        JMenuItem changeOrder = new JMenuItem("Set Sale from Order");
        changeOrder.setFont(fant);
        operations.add(changeOrder);
        operations.addSeparator();

        changeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showSaleFromOrderGUI();
            }
        });

        JMenu products = new JMenu("PRODUCT'S OPERATIONS");
        products.setFont(font);
        fileMenu.add(products);
        fileMenu.addSeparator();

        JMenuItem addNewProduct = new JMenuItem("Add new Product");
        addNewProduct.setFont(fant);
        products.add(addNewProduct);
        products.addSeparator();

        addNewProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showAddingNewProductGUI();
            }
        });

        // something else would be here


        JMenuItem exitItem = new JMenuItem("EXIT");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        return menuBar;
    }

    // sales history
    public void salesTableShow(){
        tsUI = new TableOfSales(shop);
        getFrame().getContentPane().removeAll();
        JTable table = tsUI.createSalesTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getFrame().getContentPane().add(scrollPane);
        getFrame().pack();
        getFrame().repaint();
    }

    // order's journal
    public void ordersJournalShow(){
        toUI = new TableOfOrders(shop);
        getFrame().getContentPane().removeAll();
        JTable table = toUI.createOrdersTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getFrame().getContentPane().add(scrollPane);
        getFrame().pack();
        getFrame().repaint();
    }

    // customers
    public void clientsTableShow(){
        tclUI = new TableClient(shop);
        getFrame().getContentPane().removeAll();
        JTable table = tclUI.createClientsTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getFrame().getContentPane().add(scrollPane);
        getFrame().pack();
        getFrame().repaint();
    }

    public void visitorsTableShow(){
        tvsUI = new TableVisitors(shop);
        getFrame().getContentPane().removeAll();
        JTable table = tvsUI.createVisitorsTable() ;
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getFrame().getContentPane().add(scrollPane);
        getFrame().pack();
        getFrame().repaint();
    }

    public void priceListShow(){
        tPL = new TablePriceList(shop);
        getFrame().getContentPane().removeAll();
        JTable table = tPL.createPriceListTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getFrame().getContentPane().add(scrollPane);
        getFrame().pack();
        getFrame().repaint();
    }

    //Price-List
    private void showTransactionGUI(){
        spUI = new SalePanelUI(shop, serv);
        getFrame().getContentPane().removeAll();
        panel = spUI.getSalePanel();
        getFrame().getContentPane().add(panel);
        Observer obs = new SaleControl(shop, spUI, this, serv);
        shop.addObserver(obs);
        getFrame().pack();
        getFrame().repaint();
    }

    // service methods
    private void showOrderGUI(){
        ordpUI = new OrderPanelUI(shop, serv);
        getFrame().getContentPane().removeAll();
        panel = ordpUI.getOrderPanel();
        getFrame().getContentPane().add(panel);
        Observer obs = new OrderControl(shop, ordpUI, this, serv);
        shop.addObserver(obs);
        getFrame().pack();
        getFrame().repaint();
    }

    private void showAddingNewClientGUI(){
        anclPUI = new AddNewClientPanelUI(shop, serv);
        getFrame().getContentPane().removeAll();
        panel = anclPUI.getNcPanel();
        getFrame().getContentPane().add(panel);
        Observer obs = new AddClientControl(shop, anclPUI, this, serv);
        shop.addObserver(obs);
        getFrame().pack();
        getFrame().repaint();
    }

    private void showAddingNewVisitorGUI(){
        anvsPUI = new AddNewVisitorPanelUI(shop, serv);
        getFrame().getContentPane().removeAll();
        panel = anvsPUI.getNvPanel();
        getFrame().getContentPane().add(panel);
        Observer obs = new AddVisitorControl(shop, anvsPUI, this, serv);
        shop.addObserver(obs);
        getFrame().pack();
        getFrame().repaint();
    }

    private void showAddingNewProductGUI(){
        anpPUI = new AddNewProductPanelUI(shop, serv);
        getFrame().getContentPane().removeAll();
        panel = anpPUI.getNPPanel();
        getFrame().getContentPane().add(panel);
        Observer obs = new AddProductControl(shop, anpPUI, this, serv);
        shop.addObserver(obs);
        getFrame().pack();
        getFrame().repaint();
    }

    private void showSaleFromOrderGUI(){
        oTosPUI = new OrderToSalePanelUI(shop, serv);
        getFrame().getContentPane().removeAll();
        panel = oTosPUI.getoToSPanel();
        getFrame().getContentPane().add(panel);
        getFrame().pack();
        getFrame().repaint();
    }

    //util methods for sales history
    private void showAdjustPeriodGUI(){
        perPUI = new SetAdjustedPeriodPanelUI(shop, serv, this);
        getFrame().getContentPane().removeAll();
        panel = perPUI.getPeriodPanel();
        getFrame().getContentPane().add(panel);
        getFrame().pack();
        getFrame().repaint();
    }

    private void showAdjustBuyerGUI(){
        buyerPUI = new SetAdjustedBuyerPanelUI(shop, serv, this);
        getFrame().getContentPane().removeAll();
        panel = buyerPUI.getBuyerPanel();
        getFrame().getContentPane().add(panel);
        getFrame().pack();
        getFrame().repaint();
    }

    public void adjustedByDateSalesTableShow(){
        tSabyDate = new AdjustedByDateTableOfSales(shop, perPUI);
        getFrame().getContentPane().removeAll();
        JTable table = tSabyDate.createAdjustedByDateSalesTable(perPUI.getInitialDate(), perPUI.getFinishDate());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getFrame().getContentPane().add(scrollPane);
        getFrame().pack();
        getFrame().repaint();
    }

    public void adjustedByBuyerSalesTableShow(){
        tSbyBuyer = new AdjusyedByBuyerTableOfSales(shop, buyerPUI);
        getFrame().getContentPane().removeAll();
        JTable table = tSbyBuyer.createAdjustedByBuyerSalesTable(buyerPUI.getBuyersID());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getFrame().getContentPane().add(scrollPane);
        getFrame().pack();
        getFrame().repaint();
    }
}

