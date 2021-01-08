package nightcat.plugin.godgame

import io.izzel.taboolib.module.command.base.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.permissions.PermissionDefault

@BaseCommand(name = "karma", permissionDefault = PermissionDefault.OP)
class GodGameCommand : BaseMainCommand(),Helper {

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
            Tools.setIntegral(player,args[1],args[2])
            sender.info("&7目标的 &f${args[1]} &7设置为 &f${args[2]}.")
        }
    }

}