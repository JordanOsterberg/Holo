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

import java.util.List;

public class Hologram {

    private String name;
    private ArmorStand phsyicalEntity;
    private List<String> content;
    private List<String> commands;

    public Hologram(String name, Location location, List<String> content) { // This is for new holograms
        this.name = name;
        this.content = content;

        spawn(location);
    }

    public Hologram(String name) { // This is for pre-existing ones
        this.name = name;

        this.commands = FileUtil.getInstance().getDataConfig().getStringList("holograms." + getName() + ".commands");
        this.content = FileUtil.getInstance().getDataConfig().getStringList("holograms." + getName() + ".content");

        double x = FileUtil.getInstance().getDataConfig().getDouble("holograms." + getName() + ".location.x");;
        double y = FileUtil.getInstance().getDataConfig().getDouble("holograms." + getName() + ".location.y");;
        double z = FileUtil.getInstance().getDataConfig().getDouble("holograms." + getName() + ".location.z");;
        float yaw = FileUtil.getInstance().getDataConfig().getInt("holograms." + getName() + ".location.yaw");;
        float pitch = FileUtil.getInstance().getDataConfig().getInt("holograms." + getName() + ".location.pitch");;
        String worldName = FileUtil.getInstance().getDataConfig().getString("holograms." + getName() + ".location.world");;

        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            return;
        }

        Location location = new Location(world, x, y, z, yaw, pitch);
        spawn(location);
    }

    private void spawn(Location location) {
        this.phsyicalEntity = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        this.phsyicalEntity.setVisible(false);
        this.phsyicalEntity.setGravity(false);
        this.phsyicalEntity.setCustomNameVisible(true);
        this.phsyicalEntity.setCustomName(ChatUtil.format(ChatUtil.convertIntoStringWithNewLines(content)));
        this.phsyicalEntity.setInvulnerable(true);
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
                user.accessPlayer().sendMessage("/" + command.replaceAll("%player%", user.accessPlayer().getName()));
            } else {
                user.getCommandSender().sendMessage("/" + command);
            }
        }
    }

    public String contentAsString() {
        return ChatUtil.format(ChatUtil.convertIntoStringWithNewLines(getContent()));
    }

    public void serialize() {
        Location location = this.phsyicalEntity.getLocation();

        /* LOCATION */
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.world", location.getWorld().getName());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.x", location.getX());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.y", location.getY());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.z", location.getZ());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.yaw", location.getYaw());
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".location.pitch", location.getPitch());

        /* LISTS */
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".commands", commands);
        FileUtil.getInstance().getDataConfig().set("holograms." + getName() + ".content", content);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArmorStand getPhsyicalEntity() {
        return phsyicalEntity;
    }

    public void addCommand(String command) {
        commands.add(command);
    }
}
