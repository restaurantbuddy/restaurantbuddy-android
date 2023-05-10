package net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import net.samuelcmace.restaurantbuddyandroid.R

/**
 * Fragment representing the username and password entry portion of the registration UI.
 */
class UsernamePasswordEntryFragment : Fragment(R.layout.fragment_username_password), Verifiable {

    /**
     * Reference object pointing to the username entry field on the layout.
     */
    private lateinit var etUsername: EditText

    /**
     * Reference object pointing to the username confirmation field on the layout.
     */
    private lateinit var etConfirmUsername: EditText

    /**
     * Reference object pointing to the password entry field on the layout.
     */
    private lateinit var etPassword: EditText

    /**
     * Reference object pointing to the password confirmation field on the layout.
     */
    private lateinit var etConfirmPassword: EditText

    /**
     * Method called by the Android API after the fragment has been drawn.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.etUsername = view.findViewById(R.id.etUsername)
        this.etConfirmUsername = view.findViewById(R.id.etConfirmUsername)
        this.etPassword = view.findViewById(R.id.etPassword)
        this.etConfirmPassword = view.findViewById(R.id.etConfirmPassword)

    }

    /**
     * Method to retrieve the string value of the username field.
     */
    fun getUsername() = this.etUsername.text.toString()

    /**
     * Method to retrieve the string value of the username confirmation field.
     */
    fun getConfirmUsername() = this.etConfirmUsername.text.toString()

    /**
     * Method to retrieve the string value of the password field.
     */
    fun getPassword() = this.etPassword.text.toString()

    /**
     * Method to retrieve the string value of the password confirmation field.
     */
    fun getConfirmPassword() = this.etConfirmPassword.text.toString()

    /**
     * Method called to verify the fields contained on the fragment.
     */
    override fun verifyInformation() {
        if (getUsername().isEmpty())
            throw RuntimeException("The username field cannot be empty!")
        else if (getConfirmUsername().isEmpty())
            throw RuntimeException("The username confirmation field cannot be empty!")
        else if (getPassword().isEmpty())
            throw RuntimeException("The password field cannot be empty!")
        else if (getConfirmPassword().isEmpty())
            throw RuntimeException("The password confirmation field cannot be empty!")
        else if (getUsername() != getConfirmUsername())
            throw RuntimeException("The username confirmation field does not match!")
        else if (getPassword() != getConfirmPassword())
            throw RuntimeException("The password confirmation field does not match!")
        else if (getPassword().length < 8)
            throw RuntimeException("The password must be at-least eight digits in length!")
    }

}