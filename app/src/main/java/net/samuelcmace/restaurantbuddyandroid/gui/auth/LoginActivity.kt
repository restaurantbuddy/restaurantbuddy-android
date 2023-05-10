package net.samuelcmace.restaurantbuddyandroid.gui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.gui.main.MenuActivity
import net.samuelcmace.restaurantbuddyandroid.service.AuthenticationService

class LoginActivity : AppCompatActivity() {

    private lateinit var mUsernameTextEdit: EditText
    private lateinit var mPasswordEditText: EditText

    private lateinit var mLoginButton: Button
    private lateinit var mSwitchToRegister: TextView

    private lateinit var mAuthenticationService: AuthenticationService

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.mAuthenticationService = AuthenticationService(this)

        this.mUsernameTextEdit = findViewById(R.id.activity_login_username_edittext)
        this.mPasswordEditText = findViewById(R.id.activity_login_password_edittext)

        this.mLoginButton = findViewById(R.id.activity_login_button)

        this.mSwitchToRegister = findViewById(R.id.activity_login_switch_to_register)

        this.mLoginButton.setOnClickListener {
            mAuthenticationService.login(mUsernameTextEdit.text.toString(), mPasswordEditText.text.toString())
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        this.mSwitchToRegister.setOnClickListener {
            this.startActivity(Intent(this, RegisterActivity::class.java))
            this.finish()
        }

    }
}
