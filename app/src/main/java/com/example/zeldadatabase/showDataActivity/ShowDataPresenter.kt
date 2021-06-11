package com.example.zeldadatabase.showDataActivity

import com.example.zeldadatabase.modelStuff.Model

class ShowDataPresenter (val view: ShowDataView, val model: Model) {

    init {
        view.progressBarVisible = false
    }
}