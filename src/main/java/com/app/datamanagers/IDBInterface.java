package com.app.datamanagers;

import com.app.domain.*;

import java.util.List;

/**
 * Created by prulov on 14.10.2016.
 */
public interface IDBInterface {

    //shop initiation
    List<Product> fillStock();
    List<Client> initClientsBase();
    List<Sale> initSalesJournal();
    List<Visitor> initVisitorsBase();
    List<Order> initOrdersJournal();
    List<Product> getProducts();

    List<Client> getClts();

    List<Sale> getSales();

    List<Visitor> getVisitors();

    List<Order> getOrders();


    // sale transaction
    List<Sale> updateSales(Sale sale);
    List<Order> updateOrders(Order order);
    void soldWaterMinus(Product product, int count);

    // add new Client
    List<Client> updateClientBase(Client client);
    List<Visitor> updateVisitorsBase(Visitor visitor);

    // add new Product
    List<Product> addToStock(Product product);
    List<Product> updateStock();

}