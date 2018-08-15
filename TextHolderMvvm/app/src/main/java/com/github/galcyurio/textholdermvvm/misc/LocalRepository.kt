package com.github.galcyurio.textholdermvvm.misc

import android.content.SharedPreferences
import com.github.galcyurio.textholdermvvm.data.SimpleText

class LocalRepository(
    private val preference: SharedPreferences
) {

    fun save(newSimpleText: SimpleText) {
        preference.edit()
            .putString("title", newSimpleText.title)
            .putString("detail", newSimpleText.detail)
            .apply()
    }

    fun read(): SimpleText {
        return SimpleText(
            title = preference.getString("title", "initial title"),
            detail = preference.getString("detail", "initial detail")
        )
    }
}