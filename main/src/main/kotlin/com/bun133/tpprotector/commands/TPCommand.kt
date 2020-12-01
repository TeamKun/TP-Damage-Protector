package com.bun133.tpprotector.commands

import com.bun133.tpprotector.Protector
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class TPCommand(var protector: Protector) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (sender.isOp) {
                if (args.size == 1) {
                    return run(sender, Bukkit.selectEntities(sender, args[0]))
                } else if (args.size == 2) {
                    if (Bukkit.selectEntities(sender, args[0]).isNotEmpty()) {
                        return run(Bukkit.selectEntities(sender, args[1])[0] as Player, Bukkit.selectEntities(sender, args[0]))
                    } else return false
                } else if (args.size == 3) {
                    if(getAsLocation(sender,args[0],args[1],args[2])!=null){
                        sender.teleport(getAsLocation(sender,args[0],args[1],args[2])!!)
                        return true
                    }else return false
                } else if (args.size == 4) {
                    if(getAsLocation(sender,args[1],args[2],args[3])!=null){
                        return run(getAsLocation(sender,args[1],args[2],args[3])!!,Bukkit.selectEntities(sender,args[0]))
                    }else return false
                } else return false
            }
        }
        return false
    }

    private fun run(player: Player, players: MutableList<Entity>): Boolean {
        players.forEach { it.teleport(player) }
        protector.setAll(players, 20)
        return true
    }

    private fun run(loc: Location, players: MutableList<Entity>): Boolean {
        players.forEach { it.teleport(loc) }
        protector.setAll(players, 20)
        return true
    }

    private fun getAsLocation(p:Player,x:String,y:String,z:String):Location?{
        var x_f = getAsFloat(p.location.x,x)
        var y_f = getAsFloat(p.location.y,y)
        var z_f = getAsFloat(p.location.z,z)

        if(x_f == null || y_f == null || z_f == null){
            return null
        }else{
            return Location(p.location.world,x_f,y_f,z_f)
        }
    }

    private fun getAsFloat(p:Double,s:String):Double?{
        if(s.contains('~')){
            var ss:String = s.removePrefix('~'.toString())
            if(ss.toIntOrNull()!=null){
                return p + ss.toInt()
            }
            return null
        }
        if(s.toIntOrNull()!=null){
            return s.toInt().toDouble()
        }
        return null
    }
}