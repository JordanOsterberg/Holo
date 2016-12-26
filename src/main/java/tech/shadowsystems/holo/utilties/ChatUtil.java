/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.utilties;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.List;

public class ChatUtil {

    public static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String format(List<String> list) {
        String str = "";
        for (String content : list) {
            if (str.equals("")) {
                str = content;
            } else {
                str = str + ", " + content;
            }
        }
        return str;
    }

    public static String convertIntoStringWithNewLines(List<String> list) {
        String str = "";
        for (String content : list) {
            if (str.equals("")) {
                str = content;
            } else {
                str = str + "\n" + content;
            }
        }
        return str;
    }

    public static String format(Location location) {
        return location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ();
    }
}
