/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands.subcommands;

import tech.shadowsystems.holo.HoloManager;
import tech.shadowsystems.holo.api.HoloUser;
import tech.shadowsystems.holo.api.Hologram;
import tech.shadowsystems.holo.commands.SubCommand;

public class RemoveSubCommand extends SubCommand {

    public void execute(HoloUser user, String[] args) {
        if (args.length == 0) {
            user.sendMessageWithPrefix("&c/holo remove <name>");
            return;
        }

        Hologram hologram = HoloManager.getInstance().search(args[0]);
        if (hologram != null) {
            HoloManager.getInstance().getHologramSet().remove(hologram);
            hologram.getPhsyicalEntity().remove();
            user.sendMessageWithPrefix("&aHologram " + args[0] + " deleted.");
        } else {
            user.sendMessageWithPrefix("&cThat hologram doesn't exist.");
        }
    }

}
