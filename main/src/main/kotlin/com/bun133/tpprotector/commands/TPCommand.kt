package com.bun133.tpprotector.commands

import com.bun133.tpprotector.Protector
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class TPCommand (var protector: Protector): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player){
            if(sender.isOp){
                if(args.size==1){
                    return run(sender,Bukkit.selectEntities(sender,args[0]))
                }
            }
        }
        // Maybe Throw To Legacy TP Command
        return false
    }

    private fun run(player:Player, players:MutableList<Entity>):Boolean{
        players.forEach {it.teleport(player) }
        protector.setAll(players,20)
        return true
    }
}