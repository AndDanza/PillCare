package air1715.pillcare.Adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import air1715.database.entiteti.Lijek;
import air1715.database.entiteti.Proizvodac;
import air1715.pillcare.DataLoaders.DataLoadController;
import air1715.pillcare.R;

/**
 * Created by Andrea on 06.12.2017.
 */

public class MedicationsTileRepresentation implements ModularRepresentation {
    List<Lijek> medications = null;
    List<Proizvodac> companies = null;
    View recycler;
    Context context;

    public MedicationsTileRepresentation(View recycler, Context context) {
        this.recycler = recycler;
        this.context = context;
    }

    @Override
    public void LoadData(List<Lijek> givenMedications, List<Proizvodac> givenComapnies) {
        this.medications = givenMedications;
        this.companies = givenComapnies;
    }

    @Override
    public void SetAdapter() {
        MedicationsRecyclerAdapter adapter =
                new MedicationsRecyclerAdapter(R.layout.medication_tile_item, medications, companies);
        RecyclerView recyclerView = (RecyclerView) recycler;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, Math.round(medications.size()/2)+1));
        //recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }
}
