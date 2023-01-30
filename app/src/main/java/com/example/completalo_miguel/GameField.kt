package com.example.completalo_miguel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.internal.ViewUtils.dpToPx
import java.util.*

class GameField : AppCompatActivity() {

    private val numeros = arrayOf(R.drawable.num1, R.drawable.num2, R.drawable.num3, R.drawable.num4, R.drawable.num5, R.drawable.num6)
    private val colores = arrayOf(R.drawable.amarillo, R.drawable.naranja, R.drawable.rojo, R.drawable.morado, R.drawable.azul, R.drawable.verde)
    private lateinit var dibujos: Array<Int>

    private var topEjeX: Int=0
    private var topEjeY: Int=0
    private var topElements: Int=0
    private var sonido: String?=null
    private var vibracion: String?=null
    private lateinit var ids: MutableList<MutableList<Int>>
    private lateinit var values: MutableList<MutableList<Int>>
    private var contador: Int=0
    private lateinit var mp: MediaPlayer
    private lateinit var vibrar: Vibrator
    private lateinit var contarClicks: TextView
    private var elegir: String?=""
    private lateinit var tablero: LinearLayout
    private lateinit var cronometro: Chronometer

    private lateinit var linearLayout2: LinearLayout
    private var mostrarTrama: Int=0
    private lateinit var celdaView: CeldaView

    private var alturaMarcador:Int=0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_field)

        contarClicks=findViewById(R.id.tvCelda)

        val mibundle= intent.extras

        topEjeX=mibundle!!.getInt("cantidadFilas")
        topEjeY= mibundle!!.getInt("cantidadColumnas")
        topElements= mibundle!!.getInt("tramas")
        sonido= mibundle!!.getString("sonido")
        vibracion= mibundle!!.getString("vibracion")
        elegir=mibundle?.getString("elegir")
        tablero=findViewById(R.id.celda)
        cronometro=findViewById(R.id.chrono)

        if (elegir.equals("colores")){
            dibujos=colores
        }else{
            dibujos=numeros
        }

        tablero.removeAllViews()

        ids=mutableListOf(
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0))

        values=mutableListOf(
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0))

        alturaMarcador=dpToPx(175)
        val dm: DisplayMetrics = resources.displayMetrics
        var altura=(dm.heightPixels-alturaMarcador)/topEjeY!!
        var anchura=dm.widthPixels/topEjeX!!

        var ident: Int=0

        for (i in 0 until topEjeY!!){
            linearLayout2= LinearLayout(this)
            linearLayout2.orientation=LinearLayout.HORIZONTAL

            for (j in 0 until topEjeX!!){
                mostrarTrama=miRandom()
                //guardamos la trama a mostrar
                values[j][i]=mostrarTrama
                celdaView=CeldaView(this, j, i, topElements!!, mostrarTrama, dibujos[mostrarTrama])
                ident++
                //se asigna un identificador al objeto creado
                celdaView.id=ident
                //se guarda el identificador en una matriz
                ids[j][i]=ident
                celdaView.layoutParams = LinearLayout.LayoutParams(0, altura, 1f)
                celdaView.setOnClickListener{hasClick(j, i)}
                linearLayout2.addView(celdaView)
            }
            tablero.addView(linearLayout2)
        }

        //se pone en marcha el cronometro
        cronometro.start()
    }

    fun miRandom(): Int {
        val r = Random()
        return r.nextInt(topElements!!)
    }

    private fun hasClick(x:Int, y:Int){
        //efectos de sonido y vibracion
        if (sonido!=null){
            mp=MediaPlayer.create(this, R.raw.aku_aku_sound)
            mp?.start()
        }

        if (vibracion!=null){
            vibrar=getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if(vibrar.hasVibrator()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    vibrar.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
                }else{
                    vibrar.vibrate(100)
                }
            }
        }

        changeView(x, y)
        //esquinas del tablero
        if (x==0 && y==0){
            changeView(0, 1)
            changeView(1, 0)
            changeView(1, 1)
        }
        else if (x==0 && y==topEjeY!!-1){
            changeView(0, topEjeY!!-2)
            changeView(1, topEjeY!!-2)
            changeView(1, topEjeY!!-1)
        }
        else if(x==topEjeX!!-1 && y==0){
            changeView(topEjeX!!-2,0)
            changeView(topEjeX!!-2, 1)
            changeView(topEjeX!!-1, 1)
        }
        else if(x==topEjeX!!-1 && y==topEjeY!!-1){
            changeView(topEjeX!!-2, topEjeY!!-1)
            changeView(topEjeX!!-2, topEjeY!!-2)
            changeView(topEjeX!!-1, topEjeY!!-2)
        }
        //lados del tablero
        else if (x==0){
            changeView(x,y-1)
            changeView(x, y+1)
            changeView(x+1, y)
        }
        else if(y==0){
            changeView(x-1, y)
            changeView(x+1, y)
            changeView(x, y+1)
        }
        else if(x==topEjeX!!-1){
            changeView(x, y-1)
            changeView(x, y+1)
            changeView(x-1, y)
        }
        else if (y==topEjeY!!-1){
            changeView(x-1, y)
            changeView(x+1, y)
            changeView(x, y-1)
        }
        else{//resto
            changeView(x-1, y)
            changeView(x+1, y)
            changeView(x, y-1)
            changeView(x, y+1)
        }
        //actualizar marcador
        contador++
        contarClicks.text=contador!!.toString()
        checkIfFinished()
    }

    private fun changeView(x:Int, y: Int){
        val celda = findViewById<CeldaView>(ids[x][y])
        var newIndex: Int=celda.getNewIndex()
        values[x][y]=newIndex
        celda.setBackgroundResource(dibujos[newIndex])
        celda.invalidate()
    }

    private fun checkIfFinished(){
        val valor=values[0][0]
        for (i in 0 until topEjeY){
            for (j in 0 until topEjeX){
                if (values[j][i]!=valor){
                    return
                }
            }
        }

        val resulIntent= Intent(this, WinnerActivity::class.java)
        resulIntent.putExtra("clicks", contador)
        resulIntent.putExtra("tiempo", cronometro.text.toString())
        startActivity(resulIntent)
        setResult(RESULT_OK, resulIntent)
        finish()
    }

    fun Context.dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}