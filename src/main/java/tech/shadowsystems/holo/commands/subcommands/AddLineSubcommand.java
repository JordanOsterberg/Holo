/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands.subcommands;

import tech.shadowsystems.holo.api.HoloAPI;
import tech.shadowsystems.holo.api.HoloUser;
import tech.shadowsystems.holo.api.Hologram;
import tech.shadowsystems.holo.commands.SubCommand;

public class AddLineSubcommand extends SubCommand {

    @Override
    public void execute(HoloUser user, String[] args) {
        if (args.length == 0) {
            user.sendMessageWithPrefix("/holo addline <name> <content>");
            return;
        }

        String name = args[0];
        Hologram hologram = HoloAPI.getInstance().getHologram(name);
        if (hologram == null) {
            user.sendMessageWithPrefix("&cThat hologram doesn't exist!");
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

        hologram.addLine(line);
        user.sendMessageWithPrefix("&aAdded line to " + name + ": &f" + line);
    }

}