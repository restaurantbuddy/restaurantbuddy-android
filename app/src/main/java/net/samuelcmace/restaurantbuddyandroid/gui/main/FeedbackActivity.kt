package net.samuelcmace.restaurantbuddyandroid.gui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import net.samuelcmace.restaurantbuddyandroid.R

/**
 * The activity on which the user can provide developer feedback (through the Firebase API).
 */
class FeedbackActivity : AppCompatActivity() {

    /**
     * Method called by the Android API after the UI has been drawn.
     *
     * @param savedInstanceState The object to reference the previously-created activity if it is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
    }

    /**
     * Method called by the Android API to load the menu bar for the activity.
     *
     * @param menu The menu to be inflated.
     * @return A boolean containing the result of the menu inflation process.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_feedback, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Method called by the Android API after a menu item has been selected.
     *
     * @param item The menu item that was selected.
     * @return A boolean containing the result of the menu item selection process.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itBack -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
