package com.leohabrom.minecraft.minecraftPlugin;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class UpdateCommand implements CommandExecutor {
    String downloadLink = "https://github.com/zxrotwo002/minecraftplugin/releases/download/latest/minecraft-plugin-update.jar";
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        File pluginFolder = MinecraftPlugin.getPlugin(MinecraftPlugin.class).getDataFolder().getParentFile();
        if (pluginFolder.listFiles() != null) {
            Optional<File> optionalFile = Arrays.stream(Objects.requireNonNull(pluginFolder.listFiles())).filter(file -> file.getName().equals("minecraft-plugin-update.jar")).findFirst();
            if (optionalFile.isPresent()) {
                File pluginJar = optionalFile.get();
                try {
                    commandSender.sendMessage("Creating backup");
                    Files.copy(pluginJar.toPath(), new File(pluginFolder,"backup").toPath(), StandardCopyOption.REPLACE_EXISTING);
                    commandSender.sendMessage("Backup complete");
                } catch (IOException e) {
                    commandSender.sendMessage("Failed creating backup, aborting");
                    return false;
                }
                try {
                    commandSender.sendMessage("Replacing old plugin-jar");
                    InputStream in = new URL(downloadLink).openStream();
                    Files.copy(in, pluginJar.toPath(),StandardCopyOption.REPLACE_EXISTING);
                    commandSender.sendMessage("Replacing complete");
                } catch (IOException e) {
                    commandSender.sendMessage("Error occurred, restoring backup");
                    try {
                        Files.copy(new File(pluginFolder,"backup").toPath(),pluginJar.toPath(),StandardCopyOption.REPLACE_EXISTING);
                        commandSender.sendMessage("Backup restored");
                    } catch (IOException ex) {
                        commandSender.sendMessage("Everything failed, your plugin is now corrupt");
                        commandSender.sendMessage("Please restore manually");
                        return false;
                    }
                }
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
