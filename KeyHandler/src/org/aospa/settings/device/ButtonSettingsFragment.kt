/*
 * Copyright (C) 2021 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package co.aospa.settings.device

import android.os.Bundle
import android.view.MenuItem
import androidx.preference.Preference
import androidx.preference.PreferenceFragment

class ButtonSettingsFragment : PreferenceFragment() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.button_panel)
        activity.actionBar!!.setDisplayHomeAsUpEnabled(true)

        val positionKeyMap = mapOf(
            KeyHandler.POSITION_TOP to KeyHandler.ALERT_SLIDER_TOP_KEY,
            KeyHandler.POSITION_MIDDLE to KeyHandler.ALERT_SLIDER_MIDDLE_KEY,
            KeyHandler.POSITION_BOTTOM to KeyHandler.ALERT_SLIDER_BOTTOM_KEY,
        )

        positionKeyMap.forEach { (position, key) ->
            if (!KeyHandler.isPositionSupported(context, position)) {
                val unusupportedPref = findPreference<Preference>(key)
                unusupportedPref?.parent?.removePreference(unusupportedPref)
            }
        }
    }

    override fun addPreferencesFromResource(preferencesResId: Int) {
        super.addPreferencesFromResource(preferencesResId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                activity.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
