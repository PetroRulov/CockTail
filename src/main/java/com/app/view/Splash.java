package com.app.view;

import java.awt.*;

/**
 * Created by prulov on 14.10.2016.
 */
public class Splash {

    public Splash() throws InterruptedException{

        int y = 265;
        SplashScreen splash = SplashScreen.getSplashScreen();
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

