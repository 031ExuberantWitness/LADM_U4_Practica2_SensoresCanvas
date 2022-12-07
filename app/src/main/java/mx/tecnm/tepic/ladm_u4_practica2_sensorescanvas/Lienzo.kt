package mx.tecnm.tepic.ladm_u4_practica2_sensorescanvas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Lienzo(p: Inicio): View(p) {
    var punteroFigura = p

    val nube1 = BitmapFactory.decodeResource(resources, R.drawable.nube1)
    val nube2 = BitmapFactory.decodeResource(resources, R.drawable.nube2)
    val nube3 = BitmapFactory.decodeResource(resources, R.drawable.nube3)
    val nube4 = BitmapFactory.decodeResource(resources, R.drawable.nube4)

    //Posicion default: X: 500F, Y: 150F
    val sol = BitmapFactory.decodeResource(resources, R.drawable.sol)
    val luna = BitmapFactory.decodeResource(resources, R.drawable.luna)

    var bruja = Figura (this, R.drawable.brujax120, 350F, 850F)

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        var p = Paint()

        val randomizer = (0..10).random()

        if (punteroFigura.dia){
            val girox = punteroFigura.girosX * 100
            val giroY = punteroFigura.girosY * 100

            c.drawColor(Color.parseColor("#3049b8"))
            c.drawBitmap(sol, giroY + 380f, girox, p)

            c.drawBitmap(nube1,  girox + 130f, giroY  + 1200f, p)
            c.drawBitmap(nube2,  girox + 250f, giroY + 1220f, p)
            c.drawBitmap(nube3,  girox + 500f, giroY, p)
            c.drawBitmap(nube4,  girox + 550f, giroY, p)
        }else{
            val girox = punteroFigura.girosX * 100
            val giroY = punteroFigura.girosY * 100

            c.drawColor(Color.parseColor("#1d254a"))
            c.drawBitmap(luna, giroY + 380f, girox, p)

            c.drawBitmap(nube1,  girox + 130f, giroY  + 1200f, p)
            c.drawBitmap(nube2,  girox + 250f, giroY + 1220f, p)
            c.drawBitmap(nube3,  girox + 500f, giroY, p)
            c.drawBitmap(nube4,  girox + 550f, giroY, p)
        }

        bruja.pintar(c)
    }
}