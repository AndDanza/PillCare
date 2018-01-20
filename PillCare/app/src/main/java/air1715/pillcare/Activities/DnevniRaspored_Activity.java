package air1715.pillcare.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import air1715.database.entiteti.Korisnik;
import air1715.database.entiteti.Pregled;
import air1715.database.entiteti.Terapija;
import air1715.pillcare.DataLoaders.DataLoadController;
import air1715.pillcare.R;
import air1715.pillcare.Utils.AlertHandler;
import air1715.pillcare.Utils.PopUpUtils;

public class DnevniRaspored_Activity extends AppCompatActivity {

    Korisnik loggedUser;
    List<Terapija> therapies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnevni_raspored);

        loggedUser = PrijavaActivity.getLoggedUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FillWithData();


    }


    /*
     * Metoda za učitavanje podataka sa web servisa te potom u sam view (xml)
     * Podaci se svrstavaju u dvije liste, sve terapije (therapies) te lista
     */
    private void FillWithData() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        DataLoadController dataControl = DataLoadController.GetInstance(manager);
        therapies = (List<Terapija>) dataControl.GetData("therapies", null);


        ListView listViewAppointments = (ListView) findViewById(R.id.listViewDnevniraspored);

        if (therapies != null) {
            ArrayAdapter<Terapija> adapter = new ArrayAdapter<Terapija>(this, android.R.layout.simple_list_item_1, therapies);
            listViewAppointments.setAdapter(adapter);
        } else {
            PopUpUtils.sendMessage(this, getString(R.string.therapies_empty));
        }
    }

}