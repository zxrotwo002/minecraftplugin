package com.leohabrom.minecraft.minecraftPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class MinecraftPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().log(Level.INFO,"Hello from MinecraftPlugin");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
