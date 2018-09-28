package com.github.galcyurio.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.github.galcyurio.model.DoggyRepository
import com.github.galcyurio.model.KittyRepository
import com.github.galcyurio.model.domain.Doggy
import com.github.galcyurio.model.domain.DoggyNames
import com.github.galcyurio.model.domain.Kitty
import com.github.galcyurio.model.domain.KittyNames
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author galcyurio
 */
class MainViewModel(
    private val kittyRepository: KittyRepository,
    private val doggyRepository: DoggyRepository
) : ViewModel() {
    val kitties = MediatorLiveData<List<Kitty>>()

    init {
        createNewKitties()
        createNewDoggies()

        // TODO: Doggy 추가하기
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

    internal fun createNewDoggies() {
        val random = Random()
        val period = TimeUnit.SECONDS.toMillis(1)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                val r = random.nextInt(DoggyNames.values().size)
                val nameRandom = DoggyNames.values()[r].name
                val ageRandom = random.nextInt(20)
                doggyRepository.save(Doggy(nameRandom, ageRandom))
            }
        }, period, period)
    }
}
