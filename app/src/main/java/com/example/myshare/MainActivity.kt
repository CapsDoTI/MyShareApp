package com.example.myshare

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val PREFS_NAME = "meus dados"
const val KEY_NOME = "nome"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtEdit = findViewById<TextView>(R.id.id_nome)
        val txtResultado = findViewById<TextView>(R.id.id_resultado)
        val btnCarregar = findViewById<Button>(R.id.id_btn_carregar)
        val btnSalvar = findViewById<Button>(R.id.id_btn_salvar)

        btnSalvar.setOnClickListener {
            val nome = txtEdit.text.toString()

            val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString(KEY_NOME, nome)
            editor.apply()

            Toast.makeText(
                this@MainActivity,
                "Nome salvo com sucesso",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnCarregar.setOnClickListener {
            val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            val nome = prefs.getString(KEY_NOME, "Nenhum nome salvo")
            txtResultado.text = "Nome: $nome"
        }
    }
}