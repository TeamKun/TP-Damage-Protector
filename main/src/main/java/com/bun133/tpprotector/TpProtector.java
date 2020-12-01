package com.bun133.tpprotector;

import com.bun133.tpprotector.commands.TPCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class TpProtector extends JavaPlugin {
    Protector protector;
    TPCommand command;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.protector = new Protector();
        this.getServer().getPluginManager().registerEvents(protector,this);
        this.command = new TPCommand(protector);
        this.getCommand("tp").setExecutor(command);
        this.getServer().getScheduler().runTaskTimer(this,protector,1,1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
