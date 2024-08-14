package com.example.desafio01ejer02

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //Declaración de variables
    private lateinit var ip1: EditText
    private lateinit var ip2: EditText
    private lateinit var butnCalculate: Button
    private lateinit var txRent: TextView
    private lateinit var txAFP: TextView
    private lateinit var txISSS: TextView
    private lateinit var txNeto: TextView
    private lateinit var txName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Inicialización de elementos de interfaz
        ip1 = findViewById(R.id.txtName)
        ip2 = findViewById(R.id.txtSalary)
        butnCalculate = findViewById(R.id.btnCalculate)
        txRent = findViewById(R.id.txtRent)
        txAFP = findViewById(R.id.txtAfp)
        txISSS = findViewById(R.id.txtIsss)
        txNeto = findViewById(R.id.txtNeto)
        txName = findViewById(R.id.txtNombre)

        //Listener de button Calcular
        butnCalculate.setOnClickListener{calculate()}

    }

    private fun calculate(){
        //Validando que hayan datos
        val ip1T = ip1.text.toString()
        val ip2T = ip2.text.toString()

        if (ip1T.isEmpty() || ip2T.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa los datos correspondientes!",
                Toast.LENGTH_SHORT).show()
            return
        }else if (ip2T.toDouble() == 0.00 ){
            Toast.makeText(this, "El salario base no puede ser de 0.00!",
                Toast.LENGTH_SHORT).show()
            return
        }

        //Calculando los datos correspondientes
        val salary = ip2T.toDouble()
        var resultRent = 0.00 //Result RENT
        var resultAFP = 0.00 //Result AFP
        var resultIS = 0.00 //Result ISSS
        var resultNet = 0.00 //Result Net
        var resultName = ip1T

        //Validando la Renta si aplica o no
        if (salary >= 0.01 && salary <= 472.00 ){
            Toast.makeText(this, "Su salario no aplica a la renta",
                Toast.LENGTH_SHORT).show()
            resultRent = 0.00
        }else if (salary >= 472.01 && salary <= 895.24){
            resultRent = (salary * 0.10)
        }else if (salary >= 895.25 && salary <= 2038.10){
            resultRent = (salary * 0.20)
        }else{
            resultRent = (salary * 0.30)
        }
        //Resultado
        resultAFP = salary * 0.0725
        resultIS = salary * 0.03
        resultNet = salary - resultRent - resultAFP - resultIS

        //Aplicando al resultado con funcion TwoDigits para solo aceptar 2 digitos
        //val r1 = twoDigits(resultRent)
        //val r2 = twoDigits(resultAFP)
        //val r3 = twoDigits(resultIS)
        //val r4 = twoDigits(resultNet)

        //Salida al usuario
        txRent.text = String.format("%.2f", resultRent)
        txAFP.text = String.format("%.2f", resultAFP)
        txISSS.text = String.format("%.2f", resultIS)
        txNeto.text = String.format("%.2f", resultNet)
        txName.text = "Nombre: " + resultName
    }

//    private fun twoDigits(value: Double): String {
//        val format: DecimalFormat = DecimalFormat()
//        format.setMaximumFractionDigits(2) //Define 2 decimales.
//        return format.format(value)
//    }
}