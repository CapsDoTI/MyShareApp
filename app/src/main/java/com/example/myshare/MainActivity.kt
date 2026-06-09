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
const val  KEY_EMAIL = "email"
const val  KEY_TELEFONE = "telefone"
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
        val txtEditEmail = findViewById<TextView>(R.id.id_email)
        val txtEditTel = findViewById<TextView>(R.id.id_telefone)
        val txtResultado = findViewById<TextView>(R.id.id_resultado)
        val btnCarregar = findViewById<Button>(R.id.id_btn_carregar)
        val btnSalvar = findViewById<Button>(R.id.id_btn_salvar)

        btnSalvar.setOnClickListener {
            val nome = txtEdit.text.toString()
            val email = txtEditEmail.text.toString()
            val telefone = txtEditTel.text.toString()

            val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString(KEY_NOME, nome)
                .putString(KEY_EMAIL, email)
                .putString(KEY_TELEFONE, telefone)
            editor.apply()

            Toast.makeText(
                this@MainActivity,
                "Dados salvos com sucesso",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnCarregar.setOnClickListener {
            val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            val nome = prefs.getString(KEY_NOME, "Nenhum nome salvo")
            val email = prefs.getString(KEY_EMAIL, "Nenhum nome salvo")
            val telefone = prefs.getString(KEY_TELEFONE, "Nenhum nome salvo")
            txtResultado.text = "Nome: $nome\n Email: $email\n Telefone: $telefone"
        }
    }
}