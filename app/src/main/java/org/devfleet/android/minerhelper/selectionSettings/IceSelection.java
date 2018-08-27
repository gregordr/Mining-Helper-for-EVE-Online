package org.devfleet.android.minerhelper.selectionSettings;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import org.devfleet.android.minerhelper.R;

import static android.support.v4.view.ViewCompat.jumpDrawablesToCurrentState;

public class IceSelection extends AppCompatActivity {

    private SharedPreferences sharedPref;

    private CheckBox Clear_Icicle;
    private CheckBox White_Glaze;
    private CheckBox Blue_Ice;
    private CheckBox Glacial_Mass;
    private CheckBox Enriched_Clear_Icicle;
    private CheckBox Pristine_White_Glaze;
    private CheckBox Thick_Blue_Ice;
    private CheckBox Smooth_Glacial_Mass;
    private CheckBox Glare_Crust;
    private CheckBox Dark_Glitter;
    private CheckBox Gelidus;
    private CheckBox Krystallos;

    private View v;

    private Button Amarr;
    private Button Caldari;
    private Button Minmatar;
    private Button All;
    private Button Gallente;

    private CheckBox s10;
    private CheckBox s03;
    private CheckBox s01;
    private CheckBox s00;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ore_cice);
        getViews();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        loadViews();
    }

    private void getViews() {
        Clear_Icicle = findViewById(R.id.Clear_Icicle);
        White_Glaze = findViewById(R.id.White_Glaze);
        Blue_Ice = findViewById(R.id.Blue_Ice);
        Glacial_Mass = findViewById(R.id.Glacial_Mass);
        Enriched_Clear_Icicle = findViewById(R.id.Enriched_Clear_Icicle);
        Pristine_White_Glaze = findViewById(R.id.Pristine_White_Glaze);
        Thick_Blue_Ice = findViewById(R.id.Thick_Blue_Ice);
        Smooth_Glacial_Mass = findViewById(R.id.Smooth_Glacial_Mass);
        Glare_Crust = findViewById(R.id.Glare_Crust);
        Dark_Glitter = findViewById(R.id.Dark_Glitter);
        Gelidus = findViewById(R.id.Gelidus);
        Krystallos = findViewById(R.id.Krystallos);
    }

    private void loadViews() {
        Clear_Icicle.setChecked(sharedPref.getBoolean("Clear_Icicle", true));
        White_Glaze.setChecked(sharedPref.getBoolean("White_Glaze", true));
        Blue_Ice.setChecked(sharedPref.getBoolean("Blue_Ice", true));
        Glacial_Mass.setChecked(sharedPref.getBoolean("Glacial_Mass", true));
        Enriched_Clear_Icicle.setChecked(sharedPref.getBoolean("Enriched_Clear_Icicle", true));
        Pristine_White_Glaze.setChecked(sharedPref.getBoolean("Pristine_White_Glaze", true));
        Thick_Blue_Ice.setChecked(sharedPref.getBoolean("Thick_Blue_Ice", true));
        Smooth_Glacial_Mass.setChecked(sharedPref.getBoolean("Smooth_Glacial_Mass", true));
        Glare_Crust.setChecked(sharedPref.getBoolean("Glare_Crust", true));
        Dark_Glitter.setChecked(sharedPref.getBoolean("Dark_Glitter", true));
        Gelidus.setChecked(sharedPref.getBoolean("Gelidus", true));
        Krystallos.setChecked(sharedPref.getBoolean("Krystallos", true));
    }

    private void findButtons() {
        Amarr = v.findViewById(R.id.toggleButtonA);
        Caldari = v.findViewById(R.id.toggleButtonC);
        Gallente = v.findViewById(R.id.toggleButtonG);
        Minmatar = v.findViewById(R.id.toggleButtonM);
        All = v.findViewById(R.id.toggleButtonAll);

        s10 = v.findViewById(R.id.s1_0);
        s03 = v.findViewById(R.id.s0_3);
        s01 = v.findViewById(R.id.s0_1);
        s00 = v.findViewById(R.id.s0_0);
    }

    public void openSelection(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(IceSelection.this);
        LayoutInflater inflater = this.getLayoutInflater();
        v = inflater.inflate(R.layout.orebysystemice, null);
        builder.setView(v);
        dialog = builder.create();
        dialog.show();
        findButtons();
        paintOne();
        checkCheckBox();
    }

    public void selectEmpire(View view) {

        ViewCompat.setBackgroundTintList(Amarr, ColorStateList.valueOf(0xFFD6D7D7));
        ViewCompat.setBackgroundTintList(Caldari, ColorStateList.valueOf(0xFFD6D7D7));
        ViewCompat.setBackgroundTintList(Gallente, ColorStateList.valueOf(0xFFD6D7D7));
        ViewCompat.setBackgroundTintList(Minmatar, ColorStateList.valueOf(0xFFD6D7D7));
        ViewCompat.setBackgroundTintList(All, ColorStateList.valueOf(0xFFD6D7D7));
        ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(0xaaaaaaaa));
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Button", whatButton(view));
        editor.commit();
    }

    private String whatButton(View view) {
        if (view == Amarr) {
            return "A";
        }
        if (view == Caldari) {
            return "C";
        }
        if (view == Gallente) {
            return "G";
        }
        if (view == Minmatar) {
            return "M";
        }
        return "All";
    }

    private void paintOne() {
        String View = sharedPref.getString("Button", "All");
        switch (View) {
            case "A":
                ViewCompat.setBackgroundTintList(Amarr, ColorStateList.valueOf(0xaaaaaaaa));
                break;
            case "C":
                ViewCompat.setBackgroundTintList(Caldari, ColorStateList.valueOf(0xaaaaaaaa));
                break;
            case "G":
                ViewCompat.setBackgroundTintList(Gallente, ColorStateList.valueOf(0xaaaaaaaa));
                break;
            case "M":
                ViewCompat.setBackgroundTintList(Minmatar, ColorStateList.valueOf(0xaaaaaaaa));
                break;
            case "All":
                ViewCompat.setBackgroundTintList(All, ColorStateList.valueOf(0xaaaaaaaa));
                break;
        }
    }

    private void checkCheckBox() {
        String box = sharedPref.getString("CB", "s00");
        switch (box) {
            case "s10":
                s10.setChecked(true);
                break;
            case "s03":
                s03.setChecked(true);
                break;
            case "s01":
                s01.setChecked(true);
                break;
            case "s00":
                s00.setChecked(true);
                break;
        }
    }

    public void checkButton(View view) {

        SharedPreferences.Editor editor = sharedPref.edit();
        if ((s10 == view)) {
            editor.putString("CB", "s10");
        }
        if ((s03 == view)) {
            editor.putString("CB", "s03");
        }
        if ((s01 == view)) {
            editor.putString("CB", "s01");
        }
        if ((s00 == view)) {
            editor.putString("CB", "s00");
        }
        editor.commit();

        s10.setChecked(false);
        s03.setChecked(false);
        s01.setChecked(false);
        s00.setChecked(false);

        if (!(s10 == view)) {
            jumpDrawablesToCurrentState(s10);
        }
        if (!(s03 == view)) {
            jumpDrawablesToCurrentState(s03);
        }
        if (!(s01 == view)) {
            jumpDrawablesToCurrentState(s01);
        }
        if (!(s00 == view)) {
            jumpDrawablesToCurrentState(s00);
        }

        ((CheckBox) view).setChecked(true);

    }

    public void apply(View view) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Clear_Icicle", false);
        editor.putBoolean("White_Glaze", false);
        editor.putBoolean("Blue_Ice", false);
        editor.putBoolean("Glacial_Mass", false);
        editor.putBoolean("Enriched_Clear_Icicle", false);
        editor.putBoolean("Pristine_White_Glaze", false);
        editor.putBoolean("Thick_Blue_Ice", false);
        editor.putBoolean("Smooth_Glacial_Mass", false);
        editor.putBoolean("Glare_Crust", false);
        editor.putBoolean("Dark_Glitter", false);
        editor.putBoolean("Gelidus", false);
        editor.putBoolean("Krystallos", false);

        switch (sharedPref.getString("CB", "s00")) {
            case "s10":
                switch (sharedPref.getString("Button", "All")) {
                    case "A":
                        editor.putBoolean("Clear_Icicle", true);
                        break;
                    case "C":
                        editor.putBoolean("White_Glaze", true);
                        break;
                    case "M":
                        editor.putBoolean("Blue_Ice", true);
                        break;
                    case "G":
                        editor.putBoolean("Glacial_Mass", true);
                        break;
                    case "All":
                        editor.putBoolean("Clear_Icicle", true);
                        editor.putBoolean("White_Glaze", true);
                        editor.putBoolean("Blue_Ice", true);
                        editor.putBoolean("Glacial_Mass", true);
                        break;
                }
                break;
            case "s03":
                switch (sharedPref.getString("Button", "All")) {
                    case "A":
                        editor.putBoolean("Clear_Icicle", true);
                        editor.putBoolean("Glare_Crust", true);
                        break;
                    case "C":
                        editor.putBoolean("White_Glaze", true);
                        editor.putBoolean("Glare_Crust", true);
                        break;
                    case "M":
                        editor.putBoolean("Blue_Ice", true);
                        editor.putBoolean("Glare_Crust", true);
                        break;
                    case "G":
                        editor.putBoolean("Glacial_Mass", true);
                        editor.putBoolean("Glare_Crust", true);
                        break;
                    case "All":
                        editor.putBoolean("Clear_Icicle", true);
                        editor.putBoolean("White_Glaze", true);
                        editor.putBoolean("Blue_Ice", true);
                        editor.putBoolean("Glacial_Mass", true);
                        editor.putBoolean("Glare_Crust", true);
                        break;
                }
                break;
            case "s01":
                switch (sharedPref.getString("Button", "All")) {
                    case "A":
                        editor.putBoolean("Clear_Icicle", true);
                        editor.putBoolean("Glare_Crust", true);
                        editor.putBoolean("Dark_Glitter", true);
                        break;
                    case "C":
                        editor.putBoolean("White_Glaze", true);
                        editor.putBoolean("Glare_Crust", true);
                        editor.putBoolean("Dark_Glitter", true);
                        break;
                    case "M":
                        editor.putBoolean("Blue_Ice", true);
                        editor.putBoolean("Glare_Crust", true);
                        editor.putBoolean("Dark_Glitter", true);
                        break;
                    case "G":
                        editor.putBoolean("Glacial_Mass", true);
                        editor.putBoolean("Glare_Crust", true);
                        break;
                    case "All":
                        editor.putBoolean("Clear_Icicle", true);
                        editor.putBoolean("White_Glaze", true);
                        editor.putBoolean("Dark_Glitter", true);
                        editor.putBoolean("Blue_Ice", true);
                        editor.putBoolean("Glacial_Mass", true);
                        editor.putBoolean("Glare_Crust", true);
                        break;
                }
                break;
            case "s00":
                switch (sharedPref.getString("Button", "All")) {
                    case "A":
                        editor.putBoolean("Clear_Icicle", true);
                        editor.putBoolean("Glare_Crust", true);
                        editor.putBoolean("Dark_Glitter", true);
                        editor.putBoolean("Gelidus", true);
                        editor.putBoolean("Krystallos", true);
                        editor.putBoolean("Enriched_Clear_Icicle", true);
                        break;
                    case "C":
                        editor.putBoolean("White_Glaze", true);
                        editor.putBoolean("Glare_Crust", true);
                        editor.putBoolean("Dark_Glitter", true);
                        editor.putBoolean("Gelidus", true);
                        editor.putBoolean("Krystallos", true);
                        editor.putBoolean("Pristine_White_Glaze", true);
                        break;
                    case "M":
                        editor.putBoolean("Blue_Ice", true);
                        editor.putBoolean("Glare_Crust", true);
                        editor.putBoolean("Dark_Glitter", true);
                        editor.putBoolean("Gelidus", true);
                        editor.putBoolean("Krystallos", true);
                        editor.putBoolean("Thick_Blue_Ice", true);
                        break;
                    case "G":
                        editor.putBoolean("Glacial_Mass", true);
                        editor.putBoolean("Glare_Crust", true);
                        editor.putBoolean("Gelidus", true);
                        editor.putBoolean("Krystallos", true);
                        editor.putBoolean("Smooth_Glacial_Mass", true);
                        break;
                    case "All":
                        editor.putBoolean("Clear_Icicle", true);
                        editor.putBoolean("White_Glaze", true);
                        editor.putBoolean("Gelidus", true);
                        editor.putBoolean("Krystallos", true);
                        editor.putBoolean("Smooth_Glacial_Mass", true);
                        editor.putBoolean("Enriched_Clear_Icicle", true);
                        editor.putBoolean("Pristine_White_Glaze", true);
                        editor.putBoolean("Thick_Blue_Ice", true);
                        editor.putBoolean("Dark_Glitter", true);
                        editor.putBoolean("Blue_Ice", true);
                        editor.putBoolean("Glacial_Mass", true);
                        editor.putBoolean("Glare_Crust", true);
                        break;
                }
                break;
        }
        editor.commit();
        dialog.cancel();
        loadViews();
    }


    public void setArkonor(View mArkonor) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Clear_Icicle", Clear_Icicle.isChecked());
        editor.commit();
    }

    public void setBistot(View mBistot) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("White_Glaze", White_Glaze.isChecked());
        editor.commit();
    }

    public void setCrokite(View mCrokite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Blue_Ice", Blue_Ice.isChecked());
        editor.commit();
    }

    public void setDark_Ochre(View mDark_Ochre) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Glacial_Mass", Glacial_Mass.isChecked());
        editor.commit();
    }

    public void setGneiss(View mGneiss) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Enriched_Clear_Icicle", Enriched_Clear_Icicle.isChecked());
        editor.commit();
    }

    public void setHedbergite(View mHedbergite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Pristine_White_Glaze", Pristine_White_Glaze.isChecked());
        editor.commit();
    }

    public void setHemorphite(View mHemorphite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Thick_Blue_Ice", Thick_Blue_Ice.isChecked());
        editor.commit();
    }

    public void setKernite(View mKernite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Smooth_Glacial_Mass", Smooth_Glacial_Mass.isChecked());
        editor.commit();
    }

    public void setOmber(View mOmber) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Glare_Crust", Glare_Crust.isChecked());
        editor.commit();
    }

    public void setPlagioclase(View mPlagioclase) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Dark_Glitter", Dark_Glitter.isChecked());
        editor.commit();
    }

    public void setPyroxeres(View mPyroxeres) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Gelidus", Gelidus.isChecked());
        editor.commit();
    }

    public void setScordite(View mScordite) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Krystallos", Krystallos.isChecked());
        editor.commit();
    }


}
