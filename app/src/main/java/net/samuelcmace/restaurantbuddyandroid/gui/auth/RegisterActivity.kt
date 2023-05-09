package net.samuelcmace.restaurantbuddyandroid.gui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.DatabaseManager
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao
import net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment.AddressEntryFragment
import net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment.ContactInformationEntryFragment
import net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment.UsernamePasswordEntryFragment
import net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment.Verifiable

class RegisterActivity : AppCompatActivity() {

    private lateinit var mDatabaseManager: DatabaseManager
    private lateinit var mSessionDao: SessionDao

    private var mCurrentFragmentIndex: Int = 0
    private var mFragmentSet: HashMap<Int, Fragment> = HashMap()

    private lateinit var mPreviousButton: Button
    private lateinit var mNextButton: Button

    private lateinit var mSwitchToLoginButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        this.mDatabaseManager = Room.databaseBuilder(
            applicationContext,
            DatabaseManager::class.java, DatabaseManager.DATABASE_NAME
        ).build()
        this.mSessionDao = this.mDatabaseManager.sessionDao()

        this.mFragmentSet[1] = ContactInformationEntryFragment()
        this.mFragmentSet[2] = AddressEntryFragment()
        this.mFragmentSet[3] = UsernamePasswordEntryFragment()

        this.mPreviousButton = findViewById(R.id.btPrevious)
        this.mNextButton = findViewById(R.id.btNext)

        this.mPreviousButton.setOnClickListener {
            Log.i("mPreviousButton", "Pressed")
            switchFragment(this.mCurrentFragmentIndex - 1)
        }

        this.mNextButton.setOnClickListener {
            Log.i("mNextButton", "Pressed")
            switchFragment(this.mCurrentFragmentIndex + 1)
        }

        this.mSwitchToLoginButton = findViewById(R.id.activity_register_switch_to_login)

        this.mSwitchToLoginButton.setOnClickListener {
            this.startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }

        updateFragmentGUI(1)
    }

    private fun switchFragment(newFragmentIndex: Int) {
        try {
            if (newFragmentIndex > this.mCurrentFragmentIndex)
                (this.mFragmentSet[this.mCurrentFragmentIndex] as Verifiable).verifyInformation()

            when (newFragmentIndex) {
                1, 2, 3 -> {
                    updateFragmentGUI(newFragmentIndex)
                }

                4 -> {
                    register()
                }

                else -> {
                    Toast.makeText(this, "Error: You cannot go beyond the scope of the fragments!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFragmentGUI(newFragmentIndex: Int) {
        supportFragmentManager.commit {
            replace(R.id.fragment_information_entry, mFragmentSet[newFragmentIndex]!!)
            addToBackStack(null)
        }
        this.mCurrentFragmentIndex = newFragmentIndex

        when (newFragmentIndex) {
            1 -> {
                this.mPreviousButton.isEnabled = false
            }

            3 -> {
                this.mNextButton.text = getString(R.string.signup)
            }

            else -> {
                this.mPreviousButton.isEnabled = true
                this.mNextButton.text = getString(R.string.next)
            }
        }
    }

    private fun register() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://10.0.2.2:8888/api/v1/about/status"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            {
                Toast.makeText(applicationContext, it.get("successMessage").toString(), Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(applicationContext, "That didn't work: " + it.localizedMessage, Toast.LENGTH_LONG)
                    .show()
                Log.i("RegisterActivity (JSON Request): ", it.localizedMessage!!)
            })

        queue.add(request)
    }

}