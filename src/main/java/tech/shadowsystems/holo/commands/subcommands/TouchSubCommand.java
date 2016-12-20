/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands.subcommands;

import tech.shadowsystems.holo.HoloManager;
import tech.shadowsystems.holo.api.HoloUser;
import tech.shadowsystems.holo.api.Hologram;
import tech.shadowsystems.holo.commands.SubCommand;

public class TouchSubCommand extends SubCommand {

    @Override
    public void execute(HoloUser user, String[] args) {
        if (args.length == 0) {
            user.sendMessageWithPrefix("&c/holo touch <name> [action]");
            return;
        }

        String name = args[0];

        Hologram hologram = HoloManager.getInstance().search(name);
        if (hologram == null) {
            user.sendMessageWithPrefix("&c/holo touch <name> [action]");
            return;
        }

        if (args.length <= 1) {
            user.sendMessageWithPrefix("&c/holo touch <name> [action]");
            return;
        }

        String command = "";
        for (String string : args) {
            if (string.equals(name)) {
                continue;
            }

            if (command.equals("")) {
                command = string;
            } else {
                command = command + " " + string;
            }
        }

        hologram.addCommand(command);
        user.sendMessageWithPrefix("&cAdded command /" + command + " to hologram " + hologram.getName());
    }

}
