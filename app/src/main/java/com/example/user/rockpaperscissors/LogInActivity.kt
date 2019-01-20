package com.example.user.rockpaperscissors

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()

        logInActivityCreateAccountButton.setOnClickListener {
            createAccountActivity()
        }

        logInActivityLogInButton.setOnClickListener {
            signIn(logInActivityEmailEditText.text.toString(), logInActivityPasswordEditText.text.toString())
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Sign in with Email : Success")
                logIn()
            } else {
                Log.d(TAG, "Sign in with Email : Fail")
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createAccountActivity() {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
    }

    private fun logIn() {
        val intent = Intent(this, RockPaperScissors::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "LogInActivity"
    }
}
