package com.leohabrom.minecraft.minecraftPlugin.util;

import com.leohabrom.minecraft.minecraftPlugin.MinecraftPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;

public class GithubNotifier implements Runnable {
    InputStream inputStream;
    public GithubNotifier() {
        Bukkit.broadcast(Component.text("Notifier"));
        try {
            Socket socket = new Socket("creative.leohabrom.com",25065);
            inputStream = socket.getInputStream();
            Bukkit.broadcast(Component.text("IS"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (inputStream == null) {
            Bukkit.broadcast(Component.text("IS null"));
            MinecraftPlugin.getInstance().getLogger().log(Level.INFO,"Couldn't open Github Notifier Stream");
            return;
        }
        try {
            Bukkit.broadcast(Component.text("try"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine())!=null) {
                Bukkit.broadcast(Component.text(line));
            }
        } catch (IOException e) {
            MinecraftPlugin.getInstance().getLogger().log(Level.WARNING,e.getMessage(),e.getCause());
        }
    }
}
