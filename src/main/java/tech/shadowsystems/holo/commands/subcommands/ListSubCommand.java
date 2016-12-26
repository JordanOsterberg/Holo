/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands.subcommands;

import tech.shadowsystems.holo.HoloManager;
import tech.shadowsystems.holo.api.HoloUser;
import tech.shadowsystems.holo.api.Hologram;
import tech.shadowsystems.holo.commands.SubCommand;
import tech.shadowsystems.holo.utilties.ChatUtil;

public class ListSubCommand extends SubCommand {

    @Override
    public void execute(HoloUser user, String[] args) {
        if (HoloManager.getInstance().getHologramSet().size() == 0){
            user.sendMessageWithPrefix("&cThere aren't any holograms yet.");
            return;
        }
        for (Hologram hologram : HoloManager.getInstance().getHologramSet()) {
            user.sendMessageWithPrefix("&b" + hologram.getName() + " &a: &f" + ChatUtil.format(hologram.getLocation()));
        }

    }

}
