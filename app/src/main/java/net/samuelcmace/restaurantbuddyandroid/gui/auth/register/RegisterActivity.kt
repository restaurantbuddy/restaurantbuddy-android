package net.samuelcmace.restaurantbuddyandroid.gui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.DatabaseManager
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao
import net.samuelcmace.restaurantbuddyandroid.gui.auth.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var mEmailEditText: EditText
    private lateinit var mEmailConfirmEditText: EditText

    private lateinit var mPasswordEditText: EditText
    private lateinit var mPasswordConfirmEditText: EditText

    private lateinit var mRegisterButton: Button
    private lateinit var mSwitchToLoginButton: TextView

    private lateinit var mDatabaseManager: DatabaseManager
    private lateinit var mSessionDao: SessionDao

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.mDatabaseManager = Room.databaseBuilder(
            applicationContext,
            DatabaseManager::class.java, DatabaseManager.DATABASE_NAME
        ).build()
        this.mSessionDao = this.mDatabaseManager.sessionDao()

//        this.mEmailEditText = findViewById(R.id.activity_register_username_edittext)
//        this.mEmailConfirmEditText = findViewById(R.id.activity_register_username_confirm_edittext)
//
//        this.mPasswordEditText = findViewById(R.id.activity_register_password_edittext)
//        this.mPasswordConfirmEditText = findViewById(R.id.activity_register_confirm_password_edittext)

        this.mRegisterButton = findViewById(R.id.activity_register_button)
        this.mSwitchToLoginButton = findViewById(R.id.activity_register_switch_to_login)

        this.mRegisterButton.setOnClickListener {

            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.4.160:8888/api/v1/about/status"

            val request = StringRequest(Request.Method.GET, url,
                {
                    Toast.makeText(applicationContext, "Response is: ${it}", Toast.LENGTH_LONG).show()
                },
                {
                    Toast.makeText(applicationContext, "That didn't work: " + it.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                    Log.i("RegisterActivity (JSON Request): ", it.localizedMessage!!)
                })

            queue.add(request)

//            val email = this.mEmailEditText.text.toString()
//            val password = this.mPasswordEditText.text.toString()
//
//            if (email != this.mEmailConfirmEditText.text.toString()) {
//                Toast.makeText(this, "The email confirmation field must match!", Toast.LENGTH_LONG).show()
//            } else if (password != this.mPasswordConfirmEditText.text.toString()) {
//                Toast.makeText(this, "The password confirmation field must match!", Toast.LENGTH_LONG).show()
//            } else {
//
//            }
        }

        this.mSwitchToLoginButton.setOnClickListener {
            this.startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }
    }
}