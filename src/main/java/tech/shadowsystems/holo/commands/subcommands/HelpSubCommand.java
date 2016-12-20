/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands.subcommands;

import tech.shadowsystems.holo.commands.SubCommand;
import tech.shadowsystems.holo.api.HoloUser;

public class HelpSubCommand extends SubCommand {

    public void execute(HoloUser user, String[] args) {
        int page;
        if (args.length == 0) {
            // Send page one
            page = 1;
        } else {
            try {
                page = Integer.parseInt(args[0]);
            } catch (Exception ex) {
                // Send page one
                page = 1;
            }
        }

        // Send help via page int
        sendHelp(user, page);
    }

    private void sendHelp(HoloUser user, int page) {
        user.sendMessageWithPrefix("&c&lComing soon! (Requested page " + page + ")");
    }

}
