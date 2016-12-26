/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands;

import tech.shadowsystems.holo.commands.subcommands.*;

import java.util.HashMap;
import java.util.Map;

public class SubCommandManager {

    private static SubCommandManager ourInstance = new SubCommandManager();
    public static SubCommandManager getInstance() {
        return ourInstance;
    }

    private SubCommandManager() {
        subCommands.put("help", new HelpSubCommand());
        subCommands.put("create", new CreateSubCommand());
        subCommands.put("touch", new TouchSubCommand());
        subCommands.put("remove", new RemoveSubCommand());
        subCommands.put("movehere", new MoveHereSubCommand());
        subCommands.put("goto", new GotoSubCommand());
        subCommands.put("list", new ListSubCommand());
        subCommands.put("addline", new AddLineSubcommand());
    }

    private Map<String, SubCommand> subCommands = new HashMap<>();

    public SubCommand find(String str) {
        return subCommands.get(str);
    }

}
