package org.devfleet.android.minerhelper.systemSettings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import org.devfleet.android.minerhelper.R;


public class SystemCFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.systemprefs);
    }
}
