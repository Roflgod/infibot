package com.infibot.client.api.methods;

import com.infibot.client.accessors.Client;
import com.infibot.client.api.Game;
import com.infibot.client.api.Locatable;
import com.infibot.client.api.path.TilePath;
import com.infibot.client.api.util.Calculations;
import com.infibot.client.api.util.Task;
import com.infibot.client.api.wrappers.Tile;

import java.awt.*;

public class Walking {
    /**
     * Sets running to the specified boolean.
     * @param running the state of running
     * @return true if succeeded; otherwise false
     */
    public static boolean setRun(boolean running) {
        if (isRunning() == running) {
            return true;
        }
        return Tab.SETTINGS.open() && Widgets.get(261, 0).click();
    }

    /**
     *
     * @return true if running; otherwise false
     */
    public static boolean isRunning() {
        return Settings.get(173) == 1;
    }

    /**
     * Clicks a Locatable on the minimap.
     * @param locatable the locatable to click
     * @return true if succeeded; otherwise false
     */
    public static boolean clickOnMinimap(Locatable locatable) {
        Point point = Calculations.tileToMinimap(locatable);
        Mouse.move(point.x, point.y);
        Mouse.click(true);
        Tile t = locatable.getLocation();
        long l = System.currentTimeMillis();
        if (t.distanceTo() > 100) { //Tile is -1, -1
            return false;
        }
        while (System.currentTimeMillis() - l < 8000) {
            Task.sleep(250);
            if (t.distanceTo() < 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the current walking destination.
     * @return the walking destination
     */
    public static Tile getDestination() {
        Client client = Game.getClient();
        int x = client.getDestinationX(), y = client.getDestinationY();
        return x != -1 && y != -1 ? new Tile(Game.getBaseX() + x, Game.getBaseY() + y, client.getPlane()) : new Tile(-1, -1);
    }

}
