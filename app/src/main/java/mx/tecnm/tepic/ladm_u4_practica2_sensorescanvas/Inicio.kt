package mx.tecnm.tepic.ladm_u4_practica2_sensorescanvas

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.tecnm.tepic.ladm_u4_practica2_sensorescanvas.databinding.ActivityMainBinding

class Inicio : AppCompatActivity(), SensorEventListener {
    lateinit var binding : ActivityMainBinding
    lateinit var canvas: Lienzo

    lateinit var sensorManager: SensorManager
    lateinit var proxSensor: Sensor
    lateinit var girosSensor: Sensor

    var dia = false
    var corriendo = false

    var girosX = 0F
    var girosY = 0F
    var girosZ = 0F

    private val NS2S = 1.0f / 1000000000.0f
    private val deltaRotationVector = FloatArray(4) { 0f }
    private var timestamp: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        canvas = Lienzo(this)
        setContentView(canvas)

        corriendo = true
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        girosSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.stringType.equals(girosSensor.stringType)) logicaGiroscopio(
            event.values,
            event.timestamp
        )
        if (event.sensor.stringType.equals(proxSensor.stringType)) logicaProximidad(event.values)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    private fun logicaProximidad(valores: FloatArray) {
        val distance = valores[0]

        dia = distance > 5
        if (corriendo) canvas.invalidate()
    }

    private fun logicaGiroscopio(valores: FloatArray, evtTimestamp: Long) {
        girosX = valores[0]
        girosY = valores[1]
        girosZ = valores[2]
        if (corriendo) canvas.invalidate()
    }

    override fun onResume() {
        super.onResume()

        proxSensor.also { proximidad ->
            sensorManager.registerListener(this, proximidad, SensorManager.SENSOR_DELAY_NORMAL)
        }

        girosSensor.also { giroscopio ->
            sensorManager.registerListener(this, giroscopio, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}

