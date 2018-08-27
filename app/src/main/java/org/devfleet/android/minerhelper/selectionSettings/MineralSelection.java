package org.devfleet.android.minerhelper.selectionSettings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import org.devfleet.android.minerhelper.R;

public class MineralSelection extends AppCompatActivity {

    private SharedPreferences sharedPref;

    private CheckBox Tritanium;
    private CheckBox Pyerite;
    private CheckBox Mexallon;
    private CheckBox Isogen;
    private CheckBox Nocxium;
    private CheckBox Zydrine;
    private CheckBox Megacyte;
    private CheckBox Morphite;

    private View v;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ore_cmin);
        getViews();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        loadViews();
    }

    private void getViews() {
        Tritanium = findViewById(R.id.Tritanium);
        Pyerite = findViewById(R.id.Pyerite);
        Mexallon = findViewById(R.id.Mexallon);
        Isogen = findViewById(R.id.Isogen);
        Nocxium = findViewById(R.id.Nocxium);
        Zydrine = findViewById(R.id.Zydrine);
        Megacyte = findViewById(R.id.Megacyte);
        Morphite = findViewById(R.id.Morphite);
    }

    private void loadViews() {
        Tritanium.setChecked(sharedPref.getBoolean("Tritanium", true));
        Pyerite.setChecked(sharedPref.getBoolean("Pyerite", true));
        Mexallon.setChecked(sharedPref.getBoolean("Mexallon", true));
        Isogen.setChecked(sharedPref.getBoolean("Isogen", true));
        Nocxium.setChecked(sharedPref.getBoolean("Nocxium", true));
        Zydrine.setChecked(sharedPref.getBoolean("Zydrine", true));
        Megacyte.setChecked(sharedPref.getBoolean("Megacyte", true));
        Morphite.setChecked(sharedPref.getBoolean("Morphite", true));
    }

    public void setTritanium(View mArkonor) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Tritanium", Tritanium.isChecked());
        editor.commit();
    }

    public void setPyerite(View mBistot) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Pyerite", Pyerite.isChecked());
        editor.commit();
    }

    public void setMexallon(View mCrokite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Mexallon", Mexallon.isChecked());
        editor.commit();
    }

    public void setIsogen(View mDark_Ochre) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Isogen", Isogen.isChecked());
        editor.commit();
    }

    public void setNocxium(View mGneiss) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Nocxium", Nocxium.isChecked());
        editor.commit();
    }

    public void setZydrine(View mHedbergite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Zydrine", Zydrine.isChecked());
        editor.commit();
    }

    public void setMegacyte(View mHemorphite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Megacyte", Megacyte.isChecked());
        editor.commit();
    }

    public void setMorphite(View mJaspet) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Morphite", Morphite.isChecked());
        editor.commit();
    }
}
