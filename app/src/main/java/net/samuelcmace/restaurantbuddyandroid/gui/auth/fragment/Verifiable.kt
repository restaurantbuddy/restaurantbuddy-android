package net.samuelcmace.restaurantbuddyandroid.gui.auth.fragment

/**
 * Functional interface representing a fragment that has fields to be verified.
 */
@FunctionalInterface
interface Verifiable {

    /**
     * Interface method called to validate the fields of any fragment containing user input fields.
     */
    fun verifyInformation()

}