package nightcat.plugin.godgame

import io.izzel.taboolib.module.command.base.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionDefault

@BaseCommand(name = "karma", permissionDefault = PermissionDefault.OP)
class GodGameCommand : BaseMainCommand(), Helper {

    @SubCommand(description = "设置变量")
    var set: BaseSubCommand = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(
                Argument("玩家"),
                Argument("变量类型") { listOf("Character", "Language") },
                Argument("数量")
            )
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.error("§7目标 §f${args[0]} §7离线.")
                return
            }
            val compare = listOf("Character", "Language")
            if (!compare.contains(args[1])) {
                sender.error("&7您输入的参数不符合. 请输入&f‘Character’&7 或 &f‘Language’ &7.")
                return
            }
            Tools.setIntegral(player, args[1], args[2])
            sender.info("&7目标的 &f${args[1]} &7设置为 &f${args[2]}.")
        }
    }

    @SubCommand(description = "查询变量")
    var look: BaseSubCommand = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(
                Argument("玩家"),
                Argument("变量类型") { listOf("Character", "Language") },
                //Argument("数量")
            )
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.error("§7目标 §f${args[0]} §7离线.")
                return
            }
            val compare = listOf("Character", "Language")
            if (!compare.contains(args[1])) {
                sender.error("&7您输入的参数不符合. 请输入&f‘Character’&7 或 &f‘Language’ &7.")
                return
            }
            sender.info("&7目标的 &f${args[1]} &7值为 &f${Tools.getIntegral(player, args[1], "不存在")}")
            //sender.info("&7目标的 &f${args[1]} &7设置为 &f${args[2]}.")
        }
    }

    @SubCommand(description = "扣除变量")
    var take: BaseSubCommand = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(
                Argument("玩家"),
                Argument("变量类型") { listOf("Character", "Language") },
                Argument("数量")
            )
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.error("§7目标 §f${args[0]} §7离线.")
                return
            }
            val compare = listOf("Character", "Language")
            if (!compare.contains(args[1])) {
                sender.error("&7您输入的参数不符合. 请输入&f‘Character’&7 或 &f‘Language’ &7.")
                return
            }
            //sender.info("&7目标的 &f${args[1]} &7值为 &f${Tools.getIntegral(player,args[1],"不存在")}")
            Tools.takeIntegral(player, args[1], args[2].toInt())
        }
    }

    @SubCommand(description = "enable")
    val enable: BaseSubCommand = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(
                Argument("玩家")
            )
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayer(args[0])
            if (player == null) {
                sender.error("&7目标 &f${args[0]} &7离线.")
            }
            Tools.clockMap[player ?: return] = true
            val time = GodGame.settings.getInt("CapturedTime", 200)
            Bukkit.getScheduler().runTaskLater(GodGame.plugin, Runnable {
                if (Tools.clockMap[player] != null){
                    Tools.clockMap.remove(player)
                }
            }, time.toLong())
        }
    }
}