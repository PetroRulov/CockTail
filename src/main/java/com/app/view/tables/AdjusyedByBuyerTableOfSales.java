package com.app.view.tables;

import com.app.domain.Sale;
import com.app.model.Shop;
import com.app.view.panels.SetAdjustedBuyerPanelUI;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prulov on 14.10.2016.
 */
public class AdjusyedByBuyerTableOfSales {

    private Shop shop;
    private SetAdjustedBuyerPanelUI sabPUI;
    private JTable table;

    public AdjusyedByBuyerTableOfSales(Shop shop, SetAdjustedBuyerPanelUI sabPUI) {

        this.shop = shop;
        this.sabPUI = sabPUI;
        this.table = createAdjustedByBuyerSalesTable(sabPUI.getBuyersID());
        table.setFillsViewportHeight(true);
    }

    public JTable createAdjustedByBuyerSalesTable(int buyersID) {

        int columns = 15;
        Object[] colNames = fillColumns();
        Object[][] data = fillData(buyersID);

        JTable byDateSales = new JTable(data, colNames);
        byDateSales.setAutoCreateRowSorter(false);
        TableColumn column = null;
        for (int i = 0; i < columns; i++) {
            column = byDateSales.getColumnModel().getColumn(i);
            if (i == 0 || i == 2 || i == 5) {
                column.setPreferredWidth(45);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(210);
            } else {
                column.setPreferredWidth(120);
            }
        }
        return byDateSales;
    }

    private Object[] fillColumns() {

        int columns = 15;
        String[] heads = new String[]{
                "#", "Date", "BuyerID", "Buyer's surname", "Buyer's name", "DrinkID", "Drink type", "Drink name", "Tare", "Volume, L", "Quantity",
                "Price, UAH", "Income, UAH", "Way of sale", "Order's ID"};
        Object[] colNames = new Object[heads.length];
        for (int i = 0; i < columns; i++) {
            colNames[i] = heads[i];
        }
        return colNames;
    }

    private Object[][] fillData(int buyersID) {

        int columns = 15;
        Object[][] data = new Object[returnSalesByBuyerList(buyersID).size()][columns];
        int j = 1, i = 0;

        for (Sale sale : returnSalesByBuyerList(buyersID)) {
            data[i] = new Object[]{
                    j++,
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

    private List<Sale> returnSalesByBuyerList(int buyersID) {

        ArrayList<Sale> adjusted = new ArrayList<Sale>();
        for (int i = 0; i < shop.getIdbI().getSales().size(); i++) {
            if (shop.getIdbI().getSales().get(i).getGuest().getId_client() == buyersID) {
                adjusted.add(shop.getIdbI().getSales().get(i));
            }
        }
        return adjusted;
    }

}
