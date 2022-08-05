package com.example.foodapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val sifre = binding.passET.text.toString()
            val sifreTekrar = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && sifre.isNotEmpty() && sifreTekrar.isNotEmpty()) {
                if (sifre == sifreTekrar) {

                    firebaseAuth.createUserWithEmailAndPassword(email, sifre).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Şifreler eşleşmiyor", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tüm alanları doldurun!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}