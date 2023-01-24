package com.example.completalo_miguel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.widget.SeekBar
import android.widget.TextView
import com.example.completalo_miguel.databinding.ActivityMainBinding
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progresoX: TextView
    private lateinit var progresoY: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sbNumeroColumnas.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                updateXTiles(seekBar?.getProgress()!!)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.sbNumeroFilas.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                updateYTiles(seekBar?.getProgress()!!)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


    }
    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }*/

    fun updateXTiles(progreso:Int) {
        progresoX = binding.tvColumnas
        progresoX.setText("Numero de Columnas : " + progreso)
    }

    fun updateYTiles(progreso:Int) {
        progresoY = binding.tvFilas
        progresoY.setText("Numero de Filas : " + progreso)
    }

}