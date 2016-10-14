package com.app.view.tables;

import com.app.domain.Sale;
import com.app.model.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.math.BigDecimal;

/**
 * Created by prulov on 14.10.2016.
 */
public class TableOfSales extends DefaultTableModel {

    private Shop shop;
    private JTable table;

    public TableOfSales(Shop shop){

        this.shop = shop;
        this.table = createSalesTable();
        table.setFillsViewportHeight(true);
    }

    public JTable createSalesTable(){

        int columns = 16;
        Object[] colNames = fillColumns();
        Object[][] data = fillData();

        JTable tSales = new JTable(data, colNames);
        tSales.setAutoCreateRowSorter(false);
        TableColumn column = null;
        for(int i = 0; i < columns; i++){
            column = tSales.getColumnModel().getColumn(i);
            if(i == 0 || i == 3 || i == 6) {
                column.setPreferredWidth(45);
            }else if(i == 1){
                column.setPreferredWidth(90);
            }else if(i == 8) {
                column.setPreferredWidth(210);
            }else if(i==13){
                column.setCellRenderer(new DefaultTableCellRenderer() {
                    public JComponent getTableCellRendererComponent(JTable table, Object value,
                                                                    boolean isSelected, boolean hasFocus, int row, int col) {
                        JLabel label =
                                (JLabel) super.getTableCellRendererComponent(table, value,
                                        isSelected, hasFocus, row, col);

                        label.setHorizontalAlignment(JLabel.RIGHT);
                        if (((BigDecimal)value).compareTo(new BigDecimal("475")) == 0 ||
                                ((BigDecimal)value).compareTo(new BigDecimal("475")) == 1) {
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
            } else {
                column.setPreferredWidth(120);
            }
        }
        return tSales;
    }

    public JTable getTable() {
        return table;
    }

    private Object[] fillColumns(){

        int columns = 16;
        String[] heads = new String[]{
                "#", "ID", "Date", "BuyerID", "Buyer's surname", "Buyer's name", "DrinkID",  "Drink type", "Drink name",
                "Tare", "Volume, L", "Quantity", "Price, UAH", "Income, UAH", "Way of sale", "Order's ID"};
        Object[] colNames = new Object[heads.length];
        for(int i = 0; i < columns; i++){
            colNames[i] = heads[i];
        }
        return colNames;
    }

    private Object[][] fillData(){

        int columns = 15;
        Object[][] data = new Object[shop.getIdbI().getSales().size()][columns];
        int j = 1, i = 0;

        for(Sale sale : shop.getIdbI().getSales()){
            data[i] = new Object[]{
                    j++,
                    sale.getId_sale(),
                    sale.getDate(),
                    sale.getGuest().getId_client(),
                    sale.getGuest().getSurName(),
                    sale.getGuest().getName(),
                    sale.getWat().getId_water(),
                    sale.getWat().getDrink(),
                    sale.getWat().getName(),
                    sale.getWat().getTare(),
                    sale.getWat().getVolume(),
                    sale.getQuant(),
                    sale.getWat().getPrice(),
                    sale.getIncome(),
                    sale.getWos(),
                    sale.getOrderID()
            };
            i++;
        }
        return data;
    }
}


