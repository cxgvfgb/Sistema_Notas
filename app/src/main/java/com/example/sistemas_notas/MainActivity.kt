package com.example.sistemas_notas

import android.os.Bundle
import android.text.Html
import android.view.View
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
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etNota = findViewById<EditText>(R.id.etNota)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnMostrar = findViewById<Button>(R.id.btnMostrar)
        val tvResultados = findViewById<TextView>(R.id.tvResultados)
        val tvTituloResultados = findViewById<TextView>(R.id.tvTituloResultados)

        // Acción del botón GUARDAR
        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val notaTexto = etNota.text.toString().trim()

            // 1. Validar campos vacíos
            if (nombre.isEmpty() || notaTexto.isEmpty()) {
                mostrarAviso(getString(R.string.dialog_message))
                return@setOnClickListener
            }

            // 2. Validar nombre duplicado
            if (listaEstudiantes.containsKey(nombre)) {
                mostrarAviso(getString(R.string.error_duplicate_name))
                return@setOnClickListener
            }

            // 3. Validar que sea un número y esté entre 0 y 20
            try {
                val nota = notaTexto.toInt()
                if (nota < 0 || nota > 20) {
                    mostrarAviso(getString(R.string.error_invalid_grade))
                } else {
                    // Guardar en el TreeMap
                    listaEstudiantes[nombre] = nota
                    // Limpiar campos
                    etNombre.setText("")
                    etNota.setText("")
                    etNombre.requestFocus()
                }
            } catch (e: NumberFormatException) {
                mostrarAviso(getString(R.string.error_not_number))
            }
        }

        // Acción del botón MOSTRAR
        btnMostrar.setOnClickListener {
            if (listaEstudiantes.isEmpty()) {
                mostrarAviso("No hay estudiantes registrados para mostrar.")
                tvResultados.visibility = View.GONE
                tvTituloResultados.visibility = View.GONE
                return@setOnClickListener
            }

            // Mostrar el contenedor y el título
            tvResultados.visibility = View.VISIBLE
            tvTituloResultados.visibility = View.VISIBLE

            var resultadosHtml = ""
            // Recorrer el TreeMap (ya está ordenado alfabéticamente)
            for ((nombre, nota) in listaEstudiantes) {
                val (estado, colorHex) = when {
                    nota >= 13 -> "Aprobado" to "#2E7D32"     // Verde
                    nota >= 10 -> "Sustitutorio" to "#F9A825" // Naranja/Amarillo
                    else -> "Desaprobado" to "#C62828"        // Rojo
                }
                resultadosHtml += "<b>$nombre</b> - $nota - <font color='$colorHex'>$estado</font><br/>"
            }
            // Mostrar texto con formato HTML
            tvResultados.text = Html.fromHtml(resultadosHtml, Html.FROM_HTML_MODE_LEGACY)
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