package org.devfleet.android.minerhelper;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SystemCgasFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.systemprefsgas);
    }
}
