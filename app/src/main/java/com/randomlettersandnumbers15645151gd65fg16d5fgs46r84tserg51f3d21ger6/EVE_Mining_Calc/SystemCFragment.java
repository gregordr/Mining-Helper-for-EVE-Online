package com.randomlettersandnumbers15645151gd65fg16d5fgs46r84tserg51f3d21ger6.EVE_Mining_Calc;

import android.os.Bundle;
import android.preference.PreferenceFragment;


public class SystemCFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.systemprefs);
    }
}
