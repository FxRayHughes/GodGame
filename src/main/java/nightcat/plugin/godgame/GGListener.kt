package nightcat.plugin.godgame

import io.izzel.taboolib.module.inject.TListener
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.regex.Pattern

@TListener
class GGListener : Listener {

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        if (Tools.clockMap[event.player] != true && !event.message.regex()) {
            return
        }
        val keys = GodGame.settings.getConfigurationSection("CapturedGiveing")?.getKeys(false) ?: return


    }

    private fun String.regex(): Boolean {
        val list = GodGame.settings.getStringList("CapturedWorld")
        list.forEach {
            if (Pattern.compile(it).matcher(this).matches()) {
                return true
            }
        }
        return false
    }
}