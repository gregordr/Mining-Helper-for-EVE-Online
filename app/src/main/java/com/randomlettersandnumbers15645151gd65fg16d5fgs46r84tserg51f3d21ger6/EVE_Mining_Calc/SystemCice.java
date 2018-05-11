package com.randomlettersandnumbers15645151gd65fg16d5fgs46r84tserg51f3d21ger6.EVE_Mining_Calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SystemCice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_c);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SystemCiceFragment())
                .commit();
    }
}
