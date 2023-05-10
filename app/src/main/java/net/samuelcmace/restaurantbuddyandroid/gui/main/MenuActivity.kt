package net.samuelcmace.restaurantbuddyandroid.gui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.gui.auth.LoginActivity
import net.samuelcmace.restaurantbuddyandroid.service.AuthenticationService
import net.samuelcmace.restaurantbuddyandroid.service.CustomerService

class MenuActivity : AppCompatActivity() {

    private lateinit var tvSessionToken: TextView
    private lateinit var tvSessionUrl: TextView
    private lateinit var tvSessionMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        this.tvSessionToken = findViewById(R.id.tvSessionToken)
        this.tvSessionUrl = findViewById(R.id.tvSessionUrl)
        this.tvSessionMessage = findViewById(R.id.tvSessionMessage)

        val customerService = CustomerService(this)

        customerService.testAuthorization({
            tvSessionToken.text = AppConfig.authToken?.token.toString()
            tvSessionUrl.text = AppConfig.getServerUrl()
            tvSessionMessage.text = it
        }, {
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        })
        Toast.makeText(this, "Processing request... one moment...", Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.itCart -> {
                startActivity(Intent(this, CartActivity::class.java))
            }

            R.id.itFeedback -> {
                startActivity(Intent(this, FeedbackActivity::class.java))
            }

            R.id.itLogout -> {
                logout()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        AuthenticationService(this).logout {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}

