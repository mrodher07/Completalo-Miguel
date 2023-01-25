package com.example.completalo_miguel

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Button

@SuppressLint("AppCompatCustomView")
class TileView(context: Context, x:Int, y:Int, topElementos:Int, index:Int, background:Int):Button(context){

    private var coordenadasX:Int = 0
    private var coordenadasY:Int = 0
    private var index:Int = 0
    private var topElementos = 0

    init {
        this.coordenadasX = x
        this.coordenadasY = y
        this.topElementos = topElementos
        this.index = index
        this.setBackgroundResource(background)
    }

    fun getNewIndex(): Int{
        index++
        if(index == topElementos){
            index = 0
        }
        return index
    }
}