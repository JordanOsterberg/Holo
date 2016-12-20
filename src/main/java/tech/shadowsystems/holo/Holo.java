/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo;

import org.bukkit.plugin.java.JavaPlugin;
import tech.shadowsystems.holo.api.Hologram;
import tech.shadowsystems.holo.commands.HoloCommand;
import tech.shadowsystems.holo.listeners.TouchscreenListener;
import tech.shadowsystems.holo.utilties.FileUtil;

public class Holo extends JavaPlugin {

    private static Holo instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("holo").setExecutor(new HoloCommand());
        getServer().getPluginManager().registerEvents(new TouchscreenListener(), this);

        for (String hologramName : FileUtil.getInstance().getDataConfig().getConfigurationSection("holograms").getKeys(false)) {
            Hologram hologram = new Hologram(hologramName);
            HoloManager.getInstance().getHologramSet().add(hologram);
        }
    }

    @Override
    public void onDisable() {
        FileUtil.getInstance().getDataConfig().set("holograms", null);

        for (Hologram hologram : HoloManager.getInstance().getHologramSet()) {
            hologram.serialize();
        }

        instance = null;
    }

    public static Holo getInstance() {
        return instance;
    }
}
