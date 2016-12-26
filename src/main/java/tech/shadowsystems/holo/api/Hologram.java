/*
 * Copyright (c) 2016 Shadow Technical Systems, LLC. Contact ceo@shadowsystems.tech for usage rights.
 */

package tech.shadowsystems.holo.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import tech.shadowsystems.holo.utilties.ChatUtil;
import tech.shadowsystems.holo.utilties.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private String name;
    private List<ArmorStand> physicalEntities;
    private List<String> content;
    private List<String> commands;
    private Location hologramLocation;

    public Hologram(String name, Location location, String content) { // This is for new holograms
        this.name = name;
        this.content = new ArrayList<>();
        this.physicalEntities = new ArrayList<>();
        this.commands = new ArrayList<>();

        this.content.add(content);

        spawn(location);
    }

    public Hologram(String name) { // This is for pre-existing ones
        this.name = name;
        this.physicalEntities = new ArrayList<>();
        this.commands = new ArrayList<>();

        this.commands = FileUtil.getInstance().getDataConfig().getStringList("holograms." + getName() + ".commands");

        this.content = new ArrayList<>();

        for (String key : FileUtil.getInstance().getDataConfig().getConfigurationSection("holograms." + getName() + ".content").getKeys(false)) {
            String contentValue = FileUtil.getInstance().getDataConfig().getString("holograms." + getName() + ".content." + key);
            this.content.add(ChatUtil.format(contentValue));
        }

        double x = FileUtil.getInstance().getDataConfig().getDouble("holograms." + getName() + ".location.x");
        double y = FileUtil.getInstance().getDataConfig().getDouble("holograms." + getName() + ".location.y");
        double z = FileUtil.getInstance().getDataConfig().getDouble("holograms." + getName() + ".location.z");
        float yaw = FileUtil.getInstance().getDataConfig().getInt("holograms." + getName() + ".location.yaw");
        float pitch = FileUtil.getInstance().getDataConfig().getInt("holograms." + getName() + ".location.pitch");
        String worldName = FileUtil.getInstance().getDataConfig().getString("holograms." + getName() + ".location.world");

        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            return;
        }

        Location location = new Location(world, x, y, z, yaw, pitch);
        spawn(location);
    }

    private void spawn(Location location) {
        this.hologramLocation = location;

        int cycle = 0;
        for (String str : content) {
            cycle++;
            if (cycle == 2) {
                location = location.subtract(0, 0.3, 0);
            }
            ArmorStand phsyicalEntity = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            phsyicalEntity.setVisible(false);
            phsyicalEntity.setGravity(false);
            phsyicalEntity.setCustomNameVisible(true);
            phsyicalEntity.setCustomName(ChatUtil.format(str));
            phsyicalEntity.setInvulnerable(true);
            physicalEntities.add(phsyicalEntity);
        }
    }

    public boolean isTouchscreen() {
        return commands != null && commands.size() > 0;
    }

    public List<String> getContent() {
        return content;
    }

    public void executeCommands(HoloUser user) {
        if (!isTouchscreen()) {
            return;
        }

        for (String command : commands) {
            if (user.isPlayer()) {
                boolean status = user.accessPlayer().performCommand("/" + command.replaceAll("%player%", user.accessPlayer().getName()));
                if (!status) {
                    user.sendMessageWithPrefix("&cFailed to run command " + command);
                }
            }
        }
    }

    public String contentAsString() {
        return ChatUtil.format(ChatUtil.convertIntoStringWithNewLines(getContent()));
    }

    public void serialize() {
        Location location = this.hologramLocation;

        /* LOCATION */
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.world", location.getWorld().getName());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.x", location.getX());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.y", location.getY());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.z", location.getZ());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.yaw", location.getYaw());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.pitch", location.getPitch());

        /* LISTS */
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".commands", commands);

        for (int x = 0; x < content.size(); x++) {
            FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".content." + x, String.valueOf(content.get(x)));
        }

        FileUtil.getInstance().saveData();

        for (ArmorStand armorStand : getPhysicalEntities()) {
            armorStand.remove();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArmorStand> getPhysicalEntities() {
        return physicalEntities;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public void remove() {
        for (ArmorStand armorStand : getPhysicalEntities()) {
            armorStand.remove();
        }
    }

    public Location getLocation() {
        return hologramLocation;
    }

    public void setLocation(Location location) {
        this.hologramLocation = location;
        this.remove();
        this.spawn(location);
    }

    public void addLine(String line) {
        content.add(ChatUtil.format(line));
        this.remove();
        this.spawn(this.getLocation());
    }
}
