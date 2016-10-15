package com.app;

import com.app.datamanagers.MySQL_DB_Manager;
import com.app.model.Shop;
import com.app.view.ShopUI;
import com.app.view.Splash;

/**
 * Created by prulov on 14.10.2016.
 */
public class MainCockTail {

    public static void main(String[] args) throws InterruptedException {

        new Splash();
        Shop shop = new Shop();
        shop.setDBManager(new MySQL_DB_Manager());
        new ShopUI(shop);

    }
}
