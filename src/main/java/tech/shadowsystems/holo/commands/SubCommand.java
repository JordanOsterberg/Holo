/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.commands;

import tech.shadowsystems.holo.api.HoloUser;

public abstract class SubCommand {

    public abstract void execute(HoloUser user, String[] args);

}
