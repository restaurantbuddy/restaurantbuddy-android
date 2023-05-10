package net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import net.samuelcmace.restaurantbuddyandroid.R

/**
 * Fragment representing the address entry portion of the registration UI.
 */
class AddressEntryFragment : Fragment(R.layout.fragment_address_entry), Verifiable {

    /**
     * Reference object pointing to the postal address entry field on the layout.
     */
    private lateinit var etPostalAddress: EditText

    /**
     * Reference object pointing to the city entry field on the layout.
     */
    private lateinit var etCity: EditText

    /**
     * Reference object pointing to the state entry field on the layout.
     */
    private lateinit var etState: EditText

    /**
     * Reference object pointing to the zip code entry field on the layout.
     */
    private lateinit var etZip: EditText

    /**
     * Method called by the Android API after the fragment has been drawn.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        this.etPostalAddress = view.findViewById(R.id.etPostalAddress)
        this.etCity = view.findViewById(R.id.etCity)
        this.etState = view.findViewById(R.id.etState)
        this.etZip = view.findViewById(R.id.etZip)

    }

    /**
     * Method to retrieve the string value of the postal address field.
     */
    fun getPostalAddress() = this.etPostalAddress.text.toString()

    /**
     * Method to retrieve the string value of the city field.
     */
    fun getCity() = this.etCity.text.toString()

    /**
     * Method to retrieve the string value of the state field.
     */
    fun getState() = this.etState.text.toString()

    /**
     * Method to retrieve the string value of the zip code field.
     */
    fun getZip() = this.etZip.text.toString()

    /**
     * Method called to verify the fields contained on the fragment.
     */
    override fun verifyInformation() {
        if (getPostalAddress().isEmpty())
            throw RuntimeException("The postal address field cannot be empty!")
        else if (getCity().isEmpty())
            throw RuntimeException("The city field cannot be empty!")
        else if (getState().isEmpty())
            throw RuntimeException("The state field cannot be empty!")
        else if (getZip().isEmpty())
            throw RuntimeException("The zip code field cannot be empty!")
        else if (getState().length != 2)
            throw RuntimeException("The state field must be two digits in length!")
        else if (getZip().length != 5)
            throw RuntimeException("The zip code field must be five digits in length!")
    }

}
