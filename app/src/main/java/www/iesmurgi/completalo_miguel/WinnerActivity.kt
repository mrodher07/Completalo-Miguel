package com.example.completalo_miguel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        val miBundle = intent.extras
        var clicks = miBundle!!.getInt("clicks")
        var tiempo = miBundle!!.getString("tiempo")

        var tvClick = findViewById(R.id.tvClicks) as TextView
        var textoClicks = tvClick.text.toString()
        tvClick.setText(textoClicks+" "+clicks)

        var tvTiempo = findViewById(R.id.tvTiempo) as TextView
        var textoTiempo = tvTiempo.text.toString()
        tvTiempo.setText(textoTiempo+" "+tiempo)
    }
}