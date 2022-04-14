package com.a7medelnoor.mihtabholdingtask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var ediTextEmail: EditText
    lateinit var editTextPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var buttonToRegister: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // bind the view
        ediTextEmail = findViewById(R.id.editTextTextEmailAddressLogin)
        editTextPassword = findViewById(R.id.editTextTextPasswordLogin)
        loginButton = findViewById(R.id.button_login)
        buttonToRegister = findViewById(R.id.button_to_register)

        buttonToRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
        auth = FirebaseAuth.getInstance()
        loginButton.setOnClickListener {
            userLogin()
        }

    }

    private fun userLogin() {
        val email = ediTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        if (email.isBlank()) {
            Toast.makeText(this, "Email address cannot be blank", Toast.LENGTH_SHORT)
            return
        }
        if (password.isBlank()) {
            Toast.makeText(this, "Password cannot be blank", Toast.LENGTH_SHORT)
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT)
                // redirect user to main activity
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT)
            }
        }

    }
}