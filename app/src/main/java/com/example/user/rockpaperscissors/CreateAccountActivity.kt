package com.example.user.rockpaperscissors

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        auth = FirebaseAuth.getInstance()

        createAccountActivityLogInButton.setOnClickListener {
            logInActivity()
        }

        createAccountActivityCreateAccountButton.setOnClickListener {
            register()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Create User with Email : Success")
                    setNewUserName()
                } else {
                    Log.d(TAG, "Create User with Email : Fail")
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setNewUserName() {
        val name = createAccountActivityNameEditText.text.toString()
        val user = auth.currentUser
        val setProfileSetting = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .build()
        user?.updateProfile(setProfileSetting)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "UserNameSet:Success")
                } else {
                    Log.d(TAG, "UserNameSet:Failed")
                    Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun logInActivity() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }

    private fun register() {

        val password = createAccountActivityPasswordEditText.text.toString()
        val repeatPassword = createAccountActivityRepeatPasswordEditText.text.toString()

        if (password == repeatPassword && password.isNotEmpty() && repeatPassword.isNotEmpty() && createAccountActivityEmailEditText.text.toString().isNotEmpty() && createAccountActivityNameEditText.text.toString().isNotEmpty())
            createAccount(createAccountActivityEmailEditText.text.toString(), password)
        else {
            if (password != repeatPassword) {
                createAccountActivityPasswordEditText.error = "Passwords do not match"
                createAccountActivityRepeatPasswordEditText.error = "Passwords do not match"
            } else if (password.isEmpty()) {
                createAccountActivityPasswordEditText.error = "Please enter your password"
            } else if (repeatPassword.isEmpty()) {
                createAccountActivityRepeatPasswordEditText.error = "Please repeat password"
            } else if (createAccountActivityEmailEditText.text.toString().isEmpty()) {
                createAccountActivityEmailEditText.error = "Please enter your email"
            } else {
                createAccountActivityNameEditText.error = "Please enter your name"
            }
        }

    }

    companion object {
        private const val TAG = "CreateAccountActivity"
    }
}
