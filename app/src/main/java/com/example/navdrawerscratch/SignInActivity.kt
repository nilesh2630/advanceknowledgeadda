package com.example.navdrawerscratch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        db = AppDatabase.getDatabase(this)
        progressBar = findViewById(R.id.progress_bar)

        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val signinButton: Button = findViewById(R.id.signin_button)
        val goToSignup: TextView = findViewById(R.id.go_to_signup)

        signinButton.setOnClickListener {
            hideKeyboard(it)

            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            progressBar.visibility = View.VISIBLE

            lifecycleScope.launch {
                val user = db.userDao().getUser(username, password)

                if (user != null) {
                    Toast.makeText(this@SignInActivity, "Sign In Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@SignInActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }
            }
        }

        goToSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
