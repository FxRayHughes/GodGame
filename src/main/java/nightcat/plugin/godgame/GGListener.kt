package nightcat.plugin.godgame

import io.izzel.taboolib.TabooLibAPI
import io.izzel.taboolib.module.inject.TListener
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import ray.mintcat.wizard.wizard.WizardObject
import java.util.regex.Pattern

@TListener
class GGListener : Listener, Helper {

    override val title: String
        get() = GodGame.settings.getStringColored("Title", "GoodGame")

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        if (WizardObject.getIntegral(event.player,"Flag","false").toString() == "true" && event.message.regex()) {
            val keys = GodGame.settings.getConfigurationSection("CapturedGiveing")?.getKeys(false) ?: return
            //判断权限
            keys.forEach {
                //读取权限
                val power = GodGame.settings.getString("CapturedGiveing.${it}.perms", "none") ?: "none"
                //判断权限哪里是不是写的none 或者是不存在
                if (power == "none" || event.player.hasPermission(power)) {
                    //读取此节点下的数值
                    val value = GodGame.settings.getInt("CapturedGiveing.${it}.giving", 0)
                    //添加
                    WizardObject.addIntegral(event.player, "Character", value)
                    //获取语言
                    val lang = WizardObject.getIntegral(event.player, "Language", "Chinese")
                    //获取内容
                    val message =
                        GodGame.settings.getStringColored("CapturedGiveing.${it}.message.$lang").papi(event.player)
                    //发送消息
                    event.player.info(message)
                    //结束判断 并删除玩家的flag
                    WizardObject.setIntegral(event.player,"Flag","false")
                    return
                }
            }
        }
    }

    private fun String.regex(): Boolean {
        val list = GodGame.settings.getStringList("CapturedWorld")
        list.forEach {
            if (this.equals(it,true)) {
                return true
            }
        }
        return false
    }

    fun String.papi(player: Player): String {
        return TabooLibAPI.getPluginBridge().setPlaceholders(player, this)
    }
}