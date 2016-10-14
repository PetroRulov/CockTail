package com.app.view.tables;

import com.app.domain.Order;
import com.app.domain.enumerations.OrderStatus;
import com.app.domain.enumerations.PaymentTermsType;
import com.app.model.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Created by prulov on 14.10.2016.
 */
public class TableOfOrders {
    private Shop shop;
    private JTable table;
    private int columns;

    public TableOfOrders(Shop shop){
        this.shop = shop;
        this.columns = 20;
        this.table = createOrdersTable();
        table.setFillsViewportHeight(true);
    }

    public JTable createOrdersTable() {
        Object[] colNames = fillColumns();
        Object[][] data = fillData();

        JTable tabOrders = new JTable(data, colNames);
        tabOrders.setAutoCreateRowSorter(false);
        TableColumn column = null;
        for(int i = 0; i < columns; i++) {
            column = tabOrders.getColumnModel().getColumn(i);
            if (i == 0 || i == 1 || i == 4 || i == 10) {
                column.setPreferredWidth(27);
            }else if(i == 6 || i == 11 || i == 13 || i == 14 || i == 15){
                column.setPreferredWidth(60);
            }else if(i == 2 || i == 6){
                column.setPreferredWidth(90);
            }else if(i == 7 || i == 8 || i == 9 || i == 12) {
                column.setPreferredWidth(120);
            }else if(i == 3){
                column.setCellRenderer(new DefaultTableCellRenderer() {
                    public JComponent getTableCellRendererComponent(JTable table, Object value,
                                                                    boolean isSelected, boolean hasFocus, int row, int col) {
                        JLabel label =
                                (JLabel) super.getTableCellRendererComponent(table, value,
                                        isSelected, hasFocus, row, col);

                        label.setHorizontalAlignment(JLabel.RIGHT);
                        if (value.equals(OrderStatus.COMPLETED)) {
                            label.setBackground(Color.GREEN);
                            label.setForeground(Color.BLACK);
                            label.setFont(new Font("Garamond", Font.BOLD, 16));
                        } else if(value.equals(OrderStatus.SHIPPED)){
                            label.setBackground(Color.RED);
                            label.setForeground(Color.BLUE);
                            label.setFont(new Font("Garamond", Font.BOLD, 16));
                        } else {
                            label.setBackground(Color.WHITE);
                            label.setForeground(Color.BLACK);
                        }
                        return label;
                    }
                });
            }else if(i == 19){
                column.setPreferredWidth(180);
                column.setCellRenderer(new DefaultTableCellRenderer() {
                    public JComponent getTableCellRendererComponent(JTable table, Object value,
                                                                    boolean isSelected, boolean hasFocus, int row, int col) {
                        JLabel label =
                                (JLabel) super.getTableCellRendererComponent(table, value,
                                        isSelected, hasFocus, row, col);

                        label.setHorizontalAlignment(JLabel.RIGHT);
                        if (value.equals(PaymentTermsType.UNPAID)) {
                            label.setBackground(Color.RED);
                            label.setForeground(Color.WHITE);
                            label.setFont(new Font("Garamond", Font.BOLD, 16));
                        } else if(value.equals(PaymentTermsType.PREPAID)){
                            label.setBackground(Color.YELLOW);
                            label.setForeground(Color.BLUE);
                            label.setFont(new Font("Garamond", Font.BOLD, 16));
                        } else {
                            label.setBackground(Color.WHITE);
                            label.setForeground(Color.BLACK);
                        }
                        return label;
                    }
                });
            }
        }
        tabOrders.setGridColor(Color.GREEN);
        return tabOrders;
    }

    public JTable getTable() {
        return table;
    }

    private Object[] fillColumns() {
        String[] heads = new String[]{
                "#", "ID", "Date", "Order's status", "Visitor'sID", "Visitor's surname",
                "Visitor's name", "Visitor's tel/fax", "Visitor's address", "Visitor's e-mail",
                "DrinkID",  "Drink type", "Drink name", "Tare", "Volume, L", "Quantity",
                "Price, UAH", "Prepayment", "Income, UAH", "Payment terms"};
        Object[] colNames = new Object[heads.length];
        for(int i = 0; i < columns; i++){
            colNames[i] = heads[i];
        }
        return colNames;
    }

    private Object[][] fillData() {
        Object[][] data = new Object[shop.getIdbI().getOrders().size()][columns];
        int j = 1, i = 0;

        for(Order order : shop.getIdbI().getOrders()){
            data[i] = new Object[]{
                    j++,
                    order.getId_order(),
                    order.getDate(),
                    order.getoSt(),
                    order.getClient().getId_code(),
                    order.getClient().getSurName(),
                    order.getClient().getName(),
                    order.getClient().getTelfax(),
                    order.getClient().getAddress(),
                    order.getClient().geteMail(),
                    order.getWater().getId_water(),
                    order.getWater().getDrink(),
                    order.getWater().getName(),
                    order.getWater().getTare(),
                    order.getWater().getVolume(),
                    order.getQuantity(),
                    order.getWater().getPrice(),
                    order.getPrepayment(),
                    order.getIncome(),
                    order.getPayTT()
            };
            i++;
        }
        return data;
    }
}

