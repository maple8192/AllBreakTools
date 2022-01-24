package io.github.maple8192.allbreaktools.listeners

import io.github.maple8192.allbreaktools.data.ModeRepository
import io.github.maple8192.allbreaktools.defines.ToolType
import io.github.maple8192.allbreaktools.extensions.*
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ExperienceOrb
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

class AllBreakEvent : Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val item = event.player.inventory.itemInMainHand
        val itemType = item.type
        val blockType = event.block.type
        val loc = event.block.location
        val playerUUID = event.player.uniqueId

        when {
            itemType.isPickaxe() -> if (ModeRepository.getMode(playerUUID, ToolType.PICKAXE) && blockType.isOre() && itemType.getBreakableList().contains(blockType)) breakAll(item, blockType, loc)
            itemType.isAxe() -> if (ModeRepository.getMode(playerUUID, ToolType.AXE) && blockType.isLog()) breakAll(item, blockType, loc)
            itemType.isShovel() -> if (ModeRepository.getMode(playerUUID, ToolType.SHOVEL) && blockType.isClay()) breakAll(item, blockType, loc)
        }
    }

    private fun breakAll(tool: ItemStack, block: Material, location: Location) {
        val willBreakLocations = mutableListOf<Location>().also { it.add(location) }

        for (i in 0..Int.MAX_VALUE) {
            if (i >= willBreakLocations.size) break

            val loc = willBreakLocations[i]
            for (dx in -1..1) {
                for (dy in -1..1) {
                    for (dz in -1..1) {
                        val l = loc.clone().add(dx.toDouble(), dy.toDouble(), dz.toDouble())

                        if (willBreakLocations.contains(l)) continue

                        if (l.block.type == block) willBreakLocations.add(l)
                    }
                }
            }
        }

        if (block.isLog()) {
            for (dx in -3..3) {
                for (dy in -1..1) {
                    for (dz in -3..3) {
                        val l = willBreakLocations[0].clone().add(dx.toDouble(), dy.toDouble(), dz.toDouble())

                        if (l.block.type.isLeaves()) l.block.breakNaturally(tool)
                    }
                }
            }
        }

        willBreakLocations.removeFirst()

        for (loc in willBreakLocations) {
            loc.block.breakNaturally(tool)

            if (block.isOre() && block.getExpOnBroken() != 0) {
                loc.world!!.spawn(loc, ExperienceOrb::class.java).experience += block.getExpOnBroken()
            }
            if (block.isLog()) {
                for (dx in -3..3) {
                    for (dy in -1..1) {
                        for (dz in -3..3) {
                            val l = loc.clone().add(dx.toDouble(), dy.toDouble(), dz.toDouble())

                            if (l.block.type.isLeaves()) l.block.breakNaturally(tool)
                        }
                    }
                }
            }
        }
    }
}