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
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment.AddressEntryFragment
import net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment.ContactInformationEntryFragment
import net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment.UsernamePasswordEntryFragment
import net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment.Verifiable
import net.samuelcmace.restaurantbuddyandroid.gui.main.main.MainActivity
import net.samuelcmace.restaurantbuddyandroid.service.AuthenticationService
import org.json.JSONObject

/**
 * Activity to register a new user in the application.
 */
class RegisterActivity : AppCompatActivity() {

    /**
     * The index corresponding to the current active fragment in the registration sequence.
     * The default value of 0 is invalid and will be changed upon instantiation of the activity.
     */
    private var mCurrentFragmentIndex: Int = 0

    /**
     * Set containing the fragments used in the registration process.
     */
    private var mFragmentSet: HashMap<Int, Fragment> = HashMap()

    /**
     * Reference object pointing to the previous button on the layout.
     * Used to navigate to the previous fragment (if applicable).
     */
    private lateinit var mPreviousButton: Button

    /**
     * Reference object pointing to the next button on the layout.
     * Used to navigate to the next fragment (if applicable).
     */
    private lateinit var mNextButton: Button

    /**
     * Reference object pointing to the 'Switch to Login' text field on the layout.
     */
    private lateinit var mSwitchToLoginButton: TextView

    /**
     * Method called by the Android API when the UI is drawn.
     *
     * @param savedInstanceState The object to reference the previously-created activity if it is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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

        this.mSwitchToLoginButton = findViewById(R.id.tvSwitchToLogin)

        this.mSwitchToLoginButton.setOnClickListener {
            this.startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }

        updateFragmentGUI(1)
    }

    /**
     * Method called when the fragment is switched by the user.
     * This method will verify the input fields, and will then proceed to update the fragment GUI by calling the
     * {@link updateFragmentGUI()} method afterward. If the user is on the third fragment and continues next,
     * the registration process will begin instead.
     *
     * @param newFragmentIndex The new fragment index to switch to.
     */
    private fun switchFragment(newFragmentIndex: Int) {
        if (newFragmentIndex > this.mCurrentFragmentIndex) {
            try {
                (this.mFragmentSet[this.mCurrentFragmentIndex] as Verifiable).verifyInformation()
            } catch (e: RuntimeException) {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

        when (newFragmentIndex) {
            1, 2, 3 -> {
                updateFragmentGUI(newFragmentIndex)
            }

            4 -> {
                register({
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                    {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    })
            }

            else -> {
                Toast.makeText(this, "Error: You cannot go beyond the scope of the fragments!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    /**
     * Method called to update both the fragment and fragment index.
     *
     * @param newFragmentIndex The new fragment index to switch to.
     */
    private fun updateFragmentGUI(newFragmentIndex: Int) {
        supportFragmentManager.commit {
            replace(R.id.fgRegisterInformationEntry, mFragmentSet[newFragmentIndex]!!)
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

    /**
     * Method called by the activity after the user has inputted all necessary fields, the inputs have been verified,
     * and the user proceeds to register.
     *
     * @param onSuccess A lambda function containing the code to be executed upon a successful API query.
     * @param onError A lambda function containing the code to be executed upon a failed API query.
     */
    private fun register(onSuccess: (response: JSONObject) -> Unit, onError: (message: String) -> Unit) {
        val authenticationService = AuthenticationService(this)
        authenticationService.register(
            (this.mFragmentSet[1] as ContactInformationEntryFragment).getFirstName(),
            (this.mFragmentSet[1] as ContactInformationEntryFragment).getLastName(),
            (this.mFragmentSet[1] as ContactInformationEntryFragment).getEmailAddress(),
            (this.mFragmentSet[1] as ContactInformationEntryFragment).getPhoneNumber(),

            (this.mFragmentSet[2] as AddressEntryFragment).getPostalAddress(),
            (this.mFragmentSet[2] as AddressEntryFragment).getCity(),
            (this.mFragmentSet[2] as AddressEntryFragment).getState(),
            (this.mFragmentSet[2] as AddressEntryFragment).getZip(),

            (this.mFragmentSet[3] as UsernamePasswordEntryFragment).getUsername(),
            (this.mFragmentSet[3] as UsernamePasswordEntryFragment).getPassword(),
            onSuccess,
            onError
        )
    }

}