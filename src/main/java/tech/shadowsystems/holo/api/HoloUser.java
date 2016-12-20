/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.shadowsystems.holo.utilties.ChatUtil;

public class HoloUser {

    private CommandSender commandSender;

    public HoloUser(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public CommandSender getCommandSender() {
        return commandSender;
    }

    public void sendMessage(String message) {
        getCommandSender().sendMessage(ChatUtil.format(message));
    }

    public void sendMessageWithPrefix(String message) {
//        sendMessage("&b>&9> &3&lHolo &9<&b< &7: &f" + message);
        sendMessage("&3&lHolo &b>&9> &f" + message);
    }

    public boolean isPlayer() {
        return commandSender instanceof Player;
    }

    public Player accessPlayer() {
        return (Player) commandSender;
    }

}
