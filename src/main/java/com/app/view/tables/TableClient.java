package com.app.view.tables;

import com.app.domain.Client;
import com.app.model.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Created by prulov on 14.10.2016.
 */
public class TableClient extends DefaultTableModel {

    private Shop shop;
    private JTable table;

    public TableClient(Shop shop){

        this.shop = shop;
        this.table = createClientsTable();
        table.setFillsViewportHeight(true);
    }

    public JTable createClientsTable() {

        int columns = 7;
        Object[] colNames = fillColumns();
        Object[][] data = fillData();

        JTable tClients = new JTable(data, colNames);
        tClients.setAutoCreateRowSorter(false);
        TableColumn column = null;
        for(int i = 0; i < columns; i++){
            column = tClients.getColumnModel().getColumn(i);
            if(i == 0 || i == 1) {
                column.setPreferredWidth(60);
            }else if(i == 2){
                column.setPreferredWidth(240);
            }else if(i == 3){
                column.setPreferredWidth(180);
            }else{
                column.setPreferredWidth(150);
            }
        }
        return tClients;
    }

    public JTable getTable() {
        return table;
    }

    private Object[] fillColumns(){

        int columns = 7;
        String[] heads = new String[]{
                "#", "Clients ID", "SurName", "Name", "Date of birth", "Sex",  "E-mail"};
        Object[] colNames = new Object[heads.length];
        for(int i = 0; i < columns; i++){
            colNames[i] = heads[i];
        }
        return colNames;
    }

    private Object[][] fillData(){

        int columns = 7;
        Object[][] data = new Object[shop.getIdbI().getClts().size()][columns];
        int j = 1, i = 0;

        for(Client client : shop.getIdbI().getClts()){
            data[i] = new Object[]{
                    j++,
                    client.getId_client(),
                    client.getSurName(),
                    client.getName(),
                    client.getDateOfBirth(),
                    client.getSex(),
                    client.geteMail(),
            };
            i++;
        }
        return data;
    }
}
