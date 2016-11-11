package com.mygdx.game.Utility;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Created by boon8 on 11/11/2559.
 */
public class RunningBot {

    public void testFile() {

        System.out.println("Passed0");
        File file = new File("../src/com/mygdx/game/BotContainer");

        try {

            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader cl = new URLClassLoader(urls);
            Class cls = cl.loadClass("com.mygdx.game.BotContainer.ChinBot");

            Constructor cons = cls.getConstructor(Integer.TYPE, ArrayList.class, ArrayList.class);
            cons.newInstance(1, null, null);

            Bot running = (Bot) cls.newInstance();
            new Thread(running).start();

        } catch (MalformedURLException e) {
        } catch (ClassNotFoundException e) {
        } catch (NullPointerException e) {
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        }

    }

}
