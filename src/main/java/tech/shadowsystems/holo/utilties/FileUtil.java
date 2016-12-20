/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.utilties;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import tech.shadowsystems.holo.Holo;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    private static FileUtil ourInstance = new FileUtil();
    public static FileUtil getInstance() {
        return ourInstance;
    }

    private File dataFile;
    private FileConfiguration dataConfig;

    private FileUtil() {
        this.dataFile = new File(Holo.getInstance().getDataFolder(), "data.yml");

        if (!this.dataFile.exists()) {
            try {
                this.dataFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.dataFile);
    }

    public FileConfiguration getDataConfig() {
        return dataConfig;
    }

    public void saveData() {
        try {
            this.dataConfig.save(this.dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
