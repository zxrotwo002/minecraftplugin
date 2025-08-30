package com.leohabrom.minecraft.minecraftPlugin;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class UpdateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        File pluginFolder = MinecraftPlugin.getPlugin(MinecraftPlugin.class).getDataFolder().getParentFile();
        if (pluginFolder.listFiles() != null) {
            Optional<File> optionalFile = Arrays.stream(Objects.requireNonNull(pluginFolder.listFiles())).filter(file -> file.getName().equals("minecraft-plugin-update.jar")).findFirst();
            if (optionalFile.isPresent()) {
                File pluginJar = optionalFile.get();
                commandSender.sendMessage(pluginJar.getAbsolutePath());
                Bukkit.broadcast(Component.text("Reloading server"));
                Bukkit.getServer().reload();
                return true;
            }
            commandSender.sendMessage("This Plugin is not updatable");
            return true;
        }
        return false;
    }
}
