package io.github.maple8192.allbreaktools

import io.github.maple8192.allbreaktools.json.JsonHandler
import io.github.maple8192.allbreaktools.listeners.AllBreakEvent
import io.github.maple8192.allbreaktools.listeners.ModeChangeEvent
import org.bukkit.plugin.java.JavaPlugin

class AllBreakTools : JavaPlugin() {
    override fun onEnable() {
        registerEventListeners()

        JsonHandler.importJsonFile(this)
    }

    override fun onDisable() {
        JsonHandler.exportJsonFile(this)
    }

    private fun registerEventListeners() {
        val pluginManager = server.pluginManager

        pluginManager.registerEvents(ModeChangeEvent(), this)
        pluginManager.registerEvents(AllBreakEvent(), this)
    }
}
