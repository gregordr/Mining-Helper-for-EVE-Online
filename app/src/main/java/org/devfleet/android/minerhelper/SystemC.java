package org.devfleet.android.minerhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SystemC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_c);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SystemCFragment())
                .commit();
    }
}
