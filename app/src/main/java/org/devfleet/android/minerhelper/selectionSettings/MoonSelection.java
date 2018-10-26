package org.devfleet.android.minerhelper.selectionSettings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import org.devfleet.android.minerhelper.R;


public class MoonSelection extends AppCompatActivity {

    private SharedPreferences sharedPref;

    private CheckBox VariantsMoon;
    private CheckBox Bitumens;
    private CheckBox Coesite;
    private CheckBox Sylvite;
    private CheckBox Zeolites;
    private CheckBox Cobaltite;
    private CheckBox Euxenite;
    private CheckBox Scheelite;
    private CheckBox Titanite;
    private CheckBox Chromite;
    private CheckBox Otavite;
    private CheckBox Sperrylite;
    private CheckBox Vanadinite;
    private CheckBox Carnotite;
    private CheckBox Cinnabar;
    private CheckBox Pollucite;
    private CheckBox Zircon;
    private CheckBox Loparite;
    private CheckBox Monazite;
    private CheckBox Xenotime;
    private CheckBox Ytterbite;

    private View v;

    private CheckBox Base;
    private CheckBox R8;
    private CheckBox R16;
    private CheckBox R32;
    private CheckBox R64;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ore_cmoon);
        getViews();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        loadViews();
    }

    private void getViews() {
        VariantsMoon = findViewById(R.id.VariantsMoon);
        Bitumens = findViewById(R.id.Bitumens);
        Coesite = findViewById(R.id.Coesite);
        Sylvite = findViewById(R.id.Sylvite);
        Zeolites = findViewById(R.id.Zeolites);
        Cobaltite = findViewById(R.id.Cobaltite);
        Euxenite = findViewById(R.id.Euxenite);
        Scheelite = findViewById(R.id.Scheelite);
        Titanite = findViewById(R.id.Titanite);
        Chromite = findViewById(R.id.Chromite);
        Otavite = findViewById(R.id.Otavite);
        Sperrylite = findViewById(R.id.Sperrylite);
        Vanadinite = findViewById(R.id.Vanadinite);
        Carnotite = findViewById(R.id.Carnotite);
        Cinnabar = findViewById(R.id.Cinnabar);
        Pollucite = findViewById(R.id.Pollucite);
        Zircon = findViewById(R.id.Zircon);
        Loparite = findViewById(R.id.Loparite);
        Monazite = findViewById(R.id.Monazite);
        Xenotime = findViewById(R.id.Xenotime);
        Ytterbite = findViewById(R.id.Ytterbite);
    }

    private void loadViews() {
        VariantsMoon.setChecked(sharedPref.getBoolean("VariantsMoon", false));
        Bitumens.setChecked(sharedPref.getBoolean("Bitumens", true));
        Coesite.setChecked(sharedPref.getBoolean("Coesite", true));
        Sylvite.setChecked(sharedPref.getBoolean("Sylvite", true));
        Zeolites.setChecked(sharedPref.getBoolean("Zeolites", true));
        Cobaltite.setChecked(sharedPref.getBoolean("Cobaltite", true));
        Euxenite.setChecked(sharedPref.getBoolean("Euxenite", true));
        Scheelite.setChecked(sharedPref.getBoolean("Scheelite", true));
        Titanite.setChecked(sharedPref.getBoolean("Titanite", true));
        Chromite.setChecked(sharedPref.getBoolean("Chromite", true));
        Otavite.setChecked(sharedPref.getBoolean("Otavite", true));
        Sperrylite.setChecked(sharedPref.getBoolean("Sperrylite", true));
        Vanadinite.setChecked(sharedPref.getBoolean("Vanadinite", true));
        Carnotite.setChecked(sharedPref.getBoolean("Carnotite", true));
        Cinnabar.setChecked(sharedPref.getBoolean("Cinnabar", true));
        Pollucite.setChecked(sharedPref.getBoolean("Pollucite", true));
        Zircon.setChecked(sharedPref.getBoolean("Zircon", true));
        Loparite.setChecked(sharedPref.getBoolean("Loparite", true));
        Monazite.setChecked(sharedPref.getBoolean("Monazite", true));
        Xenotime.setChecked(sharedPref.getBoolean("Xenotime", true));
        Ytterbite.setChecked(sharedPref.getBoolean("Ytterbite", true));
    }

    public void openSelection(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        v = inflater.inflate(R.layout.orebysystemmoon, null);
        builder.setView(v);
        dialog = builder.create();
        dialog.show();
        findButtons();
        checkCheckBox();
    }

    private void findButtons() {
        Base = v.findViewById(R.id.Base);
        R8 = v.findViewById(R.id.R8);
        R16 = v.findViewById(R.id.R16);
        R32 = v.findViewById(R.id.R32);
        R64 = v.findViewById(R.id.R64);
    }

    private void checkCheckBox() {
        Base.setChecked(sharedPref.getBoolean("MoonBase", true));
        R8.setChecked(sharedPref.getBoolean("Moon8", true));
        R16.setChecked(sharedPref.getBoolean("Moon16", true));
        R32.setChecked(sharedPref.getBoolean("Moon32", true));
        R64.setChecked(sharedPref.getBoolean("Moon64", true));
    }

    public void checkButton(View view) {

        SharedPreferences.Editor editor = sharedPref.edit();
        if ((Base == view)) {
            editor.putBoolean("MoonBase", Base.isChecked());
        }
        if ((R8 == view)) {
            editor.putBoolean("Moon8", R8.isChecked());
        }
        if ((R16 == view)) {
            editor.putBoolean("Moon16", R16.isChecked());
        }
        if ((R32 == view)) {
            editor.putBoolean("Moon32", R32.isChecked());
        }
        if ((R64 == view)) {
            editor.putBoolean("Moon64", R64.isChecked());
        }
        editor.commit();
    }

    public void apply(View view) {
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("Bitumens", false);
        editor.putBoolean("Coesite", false);
        editor.putBoolean("Sylvite", false);
        editor.putBoolean("Zeolites", false);
        editor.putBoolean("Cobaltite", false);
        editor.putBoolean("Euxenite", false);
        editor.putBoolean("Scheelite", false);
        editor.putBoolean("Titanite", false);
        editor.putBoolean("Chromite", false);
        editor.putBoolean("Otavite", false);
        editor.putBoolean("Sperrylite", false);
        editor.putBoolean("Vanadinite", false);
        editor.putBoolean("Carnotite", false);
        editor.putBoolean("Cinnabar", false);
        editor.putBoolean("Pollucite", false);
        editor.putBoolean("Zircon", false);
        editor.putBoolean("Loparite", false);
        editor.putBoolean("Monazite", false);
        editor.putBoolean("Xenotime", false);
        editor.putBoolean("Ytterbite", false);

        if (sharedPref.getBoolean("MoonBase", true)) {
            editor.putBoolean("Bitumens", true);
            editor.putBoolean("Coesite", true);
            editor.putBoolean("Sylvite", true);
            editor.putBoolean("Zeolites", true);
        }

        if (sharedPref.getBoolean("Moon8", true)) {
            editor.putBoolean("Cobaltite", true);
            editor.putBoolean("Euxenite", true);
            editor.putBoolean("Scheelite", true);
            editor.putBoolean("Titanite", true);
        }

        if (sharedPref.getBoolean("Moon16", true)) {
            editor.putBoolean("Chromite", true);
            editor.putBoolean("Otavite", true);
            editor.putBoolean("Sperrylite", true);
            editor.putBoolean("Vanadinite", true);
        }

        if (sharedPref.getBoolean("Moon32", true)) {
            editor.putBoolean("Carnotite", true);
            editor.putBoolean("Cinnabar", true);
            editor.putBoolean("Pollucite", true);
            editor.putBoolean("Zircon", true);
        }

        if (sharedPref.getBoolean("Moon64", true)) {
            editor.putBoolean("Loparite", true);
            editor.putBoolean("Monazite", true);
            editor.putBoolean("Xenotime", true);
            editor.putBoolean("Ytterbite", true);
        }


        editor.commit();
        dialog.cancel();
        loadViews();
    }

    public void setVariantsMoon(View mVariantsMoon) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("VariantsMoon", VariantsMoon.isChecked());
        editor.commit();
    }

    public void setBitumens(View mBitumens) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Bitumens", Bitumens.isChecked());
        editor.commit();
    }

    public void setCoesite(View mCoesite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Coesite", Coesite.isChecked());
        editor.commit();
    }

    public void setSylvite(View mSylvite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Sylvite", Sylvite.isChecked());
        editor.commit();
    }

    public void setZeolites(View mZeolites) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Zeolites", Zeolites.isChecked());
        editor.commit();
    }

    public void setCobaltite(View mCobaltite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Cobaltite", Cobaltite.isChecked());
        editor.commit();
    }

    public void setEuxenite(View mEuxenite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Euxenite", Euxenite.isChecked());
        editor.commit();
    }

    public void setScheelite(View mScheelite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Scheelite", Scheelite.isChecked());
        editor.commit();
    }

    public void setTitanite(View mTitanite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Titanite", Titanite.isChecked());
        editor.commit();
    }

    public void setChromite(View mChromite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Chromite", Chromite.isChecked());
        editor.commit();
    }

    public void setOtavite(View mOtavite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Otavite", Otavite.isChecked());
        editor.commit();
    }

    public void setSperrylite(View mSperrylite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Sperrylite", Sperrylite.isChecked());
        editor.commit();
    }

    public void setVanadinite(View mVanadinite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Vanadinite", Vanadinite.isChecked());
        editor.commit();
    }

    public void setCarnotite(View mCarnotite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Carnotite", Carnotite.isChecked());
        editor.commit();
    }

    public void setCinnabar(View mCinnabar) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Cinnabar", Cinnabar.isChecked());
        editor.commit();
    }

    public void setPollucite(View mPollucite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Pollucite", Pollucite.isChecked());
        editor.commit();
    }

    public void setZircon(View mZircon) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Zircon", Zircon.isChecked());
        editor.commit();
    }

    public void setLoparite(View mLoparite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Loparite", Loparite.isChecked());
        editor.commit();
    }

    public void setMonazite(View mMonazite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Monazite", Monazite.isChecked());
        editor.commit();
    }

    public void setXenotime(View mXenotime) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Xenotime", Xenotime.isChecked());
        editor.commit();
    }

    public void setYtterbite(View mYtterbite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Ytterbite", Ytterbite.isChecked());
        editor.commit();
    }

}
