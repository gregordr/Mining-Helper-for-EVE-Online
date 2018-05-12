package org.devfleet.android.minerhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SystemCgas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_c);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SystemCgasFragment())
                .commit();
    }
}
