/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import tech.shadowsystems.holo.HoloManager;
import tech.shadowsystems.holo.api.HoloUser;
import tech.shadowsystems.holo.api.Hologram;

public class TouchscreenListener implements Listener {

    @EventHandler
    public void onTouch(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand && event.getRightClicked().getCustomName() != null && !((ArmorStand) event.getRightClicked()).isVisible()) {
            ArmorStand armorStand = (ArmorStand) event.getRightClicked();
            Hologram hologram = HoloManager.getInstance().search(armorStand);
            if (hologram != null) {
                event.setCancelled(true);
                hologram.executeCommands(new HoloUser(event.getPlayer()));
            }
        }
    }

}