package nightcat.plugin.godgame

import io.izzel.taboolib.module.inject.THook
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player

//hook papi
@THook
class PAPIHook : PlaceholderExpansion() {
    override fun getIdentifier(): String {
        return "godgame" // %godgame_%
    }

    //作者
    override fun getAuthor(): String {
        return "枫溪 & ShenYe233"
    }

    //papi模块版本
    override fun getVersion(): String {
        return "1.0.1"
    }

    //持久化标签
    override fun persist(): Boolean {
        return true
    }

    //操作
    override fun onPlaceholderRequest(player: Player, params: String): String {
        return when (params) {
            //%godgame_character%
            "character" -> Tools.getIntegral(player, "Character", "0").toString()
            "language" -> Tools.getIntegral(player, "Language", "Chinese").toString()
            else -> "Null"
        }
    }
}