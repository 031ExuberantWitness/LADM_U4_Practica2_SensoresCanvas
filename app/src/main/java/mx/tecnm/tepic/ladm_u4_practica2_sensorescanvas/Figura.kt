package mx.tecnm.tepic.ladm_u4_practica2_sensorescanvas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Figura(Lienzo:Lienzo, imagen:Int, coordX:Float, coordY: Float) {

    var Lienzo = Lienzo
    var imagen = BitmapFactory.decodeResource(Lienzo.resources, imagen)

    var x = coordX
    var y = coordY

    fun pintar(c: Canvas){
        var p = Paint()
        c.drawBitmap(imagen, x, y, p)
    }
}