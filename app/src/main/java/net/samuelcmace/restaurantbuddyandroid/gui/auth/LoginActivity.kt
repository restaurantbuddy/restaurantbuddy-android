package net.samuelcmace.restaurantbuddyandroid.gui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.DatabaseManager
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao

class LoginActivity : AppCompatActivity() {

    private lateinit var mUsernameTextEdit: EditText
    private lateinit var mPasswordEditText: EditText

    private lateinit var mLoginButton: Button
    private lateinit var mSwitchToRegister: TextView

    private lateinit var mDatabaseManager: DatabaseManager
    private lateinit var mSessionDao: SessionDao

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.mDatabaseManager = Room.databaseBuilder(
            applicationContext,
            DatabaseManager::class.java, DatabaseManager.DATABASE_NAME
        ).build()
        this.mSessionDao = this.mDatabaseManager.sessionDao()

        this.mUsernameTextEdit = findViewById(R.id.activity_login_username_edittext)
        this.mPasswordEditText = findViewById(R.id.activity_login_password_edittext)

        this.mLoginButton = findViewById(R.id.activity_login_button)

        this.mSwitchToRegister = findViewById(R.id.activity_login_switch_to_register)

        this.mLoginButton.setOnClickListener {

        }

        this.mSwitchToRegister.setOnClickListener {
            this.startActivity(Intent(this, RegisterActivity::class.java))
            this.finish()
        }

    }
}
