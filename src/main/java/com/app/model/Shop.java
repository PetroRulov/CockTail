package com.app.model;

import com.app.datamanagers.IDBInterface;
import com.app.domain.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by prulov on 14.10.2016.
 */
public class Shop extends Observable {

    private IDBInterface idbI;
    // shop
    private List<Client> clts;
    private List<Sale> sales;
    private List<Order> orders;
    private List<Visitor> visitors;
    //stock
    //private List<Water> waters;
    private List<Product> products;

    public Shop(){
        //this.waters = new ArrayList<>();
        this.products = new ArrayList<>();
        this.clts = new ArrayList<>();
        this.sales = new LinkedList<>();
        this.orders = new LinkedList<>();
        this.visitors = new ArrayList<>();
    }

    public IDBInterface getIdbI() {
        return idbI;
    }

    public void setDBManager(IDBInterface idbI) {
        this.idbI = idbI;
    }

    public List<Client> getClts() {
        if(clts == null || clts.isEmpty()){
            idbI.initClientsBase();
        }
        return new ArrayList<Client>(clts);
    }

    public void setClts(List<Client> clientsBase) {
        this.clts = clientsBase;
    }

    public void addNewProduct(Product product){
        products = idbI.addToStock(product);
        setChanged();
        notifyObservers(product);
    }

    public void addNewClient(Client client){
        clts = idbI.updateClientBase(client);
        setChanged();
        notifyObservers(client);
    }

    public void addNewVisitor(Visitor visitor){
        visitors = idbI.updateVisitorsBase(visitor);
        setChanged();
        notifyObservers(visitor);
    }

//    public List<Water> getWaters(){
//        if(waters == null || waters.isEmpty()){
//            idbI.initStock();
//        }
//        return new ArrayList<Water>(waters);
//    }

//    public void setWaters(List<Water> watersCatalog) {
//        this.waters = watersCatalog;
//    }

    public List<Product> getProducts(){
        if(products == null || products.isEmpty()){
            idbI.fillStock();
        }
        return new ArrayList<Product>(products);
    }

    public void setProducts(List<Product> productsList) {
        this.products = productsList;
    }

    public List<Sale> getSales() {
        if(sales == null){
            idbI.initSalesJournal();
        }
        return new LinkedList<Sale>(sales);
    }

    public void setSales(List<Sale> salesHistory) {
        this.sales = salesHistory;
    }

    public List<Order> getOrders() {
        if(sales == null){
            idbI.initOrdersJournal();
        }
        return new LinkedList<Order>(orders);
    }

    public void setOrders(List<Order> ordersHistory) {
        this.orders = ordersHistory;
    }

    public List<Visitor> getVisitors() {
        if(visitors == null || visitors.isEmpty()){
            idbI.initVisitorsBase();
        }
        return new ArrayList<Visitor>(visitors);
    }

    public void setVisitors(List<Visitor> visitorsBase) {
        this.visitors = visitorsBase;
    }

    public void addSaleTransaction(Sale sale){
        sales = idbI.updateSales(sale);
        setChanged();
        notifyObservers(sale);
    }

    public void addNewOrderInJournal(Order order){
        orders = idbI.updateOrders(order);
        setChanged();
        notifyObservers(order);
    }
}