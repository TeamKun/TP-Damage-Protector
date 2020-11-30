package com.bun133.tpprotector

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.scheduler.BukkitRunnable

class Protector : Listener, BukkitRunnable() {
    var list: MutableMap<Entity, Int> = hashMapOf()

    fun set(p: Entity, ticks: Int) {
        if (list.contains(p)) {
            if (list[p]!! < ticks) {
                list[p] = ticks
            }
        } else list[p] = ticks
    }

    fun setAll(p: MutableList<Entity>, ticks: Int) {
        p.forEach { set(it, ticks) }
    }

    private fun isRemaining(p: Entity): Boolean {
        if (list.containsKey(p)) {
            return list[p]!! > 0
        }
        return false
    }

    fun shrink(ticks: Int) {
        for (key in list.keys) {
            if (list[key] != 0) {
                list[key] = list[key]!! - ticks
            }
        }
    }

    @Override
    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        if (e.cause === EntityDamageEvent.DamageCause.CRAMMING) {
            if (isRemaining(e.entity)) {
                e.isCancelled = true
                if (e.entity is Player) {
                    e.entity.sendMessage("You Protected From Cramming Damage!")
                }
//                Bukkit.broadcastMessage("You Protected From Cramming Damage!")
            }
        }
    }

    override fun run() {
        shrink(1)
    }
}