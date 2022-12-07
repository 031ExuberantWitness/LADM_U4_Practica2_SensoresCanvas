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

    fun mover(xToque: Float, yToque: Float){
        x = xToque - imagen.width/2
        y = yToque - imagen.height/2
    }

    fun determinarArea(xToque: Float, yToque: Float) : Boolean {
        var x2 = x + imagen.width
        var y2 = y + imagen.height

        if (xToque >= x && xToque <= x2){
            if (yToque >= y && yToque <= y2){
                return true
            }
        }

        return false
    }
}