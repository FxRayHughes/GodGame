package nightcat.plugin.godgame

import io.izzel.taboolib.module.command.base.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionDefault
import ray.mintcat.wizard.wizard.WizardObject

@BaseCommand(name = "karma", permissionDefault = PermissionDefault.OP)
class GodGameCommand : BaseMainCommand(), Helper {

    override val title: String
        get() = GodGame.settings.getStringColored("Title", "GoodGame")
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
            WizardObject.setIntegral(player ?: return,"Flag","true")
            val time = GodGame.settings.getInt("CapturedTime", 200)
            Bukkit.getScheduler().runTaskLater(GodGame.plugin, Runnable {
                if (WizardObject.getIntegral(player,"Flag","false").toString() == "false") {
                    WizardObject.setIntegral(player,"Flag","false")
                }
            }, time.toLong())
        }
    }

    @SubCommand(description = "enable全体玩家")
    val enableALL: BaseSubCommand = object : BaseSubCommand() {

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            Bukkit.getOnlinePlayers().forEach { player ->
                WizardObject.setIntegral(player,"Flag","true")
                val time = GodGame.settings.getInt("CapturedTime", 200)
                Bukkit.getScheduler().runTaskLater(GodGame.plugin, Runnable {
                    if (WizardObject.getIntegral(player,"Flag","false").toString() == "false") {
                        WizardObject.setIntegral(player,"Flag","false")
                    }
                }, time.toLong())
            }
        }
    }

    @SubCommand(description = "enable世界内")
    val enableWorld: BaseSubCommand = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("世界名"))
        }
        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {

            val world = Bukkit.getWorld(args[0]) ?: return
            world.players.forEach { player ->
                WizardObject.setIntegral(player,"Flag","true")
                val time = GodGame.settings.getInt("CapturedTime", 200)
                Bukkit.getScheduler().runTaskLater(GodGame.plugin, Runnable {
                    if (WizardObject.getIntegral(player,"Flag","false").toString() == "false") {
                        WizardObject.setIntegral(player,"Flag","false")
                    }
                }, time.toLong())
            }
        }
    }
}