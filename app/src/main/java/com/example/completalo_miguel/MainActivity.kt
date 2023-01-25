package com.example.completalo_miguel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.TextView
import com.example.completalo_miguel.databinding.ActivityMainBinding
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progresoX: TextView
    private lateinit var progresoY: TextView
    private lateinit var progresoColores: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sbNumeroColumnas.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateXTiles(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.sbNumeroFilas.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateYTiles(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.sbNumeroColores.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateColores(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuReglas->{
                val bottomSheet = exampleBottomSheet(R.layout.contenido_reglas)
                bottomSheet.show(supportFragmentManager, bottomSheet.tag)
            }

            R.id.menuAcercaDe->{

            }

            R.id.menuConfig->{

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateXTiles(progreso:Int) {
        progresoX = binding.tvColumnas
        progresoX.setText("Numero de Columnas : " + progreso)
    }

    fun updateYTiles(progreso:Int) {
        progresoY = binding.tvFilas
        progresoY.setText("Numero de Filas : " + progreso)
    }

    fun updateColores(progreso:Int) {
        progresoColores = binding.tvColor
        progresoColores.setText("Numero de Colores : " + progreso)
    }

    fun mostrarHowTo(){
        val bottomSheet = exampleBottomSheet(R.layout.contenido_reglas)
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }

}