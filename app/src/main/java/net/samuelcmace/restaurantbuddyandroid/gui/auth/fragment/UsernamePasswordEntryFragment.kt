package net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import net.samuelcmace.restaurantbuddyandroid.R

class UsernamePasswordEntryFragment : Fragment(R.layout.fragment_username_password), Verifiable {

    private lateinit var etUsername: EditText
    private lateinit var etConfirmUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.etUsername = view.findViewById(R.id.etUsername)
        this.etConfirmUsername = view.findViewById(R.id.etConfirmUsername)
        this.etPassword = view.findViewById(R.id.etPassword)
        this.etConfirmPassword = view.findViewById(R.id.etConfirmPassword)

    }

    private fun getUsername() = this.etUsername.text.toString()
    private fun getConfirmUsername() = this.etConfirmUsername.text.toString()
    private fun getPassword() = this.etPassword.text.toString()
    private fun getConfirmPassword() = this.etConfirmPassword.text.toString()

    override fun verifyInformation() {
        if (getUsername().isEmpty())
            throw Exception("The username field cannot be empty!")
        else if (getConfirmUsername().isEmpty())
            throw Exception("The username confirmation field cannot be empty!")
        else if (getPassword().isEmpty())
            throw Exception("The password field cannot be empty!")
        else if (getConfirmPassword().isEmpty())
            throw Exception("The password confirmation field cannot be empty!")
        else if (getUsername() != getConfirmUsername())
            throw Exception("The username confirmation field does not match!")
        else if (getPassword() != getConfirmPassword())
            throw Exception("The password confirmation field does not match!")
        else if (getPassword().length < 8)
            throw Exception("The password must be at-least eight digits in length!")
    }

}