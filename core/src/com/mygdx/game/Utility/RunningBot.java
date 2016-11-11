package com.mygdx.game.Utility;

import com.mygdx.game.Actor.NodeActor;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Created by boon8 on 11/11/2559.
 */
public class RunningBot {

    public void startBot(int team, ArrayList<PlayerData> allData, ArrayList<NodeActor> allNode) {

        File file = new File("../src/com/mygdx/game/BotContainer");

        try {

            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader cl = new URLClassLoader(urls);
            Class cls = cl.loadClass("com.mygdx.game.BotContainer.ChinBot");

            Constructor cons = cls.getConstructor(Integer.TYPE, ArrayList.class, ArrayList.class);
            cons.newInstance(team, allData, allNode);

            Method mtSetActive = cls.getMethod("setActive", Boolean.TYPE);
            mtSetActive.invoke(cls.newInstance(), true);

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
