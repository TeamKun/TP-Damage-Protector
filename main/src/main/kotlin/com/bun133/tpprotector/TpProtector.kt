package com.bun133.tpprotector

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.plugin.java.JavaPlugin

class TpProtector : JavaPlugin(), Listener {
    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(this, this)
    }

    @Override
    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        if (e.cause === EntityDamageEvent.DamageCause.CRAMMING)
            if (e.entity is Player)
                e.isCancelled = true
    }
}