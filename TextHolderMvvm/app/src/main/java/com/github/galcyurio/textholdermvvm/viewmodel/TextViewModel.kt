package com.github.galcyurio.textholdermvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.galcyurio.textholdermvvm.data.SimpleText
import com.github.galcyurio.textholdermvvm.misc.LocalRepository

class TextViewModel(
    private val localRepository: LocalRepository
) : ViewModel() {
    val simpleText: MutableLiveData<SimpleText>
        get() = localRepository.simpleText

    fun save(title: String, detail: String) {
        localRepository.save(SimpleText(title, detail))
    }
}

