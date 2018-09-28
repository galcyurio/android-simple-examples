package com.github.galcyurio.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.github.galcyurio.model.KittyRepository
import com.github.galcyurio.model.domain.Kitty
import com.github.galcyurio.model.domain.KittyNames
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author galcyurio
 */
class MainViewModel(
    private val kittyRepository: KittyRepository
) : ViewModel() {
    val kitties = MediatorLiveData<List<Kitty>>()

    init {
        createNewKitties()

        kitties.addSource(kittyRepository.getKitties(), kitties::setValue)
    }

    internal fun createNewKitties() {
        val random = Random()
        val period = TimeUnit.SECONDS.toMillis(1)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                val r = random.nextInt(KittyNames.values().size)
                val nameRandom = KittyNames.values()[r].name
                val ageRandom = random.nextInt(5)
                kittyRepository.save(Kitty(nameRandom, ageRandom))
            }
        }, period, period)
    }
}
