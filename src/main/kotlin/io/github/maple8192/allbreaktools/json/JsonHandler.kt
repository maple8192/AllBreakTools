package io.github.maple8192.allbreaktools.json

import com.google.gson.Gson
import io.github.maple8192.allbreaktools.data.ModeRepository
import io.github.maple8192.allbreaktools.data.ModeSet
import org.bukkit.plugin.Plugin
import java.io.*
import java.util.*

object JsonHandler {
    fun exportJsonFile(plugin: Plugin) {
        val file = File(plugin.dataFolder, "data.json")

        if (file.parentFile.exists().not()) {
            file.parentFile.mkdir()
        }
        if (file.exists().not()) {
            file.createNewFile()
        }

        val data = GsonData(mutableListOf<GsonFormattedModeSet>().let {
            ModeRepository.getModeMap().forEach { entry ->
                it.add(GsonFormattedModeSet(entry.key.toString(), entry.value.pickaxe, entry.value.axe, entry.value.shovel))
            }

            it.toList()
        })

        try {
            OutputStreamWriter(FileOutputStream(file), "UTF-8").use { Gson().toJson(data, it) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun importJsonFile(plugin: Plugin) {
        val file = File(plugin.dataFolder, "data.json")

        if (file.exists().not()) {
            return
        }

        lateinit var data: GsonData

        try {
            InputStreamReader(FileInputStream(file), "UTF-8").use { data = Gson().fromJson(it, GsonData::class.java) }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        ModeRepository.setModeMap(mutableMapOf<UUID, ModeSet>().let {
            data.modes.forEach { item ->
                it[UUID.fromString(item.uuid)] = ModeSet(item.pickaxe, item.axe, item.shovel)
            }

            it.toMap()
        })
    }
}