package net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import net.samuelcmace.restaurantbuddyandroid.R

/**
 * Fragment representing the contact information entry portion of the registration UI.
 */
class ContactInformationEntryFragment : Fragment(R.layout.fragment_contact_information_entry), Verifiable {

    /**
     * Reference object pointing to the first name entry field on the layout.
     */
    private lateinit var etFirstName: EditText

    /**
     * Reference object pointing to the last name entry field on the layout.
     */
    private lateinit var etLastName: EditText

    /**
     * Reference object pointing to the email address entry field on the layout.
     */
    private lateinit var etEmailAddress: EditText

    /**
     * Reference object pointing to the phone number entry field on the layout.
     */
    private lateinit var etPhoneNumber: EditText

    /**
     * Method called by the Android API after the fragment has been drawn.
     *
     * @param view The view being created by the fragment.
     * @param savedInstanceState The object to reference the previously-created fragment if it is being recreated.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        this.etFirstName = view.findViewById(R.id.etFirstName)
        this.etLastName = view.findViewById(R.id.etLastName)
        this.etEmailAddress = view.findViewById(R.id.etEmailAddress)
        this.etPhoneNumber = view.findViewById(R.id.etPhoneNumber)

    }

    /**
     * Method to retrieve the string value of the first name field.
     *
     * @return The string value of the first name field.
     */
    fun getFirstName() = etFirstName.text.toString()

    /**
     * Method to retrieve the string value of the last name field.
     *
     * @return The string value of the last name field.
     */
    fun getLastName() = etLastName.text.toString()

    /**
     * Method to retrieve the string value of the email address field.
     *
     * @return The string value of the email address field.
     */
    fun getEmailAddress() = etEmailAddress.text.toString()

    /**
     * Method to retrieve the string value of the phone number field.
     *
     * @return The string value of the phone number field.
     */
    fun getPhoneNumber() = etPhoneNumber.text.toString()

    /**
     * Method called to verify the fields contained on the fragment.
     */
    override fun verifyInformation() {
        if (getFirstName().isEmpty())
            throw RuntimeException("The first name field cannot be empty!")
        else if (getLastName().isEmpty())
            throw RuntimeException("The last name field cannot be empty!")
        else if (getEmailAddress().isEmpty())
            throw RuntimeException("The email address field cannot be empty!")
        else if (getPhoneNumber().isEmpty())
            throw RuntimeException("The phone number field cannot be empty!")
    }

}