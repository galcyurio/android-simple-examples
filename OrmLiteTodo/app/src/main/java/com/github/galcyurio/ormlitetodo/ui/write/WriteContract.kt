package com.github.galcyurio.ormlitetodo.ui.write

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface WriteContract {
    interface View : MvpView {
        fun showTitleEmptyMessage()
        fun showErrorMessage()
        fun close()
    }

    interface Presenter : MvpPresenter<View> {
        fun write(title: String, description: String?)
    }
}