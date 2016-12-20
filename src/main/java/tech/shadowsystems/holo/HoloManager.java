/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo;

import org.bukkit.entity.ArmorStand;
import tech.shadowsystems.holo.api.Hologram;

import java.util.HashSet;
import java.util.Set;

public class HoloManager {

    private static HoloManager ourInstance = new HoloManager();
    public static HoloManager getInstance() {
        return ourInstance;
    }
    private HoloManager() {
    }

    private Set<Hologram> hologramSet = new HashSet<>();

    public Set<Hologram> getHologramSet() {
        return hologramSet;
    }

    public Hologram search(ArmorStand armorStand) {
        for (Hologram hologram : hologramSet) {
            if (hologram.getPhsyicalEntity() == armorStand) {
                return hologram;
            }
        }

        return null;
    }

    public Hologram search(String string) {
        for (Hologram hologram : hologramSet) {
            if (hologram.getName().equalsIgnoreCase(string)) {
                return hologram;
            }
        }

        return null;
    }

}
