package net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import net.samuelcmace.restaurantbuddyandroid.R

class ContactInformationEntryFragment : Fragment(R.layout.fragment_contact_information_entry), Verifiable {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmailAddress: EditText
    private lateinit var etPhoneNumber: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        this.etFirstName = view.findViewById(R.id.etFirstName)
        this.etLastName = view.findViewById(R.id.etLastName)
        this.etEmailAddress = view.findViewById(R.id.etEmailAddress)
        this.etPhoneNumber = view.findViewById(R.id.etPhoneNumber)

    }

    private fun getFirstName() = etFirstName.text.toString()
    private fun getLastName() = etLastName.text.toString()
    private fun getEmailAddress() = etEmailAddress.text.toString()
    private fun getPhoneNumber() = etPhoneNumber.text.toString()

    override fun verifyInformation() {
        if (getFirstName().isEmpty())
            throw Exception("The first name field cannot be empty!")
        else if (getLastName().isEmpty())
            throw Exception("The last name field cannot be empty!")
        else if (getEmailAddress().isEmpty())
            throw Exception("The email address field cannot be empty!")
        else if (getPhoneNumber().isEmpty())
            throw Exception("The phone number field cannot be empty!")
    }

}