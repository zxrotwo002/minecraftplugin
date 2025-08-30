package com.leohabrom.minecraft.minecraftPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("update2").setExecutor(new UpdateCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
