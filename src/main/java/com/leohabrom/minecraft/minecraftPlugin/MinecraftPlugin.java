package com.leohabrom.minecraft.minecraftPlugin;

import com.leohabrom.minecraft.minecraftPlugin.util.GithubNotifier;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class MinecraftPlugin extends JavaPlugin {

    public static MinecraftPlugin instance;

    public static MinecraftPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getLogger().log(Level.INFO,"Hello from MinecraftPlugin");
        new GithubNotifier();
    }

    @Override
    public void onDisable() {
        instance = null;
        // Plugin shutdown logic
    }
}
