/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.utilties;

import tech.shadowsystems.holo.Holo;

public class InfoUtil {

    public static String getInfoString() {
        Holo holo = Holo.getInstance();
        return "Holo version " + holo.getDescription().getVersion() + " by " + ChatUtil.format(holo.getDescription().getAuthors());
    }

}
