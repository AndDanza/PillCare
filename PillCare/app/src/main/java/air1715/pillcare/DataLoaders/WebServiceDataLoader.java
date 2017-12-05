package air1715.pillcare.DataLoaders;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import air1715.database.entiteti.Korisnik;
import air1715.database.entiteti.Lijek;
import air1715.database.entiteti.Pregled;
import air1715.database.entiteti.Proizvodac;
import air1715.database.entiteti.Terapija;
import air1715.pillcare.Utils.HttpUtils;

/**
 * Created by Andrea on 29.11.2017.
 */

public class WebServiceDataLoader implements DataLoader {

    public WebServiceDataLoader() {
    }

    @Override
    public Object GetData(String dataType, Korisnik user) {
        Object returnData = null;

        switch (dataType) {
            case "medications":
                returnData = (Object) GetMedications();
                break;
            case "therapies":
                returnData = (Object) GetTherapies();
                break;
            case "pharmaCompanies":
                returnData = (Object) GetPharmaCompanies();
                break;
            case "appointments":
                returnData = (Object) GetAppointments(user);
                break;
        }

        return returnData;
    }

    @Override
    public List<Lijek> GetMedications() {
        Map params = new HashMap<String, String>();
        params.put("type", "all");
        JSONArray response = HttpUtils.sendGetRequestArray(params, "https://pillcare.000webhostapp.com/lijekovi.php");
        List<Lijek> medications = new ArrayList<Lijek>();
        try {
            if(response != null) {
                Log.d("response", "response razlicit od null medications ");
                medications = getMedicationsJSON(medications, response);
            }
            else {
                Log.d("null", "null u response-u medications");
                return null;
            }
        }
        catch (JSONException e) {
            System.out.println("JsonExceptionMedications. " + e.getLocalizedMessage());
        }

        return medications;

    }

    private List<Lijek> getMedicationsJSON(List<Lijek> medications, JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            medications.add(new Lijek(jsonobject));
        }

        return medications;
    }

    @Override
    public List<Terapija> GetTherapies(){
        return null;
    }

    @Override
    public List<Proizvodac> GetPharmaCompanies() {
        Map params = new HashMap<String, String>();
        params.put("type", "all");
        JSONArray response = HttpUtils.sendGetRequestArray(params, "https://pillcare.000webhostapp.com/proizvodac.php");
        List<Proizvodac> companies = new ArrayList<Proizvodac>();
        try {
            if(response != null) {
                Log.d("response", "response razlicit od null companies");
                companies = getCompaniesJSON(companies, response);
            }
            else {
                Log.d("null", "null u response-u companies");
                return null;
            }
        }
        catch (JSONException e) {
            System.out.println("JsonExceptionCompanies. " + e.getLocalizedMessage());
        }

        return companies;
    }

    private List<Proizvodac> getCompaniesJSON(List<Proizvodac> companies, JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            companies.add(new Proizvodac(jsonobject));
        }

        return companies;
    }

    @Override
    public List<Pregled> GetAppointments(Korisnik user) {
        Map params = new HashMap<String, String>();
        params.put("user", user.getKorisnickoIme());
        JSONArray response = HttpUtils.sendGetRequestArray(params, "https://pillcare.000webhostapp.com/pregled.php");
        List<Pregled> appointments = new ArrayList<Pregled>();
        try {
            if(response != null) {
                Log.d("response", "response razlicit od null pregledi");
                appointments = getAppointmentsJSON(appointments, response);
            }
            else {
                Log.d("null", "null u response-u appointments");
                return null;
            }
        }
        catch (JSONException e) {
            System.out.println("JsonExceptionAppointments. " + e.getLocalizedMessage());
        }

        return appointments;
    }

    private List<Pregled> getAppointmentsJSON(List<Pregled> appointments, JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            appointments.add(new Pregled(jsonobject));
        }

        return appointments;
    }
}
