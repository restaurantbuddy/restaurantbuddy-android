package net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import net.samuelcmace.restaurantbuddyandroid.R

class AddressEntryFragment : Fragment(R.layout.fragment_address_entry), Verifiable {

    private lateinit var etPostalAddress: EditText
    private lateinit var etCity: EditText
    private lateinit var etState: EditText
    private lateinit var etZip: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        this.etPostalAddress = view.findViewById(R.id.etPostalAddress)
        this.etCity = view.findViewById(R.id.etCity)
        this.etState = view.findViewById(R.id.etState)
        this.etZip = view.findViewById(R.id.etZip)

    }

    private fun getPostalAddress() = this.etPostalAddress.text.toString()
    private fun getCity() = this.etCity.text.toString()
    private fun getState() = this.etState.text.toString()
    private fun getZip() = this.etZip.text.toString()

    override fun verifyInformation() {
        if (getPostalAddress().isEmpty())
            throw Exception("The postal address field cannot be empty!")
        else if (getCity().isEmpty())
            throw Exception("The city field cannot be empty!")
        else if (getState().isEmpty())
            throw Exception("The state field cannot be empty!")
        else if (getZip().isEmpty())
            throw Exception("The zip code field cannot be empty!")
        else if (getState().length != 2)
            throw Exception("The state field must be two digits in length!")
        else if (getZip().length != 5)
            throw Exception("The zip code field must be five digits in length!")
    }

}
