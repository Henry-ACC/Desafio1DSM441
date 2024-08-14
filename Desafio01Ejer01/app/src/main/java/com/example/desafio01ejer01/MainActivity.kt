package com.example.desafio01ejer01

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
    private lateinit var nameStudent: EditText
    private lateinit var score1: EditText
    private lateinit var score2: EditText
    private lateinit var score3: EditText
    private lateinit var score4: EditText
    private lateinit var score5: EditText
    private lateinit var result: TextView
    private lateinit var btnPromedio: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Inicializacion de variables
        nameStudent = findViewById(R.id.studentName)
        score1 = findViewById(R.id.nota1)
        score2 = findViewById(R.id.nota2)
        score3 = findViewById(R.id.nota3)
        score4 = findViewById(R.id.nota4)
        score5 = findViewById(R.id.nota5)
        result = findViewById(R.id.resultText)
        btnPromedio = findViewById(R.id.btnCalcular)


        btnPromedio.setOnClickListener{calcPromed()}
    }

    private fun calcPromed(){
        val nameText = nameStudent.text.toString()
        val score1T = score1.text.toString()
        val score2T = score2.text.toString()
        val score3T = score3.text.toString()
        val score4T = score4.text.toString()
        val score5T = score5.text.toString()

        if (nameText.isEmpty() || score1T.isEmpty() || score2T.isEmpty() || score3T.isEmpty() || score4T.isEmpty() || score5T.isEmpty()){
            Toast.makeText(this, "No hay datos ingresados!",
                Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = score1T.toDouble()
        val num2 = score2T.toDouble()
        val num3 = score3T.toDouble()
        val num4 = score4T.toDouble()
        val num5 = score5T.toDouble()

        if (num1 > 10 || num2 > 10 || num3 > 10 || num4 > 10 || num5 > 10) {
            Toast.makeText(
                this, "Hay notas ingresadas mayores que 10, solo se permite 0 a 10",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val nota1 = num1 * 0.15
        val nota2 = num2 * 0.15
        val nota3 = num3 * 0.20
        val nota4 = num4 * 0.25
        val nota5 = num5 * 0.25
        val scoreRes = nota1 + nota2 + nota3 + nota4 + nota5
        var textApRe = ""

        if (scoreRes >= 6.00){
            textApRe = "Aprobado :) Felicidades!"
        }else{
            textApRe = "Reprobado :( Sigue mejorando."
        }

        result.text = "Nombre: " + nameText + "\nNota Final: " + scoreRes + "\nHas " + textApRe
    }

}