package com.github.galcyurio.textholdermvvm.misc

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.github.galcyurio.textholdermvvm.data.SimpleText

class LocalRepository(
    private val preference: SharedPreferences
) {
    val simpleText = MutableLiveData<SimpleText>()

    init {
        simpleText.value = read()
    }

    fun save(newSimpleText: SimpleText) {
        this.simpleText.postValue(newSimpleText)
        preference.edit()
            .putString("title", newSimpleText.title)
            .putString("detail", newSimpleText.detail)
            .apply()
    }

    private fun read(): SimpleText {
        return SimpleText(
            title = preference.getString("title", "initial title"),
            detail = preference.getString("detail", "initial detail")
        )
    }
}