package org.devfleet.android.minerhelper.systemSettings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.devfleet.android.minerhelper.R;

public class IceSystemSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_c);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new IceSystemSelectionFragment())
                .commit();
    }
}
