/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands.subcommands;

import tech.shadowsystems.holo.HoloManager;
import tech.shadowsystems.holo.api.HoloAPI;
import tech.shadowsystems.holo.api.HoloUser;
import tech.shadowsystems.holo.api.Hologram;
import tech.shadowsystems.holo.commands.SubCommand;
import tech.shadowsystems.holo.utilties.ChatUtil;

public class GotoSubCommand extends SubCommand {

    @Override
    public void execute(HoloUser user, String[] args) {
        if (args.length == 0) {
            user.sendMessageWithPrefix("/holo goto <name>");
            return;
        }

        String name = args[0];
        Hologram hologram = HoloAPI.getInstance().getHologram(name);
        if (hologram == null) {
            user.sendMessageWithPrefix("&cThat hologram doesn't exist!");
            return;
        }

        if (user.isPlayer()) {
            user.accessPlayer().teleport(hologram.getLocation());
        } else {
            user.sendMessageWithPrefix("&cYou must be a player.");
        }
    }

}