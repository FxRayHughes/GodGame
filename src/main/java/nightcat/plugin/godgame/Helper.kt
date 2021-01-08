package nightcat.plugin.godgame

import io.izzel.taboolib.Version
import io.izzel.taboolib.module.inject.TInject
import io.izzel.taboolib.module.locale.TLocale
import io.izzel.taboolib.util.lite.cooldown.Cooldown
import nightcat.plugin.godgame.GodGame
import org.bukkit.Effect
import org.bukkit.FluidCollisionMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.command.CommandSender
import org.bukkit.entity.Golem
import org.bukkit.entity.Player

interface Helper {

    val title: String

    fun Player.getTargetBlockExact(): Block? {
        return if (Version.isAfter(Version.v1_13)) {
            this.getTargetBlockExact(10, FluidCollisionMode.NEVER)
        } else {
            this.getTargetBlock(setOf(Material.AIR), 10)
        }
    }

    fun CommandSender.info(value: String) {
        this.sendMessage("$title${value.replace("&", "§")}")
    }

    fun CommandSender.error(value: String) {
        this.sendMessage("§8[§c $title §8] §7${value.replace("&", "§")}")
    }

    fun Player.info(value: String) {
        this.sendMessage("$title${value.replace("&", "§")}")
    }

    fun Player.error(value: String) {
        this.sendMessage("§8[§c $title §8] §7${value.replace("&", "§")}")
    }
}