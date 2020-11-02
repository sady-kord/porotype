package com.demo.sample.controls

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.demo.sample.R
import kotlinx.android.synthetic.main.toolbar.view.*

class Toolbar : ConstraintLayout {

    constructor(context: Context) : super(context) {
        initialize(context)
    }

    constructor(context: Context , attrs: AttributeSet?) : super(context , attrs) {
        initialize(context)
    }

    constructor(context: Context , attrs: AttributeSet , defStyleAttr : Int)
            : super(context , attrs , defStyleAttr) {
        initialize(context)
    }

    private fun initialize(context: Context){
        var inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.toolbar ,this , true)

        val elevation = resources.getDimensionPixelSize(R.dimen.toolbar_elevation).toFloat()
        ViewCompat.setElevation(this, elevation)
    }

    fun setTitle(title : String){
        text_title.text = title
    }
}