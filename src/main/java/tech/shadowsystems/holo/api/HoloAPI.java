/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.api;

import org.bukkit.Location;

import java.util.List;

public class HoloAPI {

    private static HoloAPI instance = new HoloAPI();
    private HoloAPI() {}
    public static HoloAPI getInstance() {
        return instance;
    }

    public Hologram createHologram(String name, Location location, List<String> content) {
        return new Hologram(name, location, content);
    }

    public void deleteHologram(String name) {
        // todo
    }

}
