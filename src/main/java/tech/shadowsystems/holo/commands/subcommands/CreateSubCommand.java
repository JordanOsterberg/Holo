/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands.subcommands;

import tech.shadowsystems.holo.HoloManager;
import tech.shadowsystems.holo.commands.SubCommand;
import tech.shadowsystems.holo.api.HoloUser;
import tech.shadowsystems.holo.api.Hologram;

import java.util.ArrayList;
import java.util.List;

public class CreateSubCommand extends SubCommand {

    @Override
    public void execute(HoloUser user, String[] args) {
        if (!user.isPlayer()) {
            user.sendMessageWithPrefix("&cYou must be a player to execute this command.");
            return;
        }

        if (args.length == 0) {
            user.sendMessageWithPrefix("&c/holo create <name> <content>");
            return;
        }

        String name = args[0];

        if (args.length <= 1) {
            user.sendMessageWithPrefix("&c/holo create <name> <content>");
            return;
        }

        String line = "";
        for (String string : args) {
            if (string.equals(name)) {
                continue;
            }

            if (line.equals("")) {
                line = string;
            } else {
                line = line + " " + string;
            }
        }

        Hologram hologram = new Hologram(name.toLowerCase(), user.accessPlayer().getLocation(), line);
        HoloManager.getInstance().getHologramSet().add(hologram);
        user.sendMessageWithPrefix("&aCreated hologram " + name + " at your location.");
    }

}
