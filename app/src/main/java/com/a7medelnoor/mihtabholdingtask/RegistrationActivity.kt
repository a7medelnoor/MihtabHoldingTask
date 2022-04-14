package com.a7medelnoor.mihtabholdingtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    lateinit var editTextEmailAddress: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextName: EditText
    private lateinit var registerButton: Button
    private lateinit var signButton: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // bind the views
        editTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextTextPassword)
        registerButton = findViewById(R.id.button_register)
        signButton = findViewById(R.id.login_button)
        editTextName = findViewById(R.id.editTextName)

        auth = Firebase.auth

        registerButton.setOnClickListener {
            userRegistration()
        }
        signButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
    private fun userRegistration(){
        val emailAddress = editTextEmailAddress.text.toString()
        val password = editTextPassword.text.toString()
        val name = editTextName.text.toString()
        if (name.isBlank()){
            Toast.makeText(this,"Name cannot be blank",Toast.LENGTH_SHORT)
            return
        }
        if (emailAddress.isBlank()){
            Toast.makeText(this,"Email cannot be blank",Toast.LENGTH_SHORT)
            return
        }
        if (password.isBlank()){
            Toast.makeText(this,"Password cannot be blank",Toast.LENGTH_SHORT)
            return
        }
        // Todo: save the name with local database to be displayed on login
        auth.createUserWithEmailAndPassword(emailAddress,password).addOnCompleteListener(this) {
            if (it.isSuccessful){
                Toast.makeText(this,"Successfully Registered", Toast.LENGTH_SHORT)
                finish()
            }else {
                Toast.makeText(this," Registration failed please try again", Toast.LENGTH_SHORT)
            }
        }
    }
}