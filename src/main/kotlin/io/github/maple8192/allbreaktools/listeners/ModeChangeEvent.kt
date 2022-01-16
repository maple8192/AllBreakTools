package io.github.maple8192.allbreaktools.listeners

import io.github.maple8192.allbreaktools.data.ModeRepository
import io.github.maple8192.allbreaktools.defines.PluginDefine
import io.github.maple8192.allbreaktools.defines.ToolType
import io.github.maple8192.allbreaktools.extensions.isAxe
import io.github.maple8192.allbreaktools.extensions.isPickaxe
import io.github.maple8192.allbreaktools.extensions.isShovel
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class ModeChangeEvent : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action != Action.RIGHT_CLICK_AIR && event.action != Action.RIGHT_CLICK_BLOCK) return
        if (event.hand != EquipmentSlot.HAND) return
        if (event.player.isSneaking.not()) return

        val item = event.player.inventory.itemInMainHand.type

        when {
            item.isPickaxe() -> modeChange(event.player, ToolType.PICKAXE)
            item.isAxe() -> modeChange(event.player, ToolType.AXE)
            item.isShovel() -> modeChange(event.player, ToolType.SHOVEL)
        }
    }

    private fun modeChange(player: Player, tool: ToolType) {
        ModeRepository.switchMode(player.uniqueId, tool)
        player.playSound(player.location, Sound.BLOCK_DISPENSER_FAIL, 1.0f, 1.0f)

        val toolStr = when (tool) {
            ToolType.PICKAXE -> "ツルハシ"
            ToolType.AXE -> "斧"
            ToolType.SHOVEL -> "シャベル"
        }
        val modeStr = if (ModeRepository.getMode(player.uniqueId, tool)) "§a有効" else "§c無効"
        player.sendMessage("${PluginDefine.prefix} §b$toolStr §rの一括破壊を $modeStr §rにしました")
    }
}