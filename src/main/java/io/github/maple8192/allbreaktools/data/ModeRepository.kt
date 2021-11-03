package io.github.maple8192.allbreaktools.data

import io.github.maple8192.allbreaktools.defines.ToolType
import java.util.*

object ModeRepository {
    private var modes = mutableMapOf<UUID, ModeSet>()

    fun getMode(uuid: UUID, tool: ToolType): Boolean {
        return if (modes.containsKey(uuid)) {
            when (tool) {
                ToolType.PICKAXE -> modes[uuid]!!.pickaxe
                ToolType.AXE -> modes[uuid]!!.axe
                ToolType.SHOVEL -> modes[uuid]!!.shovel
            }
        } else {
            false
        }
    }

    fun switchMode(uuid: UUID, tool: ToolType) {
        if (modes.containsKey(uuid).not()) {
            modes[uuid] = ModeSet()
        }

        when (tool) {
            ToolType.PICKAXE -> modes[uuid]!!.pickaxe = modes[uuid]!!.pickaxe.not()
            ToolType.AXE -> modes[uuid]!!.axe = modes[uuid]!!.axe.not()
            ToolType.SHOVEL -> modes[uuid]!!.shovel = modes[uuid]!!.shovel.not()
        }
    }

    fun getModeMap(): Map<UUID, ModeSet> {
        return modes.toMap()
    }

    fun setModeMap(map: Map<UUID, ModeSet>) {
        modes = map.toMutableMap()
    }
}