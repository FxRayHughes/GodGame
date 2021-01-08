package nightcat.plugin.godgame

import io.izzel.taboolib.module.inject.TListener
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

@TListener
class GGListener : Listener {

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent){
       if(!(Tools.clockMap[event.player] == true)) {
           return
       }
        if(event.message == "gg"){

        }
    }

    fun regex(){

    }
}