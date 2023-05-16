package net.samuelcmace.restaurantbuddyandroid.gui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.gui.main.main.MainActivity
import net.samuelcmace.restaurantbuddyandroid.service.AuthenticationService

/**
 * Class representing the Login activity for the application.
 */
class LoginActivity : AppCompatActivity() {

    /**
     * Reference object pointing to the username field on the layout.
     */
    private lateinit var mUsernameTextEdit: EditText

    /**
     * Reference object pointing to the password field on the layout.
     */
    private lateinit var mPasswordEditText: EditText

    /**
     * Reference object pointing to the login button on the layout.
     */

    private lateinit var mLoginButton: Button

    /**
     * Reference object pointing to the 'Switch to Register' text field on the layout.
     */
    private lateinit var mSwitchToRegister: TextView

    /**
     * Instance of the AuthenticationService used by the LoginActivity.
     */
    private lateinit var mAuthenticationService: AuthenticationService

    /**
     * Method called by the Android API when the UI is drawn.
     *
     * @param savedInstanceState The object to reference the previously-created activity if it is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.mAuthenticationService = AuthenticationService(this)

        this.mUsernameTextEdit = findViewById(R.id.etUsername)
        this.mPasswordEditText = findViewById(R.id.etPassword)

        this.mLoginButton = findViewById(R.id.btLogin)

        this.mSwitchToRegister = findViewById(R.id.tvSwitchToRegister)

        this.mLoginButton.setOnClickListener {
            mAuthenticationService.login(mUsernameTextEdit.text.toString(), mPasswordEditText.text.toString(), {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
        }

        this.mSwitchToRegister.setOnClickListener {
            this.startActivity(Intent(this, RegisterActivity::class.java))
            this.finish()
        }

    }

}
