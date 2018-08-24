package org.devfleet.android.minerhelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;


public class OreCgas extends AppCompatActivity {

    private SharedPreferences sharedPref;

    private CheckBox C28;
    private CheckBox C32;
    private CheckBox C50;
    private CheckBox C60;
    private CheckBox C70;
    private CheckBox C72;
    private CheckBox C84;
    private CheckBox C320;
    private CheckBox C540;
    private CheckBox Amber_Cytoserocin;
    private CheckBox Amber_Mykoserocin;
    private CheckBox Azure_Cytoserocin;
    private CheckBox Azure_Mykoserocin;
    private CheckBox Celadon_Cytoserocin;
    private CheckBox Celadon_Mykoserocin;
    private CheckBox Golden_Cytoserocin;
    private CheckBox Golden_Mykoserocin;
    private CheckBox Lime_Cytoserocin;
    private CheckBox Lime_Mykoserocin;
    private CheckBox Malachite_Cytoserocin;
    private CheckBox Malachite_Mykoserocin;
    private CheckBox Vermillion_Cytoserocin;
    private CheckBox Vermillion_Mykoserocin;
    private CheckBox Viridian_Cytoserocin;
    private CheckBox Viridian_Mykoserocin;

    private View v;

    private CheckBox Wormhole;
    private CheckBox Cytoserocin;
    private CheckBox Mykoserocin;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ore_cgas);
        getViews();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        loadViews();
    }

    private void getViews() {
        C28 = findViewById(R.id.C28);
        C32 = findViewById(R.id.C32);
        C50 = findViewById(R.id.C50);
        C60 = findViewById(R.id.C60);
        C70 = findViewById(R.id.C70);
        C72 = findViewById(R.id.C72);
        C84 = findViewById(R.id.C84);
        C320 = findViewById(R.id.C320);
        C540 = findViewById(R.id.C540);

        Amber_Cytoserocin = findViewById(R.id.Amber_Cytoserocin);
        Azure_Cytoserocin = findViewById(R.id.Azure_Cytoserocin);
        Celadon_Cytoserocin = findViewById(R.id.Celadon_Cytoserocin);
        Golden_Cytoserocin = findViewById(R.id.Golden_Cytoserocin);
        Lime_Cytoserocin = findViewById(R.id.Lime_Cytoserocin);
        Malachite_Cytoserocin = findViewById(R.id.Malachite_Cytoserocin);
        Vermillion_Cytoserocin = findViewById(R.id.Vermillion_Cytoserocin);
        Viridian_Cytoserocin = findViewById(R.id.Viridian_Cytoserocin);

        Amber_Mykoserocin = findViewById(R.id.Amber_Mykoserocin);
        Azure_Mykoserocin = findViewById(R.id.Azure_Mykoserocin);
        Celadon_Mykoserocin = findViewById(R.id.Celadon_Mykoserocin);
        Golden_Mykoserocin = findViewById(R.id.Golden_Mykoserocin);
        Lime_Mykoserocin = findViewById(R.id.Lime_Mykoserocin);
        Malachite_Mykoserocin = findViewById(R.id.Malachite_Mykoserocin);
        Vermillion_Mykoserocin = findViewById(R.id.Vermillion_Mykoserocin);
        Viridian_Mykoserocin = findViewById(R.id.Viridian_Mykoserocin);
    }

    private void loadViews() {
        C28.setChecked(sharedPref.getBoolean("C28", true));
        C32.setChecked(sharedPref.getBoolean("C32", true));
        C50.setChecked(sharedPref.getBoolean("C50", true));
        C60.setChecked(sharedPref.getBoolean("C60", true));
        C70.setChecked(sharedPref.getBoolean("C70", true));
        C72.setChecked(sharedPref.getBoolean("C72", true));
        C84.setChecked(sharedPref.getBoolean("C84", true));
        C320.setChecked(sharedPref.getBoolean("C320", true));
        C540.setChecked(sharedPref.getBoolean("C540", true));

        Amber_Cytoserocin.setChecked(sharedPref.getBoolean("Amber_Cytoserocin", true));
        Azure_Cytoserocin.setChecked(sharedPref.getBoolean("Azure_Cytoserocin", true));
        Celadon_Cytoserocin.setChecked(sharedPref.getBoolean("Celadon_Cytoserocin", true));
        Golden_Cytoserocin.setChecked(sharedPref.getBoolean("Golden_Cytoserocin", true));
        Lime_Cytoserocin.setChecked(sharedPref.getBoolean("Lime_Cytoserocin", true));
        Malachite_Cytoserocin.setChecked(sharedPref.getBoolean("Malachite_Cytoserocin", true));
        Vermillion_Cytoserocin.setChecked(sharedPref.getBoolean("Vermillion_Cytoserocin", true));
        Viridian_Cytoserocin.setChecked(sharedPref.getBoolean("Viridian_Cytoserocin", true));
        
        Amber_Mykoserocin.setChecked(sharedPref.getBoolean("Amber_Mykoserocin", true));
        Azure_Mykoserocin.setChecked(sharedPref.getBoolean("Azure_Mykoserocin", true));
        Celadon_Mykoserocin.setChecked(sharedPref.getBoolean("Celadon_Mykoserocin", true));
        Golden_Mykoserocin.setChecked(sharedPref.getBoolean("Golden_Mykoserocin", true));
        Lime_Mykoserocin.setChecked(sharedPref.getBoolean("Lime_Mykoserocin", true));
        Malachite_Mykoserocin.setChecked(sharedPref.getBoolean("Malachite_Mykoserocin", true));
        Vermillion_Mykoserocin.setChecked(sharedPref.getBoolean("Vermillion_Mykoserocin", true));
        Viridian_Mykoserocin.setChecked(sharedPref.getBoolean("Viridian_Mykoserocin", true));
    }

    public void openSelection(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OreCgas.this);
        LayoutInflater inflater = this.getLayoutInflater();
        v = inflater.inflate(R.layout.orebysystemgas, null);
        builder.setView(v);
        dialog = builder.create();
        dialog.show();
        findButtons();
        checkCheckBox();
    }

    private void findButtons() {
        Mykoserocin = v.findViewById(R.id.Mykoserocin);
        Cytoserocin = v.findViewById(R.id.Cytoserocin);
        Wormhole = v.findViewById(R.id.Wormhole);
    }

    private void checkCheckBox() {
        Wormhole.setChecked(sharedPref.getBoolean("CBGW",true));
        Mykoserocin.setChecked(sharedPref.getBoolean("CBGM",true));
        Cytoserocin.setChecked(sharedPref.getBoolean("CBGC",true));
    }

    public void checkButton(View view) {

        SharedPreferences.Editor editor = sharedPref.edit(); //wrong cause mulptiple possible
        if ((Wormhole==view)) {editor.putBoolean("CBGW",Wormhole.isChecked());}
        if ((Mykoserocin==view)) {editor.putBoolean("CBGM",Mykoserocin.isChecked());}
        if ((Cytoserocin==view)) {editor.putBoolean("CBGC",Cytoserocin.isChecked());}
        editor.commit();


    }

    public void apply(View view) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C28",false);
        editor.putBoolean("C32",false);
        editor.putBoolean("C50",false);
        editor.putBoolean("C60",false);
        editor.putBoolean("C70",false);
        editor.putBoolean("C72",false);
        editor.putBoolean("C84",false);
        editor.putBoolean("C320",false);
        editor.putBoolean("C540",false);
        
        editor.putBoolean("Amber_Cytoserocin",false);
        editor.putBoolean("Azure_Cytoserocin",false);
        editor.putBoolean("Celadon_Cytoserocin",false);
        editor.putBoolean("Golden_Cytoserocin",false);
        editor.putBoolean("Lime_Cytoserocin",false);
        editor.putBoolean("Malachite_Cytoserocin",false);
        editor.putBoolean("Vermillion_Cytoserocin",false);
        editor.putBoolean("Viridian_Cytoserocin",false);
        
        editor.putBoolean("Amber_Mykoserocin",false);
        editor.putBoolean("Azure_Mykoserocin",false);
        editor.putBoolean("Celadon_Mykoserocin",false);
        editor.putBoolean("Golden_Mykoserocin",false);
        editor.putBoolean("Lime_Mykoserocin",false);
        editor.putBoolean("Malachite_Mykoserocin",false);
        editor.putBoolean("Vermillion_Mykoserocin",false);
        editor.putBoolean("Viridian_Mykoserocin",false);

        if(sharedPref.getBoolean("CBGW", true)) {
            editor.putBoolean("C28",true);
            editor.putBoolean("C32",true);
            editor.putBoolean("C50",true);
            editor.putBoolean("C60",true);
            editor.putBoolean("C70",true);
            editor.putBoolean("C72",true);
            editor.putBoolean("C84",true);
            editor.putBoolean("C320",true);
            editor.putBoolean("C540",true);
        }
        
        if(sharedPref.getBoolean("CBGC", true)){
            editor.putBoolean("Amber_Cytoserocin",true);
            editor.putBoolean("Azure_Cytoserocin",true);
            editor.putBoolean("Celadon_Cytoserocin",true);
            editor.putBoolean("Golden_Cytoserocin",true);
            editor.putBoolean("Lime_Cytoserocin",true);
            editor.putBoolean("Malachite_Cytoserocin",true);
            editor.putBoolean("Vermillion_Cytoserocin",true);
            editor.putBoolean("Viridian_Cytoserocin",true);
        }     
        
        if(sharedPref.getBoolean("CBGM", true)){
            editor.putBoolean("Amber_Mykoserocin",true);
            editor.putBoolean("Azure_Mykoserocin",true);
            editor.putBoolean("Celadon_Mykoserocin",true);
            editor.putBoolean("Golden_Mykoserocin",true);
            editor.putBoolean("Lime_Mykoserocin",true);
            editor.putBoolean("Malachite_Mykoserocin",true);
            editor.putBoolean("Vermillion_Mykoserocin",true);
            editor.putBoolean("Viridian_Mykoserocin",true);
        }
        
        editor.commit();
        dialog.cancel();
        loadViews();
    }



    public void setC28(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C28",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setC32(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C32",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setC50(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C50",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setC60(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C60",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setC70(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C70",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setC72(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C72",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setC84(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C84",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setC320(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C320",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setC540(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("C540",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setAmber_Cytoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Amber_Cytoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setAzure_Cytoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Azure_Cytoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setCeladon_Cytoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Celadon_Cytoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setGolden_Cytoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Golden_Cytoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setLime_Cytoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Lime_Cytoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setMalachite_Cytoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Malachite_Cytoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setVermillion_Cytoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Vermillion_Cytoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setViridian_Cytoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Viridian_Cytoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }


    public void setAmber_Mykoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Amber_Mykoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setAzure_Mykoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Azure_Mykoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setCeladon_Mykoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Celadon_Mykoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setGolden_Mykoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Golden_Mykoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setLime_Mykoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Lime_Mykoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setMalachite_Mykoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Malachite_Mykoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setVermillion_Mykoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Vermillion_Mykoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

    public void setViridian_Mykoserocin(View v) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Viridian_Mykoserocin",((CheckBox)v).isChecked());
        editor.commit();
    }

}
