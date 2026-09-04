package com.example.sistemas_notas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.TreeMap

class MainActivity : AppCompatActivity() {

    // TreeMap para guardar estudiantes y notas (ordenado por nombre)
    private val listaEstudiantes = TreeMap<String, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vincular componentes de la interfaz
        val etName = findViewById<EditText>(R.id.etName)
        val etGrade = findViewById<EditText>(R.id.etGrade)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val tvResults = findViewById<TextView>(R.id.tvResults)

        // Acción del botón GUARDAR
        btnSave.setOnClickListener {
            val nombre = etName.text.toString().trim()
            val notaTexto = etGrade.text.toString().trim()

            // 1. Validar campos vacíos
            if (nombre.isEmpty() || notaTexto.isEmpty()) {
                mostrarAviso(getString(R.string.dialog_message))
                return@setOnClickListener
            }

            // 2. Validar que sea un número y esté entre 0 y 20
            try {
                val nota = notaTexto.toInt()
                if (nota < 0 || nota > 20) {
                    mostrarAviso(getString(R.string.error_invalid_grade))
                } else {
                    // Guardar en el TreeMap
                    listaEstudiantes[nombre] = nota
                    // Limpiar campos
                    etName.setText("")
                    etGrade.setText("")
                    etName.requestFocus()
                }
            } catch (e: NumberFormatException) {
                mostrarAviso(getString(R.string.error_not_number))
            }
        }

        // Acción del botón MOSTRAR
        btnShow.setOnClickListener {
            if (listaEstudiantes.isEmpty()) {
                tvResults.text = "No hay estudiantes registrados."
                return@setOnClickListener
            }

            var resultados = ""
            // Recorrer el TreeMap (ya está ordenado alfabéticamente)
            for ((nombre, nota) in listaEstudiantes) {
                val estado = when {
                    nota >= 13 -> "Aprobado"
                    nota >= 10 -> "Sustitutorio"
                    else -> "Desaprobado"
                }
                resultados += "$nombre - $nota - $estado\n"
            }
            tvResults.text = resultados
        }
    }

    // Función para mostrar el AlertDialog de aviso
    private fun mostrarAviso(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_title))
        builder.setMessage(mensaje)
        builder.setPositiveButton(getString(R.string.dialog_ok), null)
        builder.show()
    }
}