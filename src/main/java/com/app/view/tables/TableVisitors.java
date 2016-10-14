package com.app.view.tables;

import com.app.domain.Visitor;
import com.app.model.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Created by prulov on 14.10.2016.
 */
public class TableVisitors extends DefaultTableModel {

    private Shop shop;
    private JTable table;

    public TableVisitors(Shop shop){

        this.shop = shop;
        this.table = createVisitorsTable();
        table.setFillsViewportHeight(true);
    }

    public JTable createVisitorsTable() {

        int columns = 8;
        Object[] colNames = fillColumns();
        Object[][] data = fillData();

        JTable tVisitors = new JTable(data, colNames);
        tVisitors.setAutoCreateRowSorter(false);
        TableColumn column = null;
        for(int i = 0; i < columns; i++){
            column = tVisitors.getColumnModel().getColumn(i);
            if(i == 0 || i == 1 || i==4) {
                column.setPreferredWidth(45);
            }else if(i == 3){
                column.setPreferredWidth(240);
            }else if(i == 4){
                column.setPreferredWidth(180);
            }else{
                column.setPreferredWidth(150);
            }
        }
        return tVisitors;
    }

    public JTable getTable() {
        return table;
    }

    private Object[] fillColumns(){

        int columns = 8;
        String[] heads = new String[]{
                "#", "ID", "Identify #", "SurName", "Name", "Tel/fax", "Address",  "E-mail"};
        Object[] colNames = new Object[heads.length];
        for(int i = 0; i < columns; i++){
            colNames[i] = heads[i];
        }
        return colNames;
    }

    private Object[][] fillData(){

        int columns = 8;
        Object[][] data = new Object[shop.getIdbI().getVisitors().size()][columns];
        int j = 1, i = 0;

        for(Visitor visitor : shop.getIdbI().getVisitors()){
            data[i] = new Object[]{
                    j++,
                    visitor.getId_code(),
                    visitor.getIdentify(),
                    visitor.getSurName(),
                    visitor.getName(),
                    visitor.getTelfax(),
                    visitor.getAddress(),
                    visitor.geteMail(),
            };
            i++;
        }
        return data;
    }
}

