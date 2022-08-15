package com.example.foodapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.foodapp.MainActivity
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityPhoneBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_phone.*
import java.util.concurrent.TimeUnit

class PhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var buttonPhone: Button
    private lateinit var phoneET: EditText
    private lateinit var number: String
    private lateinit var mProgressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        buttonPhone.setOnClickListener {
            number = phoneET.text.trim().toString()
            if (number.isNotEmpty()){
                if (number.length == 10){
                    number = "+90$number"
                    mProgressBar.visibility = View.VISIBLE

                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                }else{ Toast.makeText(this,"Lütfen numaranızı kontrol edin", Toast.LENGTH_SHORT).show()

                }
            }else{ Toast.makeText(this,"Lütfen numaranızı girin", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun init(){
        mProgressBar = binding.phoneProgressBar
        mProgressBar.visibility = View.INVISIBLE
        buttonPhone = binding.buttonPhone
        phoneET = binding.phoneEt
        auth = FirebaseAuth.getInstance()
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"Doğrulama Başarılı", Toast.LENGTH_SHORT).show()
                        sendToMain()
                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                        Log.d("TAG","signInWithPhoneAuthCredential: ${task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private fun sendToMain(){
        startActivity(Intent(this,MainActivity::class.java))
    }
    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Log.d("TAG","onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Log.d("TAG","onVerificationFailed: ${e.toString()}")
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            // Save verification ID and resending token so we can use them later
            val intent = Intent(this@PhoneActivity, OTPActivity::class.java)
            intent.putExtra("OTP",verificationId)
            intent.putExtra("resendToken", token)
            intent.putExtra("phoneNumber", number)
            startActivity(intent)
            mProgressBar.visibility = View.INVISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}