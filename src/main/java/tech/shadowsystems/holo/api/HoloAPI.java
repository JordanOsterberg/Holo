/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.api;

import org.bukkit.Location;
import tech.shadowsystems.holo.HoloManager;
import tech.shadowsystems.holo.utilties.FileUtil;

import java.util.List;

public class HoloAPI {

    private static HoloAPI instance = new HoloAPI();
    private HoloAPI() {}
    public static HoloAPI getInstance() {
        return instance;
    }

    public Hologram createHologram(String name, Location location, String content) {
        return new Hologram(name, location, content);
    }

    public void deleteHologram(String name) {
        Hologram hologram = HoloManager.getInstance().search(name);
        if (hologram != null) {
            HoloManager.getInstance().getHologramSet().remove(hologram);
            FileUtil.getInstance().getDataConfig().set("holograms." + name.toLowerCase(), null);
            FileUtil.getInstance().saveData();
            hologram.remove();
        }
    }

    public Hologram getHologram(String name) {
        return HoloManager.getInstance().search(name);
    }

}
