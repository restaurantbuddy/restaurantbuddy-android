package net.samuelcmace.restaurantbuddyandroid.gui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.service.AuthenticationService

class MenuActivity : AppCompatActivity() {

    private lateinit var tvSessionToken: TextView
    private lateinit var tvSessionUrl: TextView
    private lateinit var tvSessionMessage: TextView

    private lateinit var btLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        this.tvSessionToken = findViewById(R.id.tvSessionToken)
        this.tvSessionUrl = findViewById(R.id.tvSessionUrl)
        this.tvSessionMessage = findViewById(R.id.tvSessionMessage)
        this.btLogout = findViewById(R.id.btLogoutButton)

        tvSessionToken.text = AppConfig.authToken?.token.toString()
        tvSessionUrl.text = AppConfig.getServerUrl()

        this.btLogout.setOnClickListener {
            tvSessionToken.text = AppConfig.authToken?.token.toString()
            tvSessionUrl.text = AppConfig.getServerUrl()
        }

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
        val authenticationService = AuthenticationService(this)
        lifecycleScope.launch {
            authenticationService.logout()
        }.invokeOnCompletion {
            finish()
        }
    }

}