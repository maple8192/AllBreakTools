package io.github.maple8192.allbreaktools.extensions

import org.bukkit.Material
import kotlin.random.Random

fun Material.isPickaxe(): Boolean {
    if (this == Material.WOODEN_PICKAXE) return true
    if (this == Material.STONE_PICKAXE) return true
    if (this == Material.IRON_PICKAXE) return true
    if (this == Material.GOLDEN_PICKAXE) return true
    if (this == Material.DIAMOND_PICKAXE) return true
    if (this == Material.NETHERITE_PICKAXE) return true

    return false
}

fun Material.isAxe(): Boolean {
    if (this == Material.WOODEN_AXE) return true
    if (this == Material.STONE_AXE) return true
    if (this == Material.IRON_AXE) return true
    if (this == Material.GOLDEN_AXE) return true
    if (this == Material.DIAMOND_AXE) return true
    if (this == Material.NETHERITE_AXE) return true

    return false
}

fun Material.isShovel(): Boolean {
    if (this == Material.WOODEN_SHOVEL) return true
    if (this == Material.STONE_SHOVEL) return true
    if (this == Material.IRON_SHOVEL) return true
    if (this == Material.GOLDEN_SHOVEL) return true
    if (this == Material.DIAMOND_SHOVEL) return true
    if (this == Material.NETHERITE_SHOVEL) return true

    return false
}

fun Material.isOre(): Boolean {
    if (this == Material.COAL_ORE) return true
    if (this == Material.DEEPSLATE_COAL_ORE) return true
    if (this == Material.IRON_ORE) return true
    if (this == Material.DEEPSLATE_IRON_ORE) return true
    if (this == Material.COPPER_ORE) return true
    if (this == Material.DEEPSLATE_COPPER_ORE) return true
    if (this == Material.GOLD_ORE) return true
    if (this == Material.DEEPSLATE_GOLD_ORE) return true
    if (this == Material.REDSTONE_ORE) return true
    if (this == Material.DEEPSLATE_REDSTONE_ORE) return true
    if (this == Material.EMERALD_ORE) return true
    if (this == Material.DEEPSLATE_EMERALD_ORE) return true
    if (this == Material.LAPIS_ORE) return true
    if (this == Material.DEEPSLATE_LAPIS_ORE) return true
    if (this == Material.DIAMOND_ORE) return true
    if (this == Material.DEEPSLATE_DIAMOND_ORE) return true
    if (this == Material.NETHER_GOLD_ORE) return true
    if (this == Material.NETHER_QUARTZ_ORE) return true
    if (this == Material.GLOWSTONE) return true
    if (this == Material.OBSIDIAN) return true

    return false
}

fun Material.isLog(): Boolean {
    if (this == Material.OAK_LOG) return true
    if (this == Material.SPRUCE_LOG) return true
    if (this == Material.BIRCH_LOG) return true
    if (this == Material.JUNGLE_LOG) return true
    if (this == Material.ACACIA_LOG) return true
    if (this == Material.DARK_OAK_LOG) return true

    return false
}

fun Material.isClay(): Boolean {
    if (this == Material.CLAY) return true

    return false
}

fun Material.isLeaves(): Boolean {
    if (this == Material.OAK_LEAVES) return true
    if (this == Material.SPRUCE_LEAVES) return true
    if (this == Material.BIRCH_LEAVES) return true
    if (this == Material.JUNGLE_LEAVES) return true
    if (this == Material.ACACIA_LEAVES) return true
    if (this == Material.DARK_OAK_LEAVES) return true

    return false
}

fun Material.getBreakableList(): List<Material> {
    return when (this) {
        Material.WOODEN_PICKAXE, Material.GOLDEN_PICKAXE -> listOf(
            Material.COAL_ORE,
            Material.DEEPSLATE_COAL_ORE,
            Material.NETHER_GOLD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.GLOWSTONE
        )
        Material.STONE_PICKAXE -> listOf(
            Material.COAL_ORE,
            Material.DEEPSLATE_COAL_ORE,
            Material.IRON_ORE,
            Material.DEEPSLATE_IRON_ORE,
            Material.COPPER_ORE,
            Material.DEEPSLATE_COPPER_ORE,
            Material.LAPIS_ORE,
            Material.DEEPSLATE_LAPIS_ORE,
            Material.NETHER_GOLD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.GLOWSTONE
        )
        Material.IRON_PICKAXE -> listOf(
            Material.COAL_ORE,
            Material.DEEPSLATE_COAL_ORE,
            Material.IRON_ORE,
            Material.DEEPSLATE_IRON_ORE,
            Material.COPPER_ORE,
            Material.DEEPSLATE_COPPER_ORE,
            Material.GOLD_ORE,
            Material.DEEPSLATE_GOLD_ORE,
            Material.REDSTONE_ORE,
            Material.DEEPSLATE_REDSTONE_ORE,
            Material.EMERALD_ORE,
            Material.DEEPSLATE_EMERALD_ORE,
            Material.LAPIS_ORE,
            Material.DEEPSLATE_LAPIS_ORE,
            Material.DIAMOND_ORE,
            Material.DEEPSLATE_DIAMOND_ORE,
            Material.NETHER_GOLD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.GLOWSTONE
        )
        Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE -> listOf(
            Material.COAL_ORE,
            Material.DEEPSLATE_COAL_ORE,
            Material.IRON_ORE,
            Material.DEEPSLATE_IRON_ORE,
            Material.COPPER_ORE,
            Material.DEEPSLATE_COPPER_ORE,
            Material.GOLD_ORE,
            Material.DEEPSLATE_GOLD_ORE,
            Material.REDSTONE_ORE,
            Material.DEEPSLATE_REDSTONE_ORE,
            Material.EMERALD_ORE,
            Material.DEEPSLATE_EMERALD_ORE,
            Material.LAPIS_ORE,
            Material.DEEPSLATE_LAPIS_ORE,
            Material.DIAMOND_ORE,
            Material.DEEPSLATE_DIAMOND_ORE,
            Material.NETHER_GOLD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.ANCIENT_DEBRIS,
            Material.GLOWSTONE,
            Material.OBSIDIAN
        )
        else -> listOf()
    }
}

fun Material.getExpOnBroken(): Int {
    return when (this) {
        Material.NETHER_GOLD_ORE -> Random.nextInt(0, 2)
        Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE -> Random.nextInt(0, 3)
        Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE -> Random.nextInt(1, 6)
        Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.NETHER_QUARTZ_ORE -> Random.nextInt(2, 6)
        Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE -> Random.nextInt(3, 8)
        else -> 0
    }
}
