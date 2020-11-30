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
                }else if(args.size==2){
                    if(Bukkit.selectEntities(sender,args[0]).isNotEmpty()){
                        return run(Bukkit.selectEntities(sender,args[0])[0] as Player,Bukkit.selectEntities(sender,args[1]))
                    }else return false
                }else return false
            }
        }
        return false
    }

    private fun run(player:Player, players:MutableList<Entity>):Boolean{
        players.forEach {it.teleport(player) }
        protector.setAll(players,20)
        return true
    }
}