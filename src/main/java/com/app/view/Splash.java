package com.app.view;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by prulov on 14.10.2016.
 */
public class Splash {

    public Splash() throws InterruptedException {

        int y = 265;

        SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.err.println("SplashScreen.getSplashScreen() returned null");
            return;
        }

        Thread.sleep(2000);

        Graphics2D g = splash.createGraphics();
        g.setColor(Color.BLUE);
        g.setFont(new Font("Garamond", Font.ITALIC, 18));
        g.drawString("\"COCK TAIL\" ALCOHOL SHOP", 75, 20);
        g.setColor(Color.GREEN);
        g.drawString("LOADING...", 15, 265);
        String s = "_";
        for(int i = 0; i < 7; i++){
            g.drawString(s, 15, y + 4);
            s+=s;
            splash.update();
            Thread.sleep(1000);
        }
        splash.close();


    }

}

