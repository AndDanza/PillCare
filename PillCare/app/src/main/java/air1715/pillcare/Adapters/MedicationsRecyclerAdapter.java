package air1715.pillcare.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import air1715.database.entiteti.Lijek;
import air1715.database.entiteti.Proizvodac;


/**
 * The type Medications recycler adapter.
 */
public class MedicationsRecyclerAdapter extends RecyclerView.Adapter<MedicationsViewHolder> {
    private List<Lijek> medications;
    private List<Proizvodac> companies;
    private final int layout_id;

    /**
     * Instantiates a new Medications recycler adapter.
     *
     * @param layoutID    the layout id
     * @param medications the medications
     * @param companies   the companies
     */
    public MedicationsRecyclerAdapter(final int layoutID, final List<Lijek> medications, final List<Proizvodac> companies) {
        this.medications = medications;
        this.companies = companies;
        this.layout_id = layoutID;
    }

    @Override
    public MedicationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View medicationView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        MedicationsViewHolder viewHolder = new MedicationsViewHolder(medicationView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MedicationsViewHolder holder, int position) {
        holder.BindViewWithData(medications.get(position), GetMatchingComapny(medications.get(position)));
    }

    private Proizvodac GetMatchingComapny(Lijek medication) {
        Proizvodac pharmaCompany = null;

        for(Proizvodac company : companies){
            if(medication.getProizvodacId() == company.getId()){
                pharmaCompany = company;
            }
        }

        return pharmaCompany;
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layout_id;
    }

    /**
     * Clear data.
     */
    public void ClearData() {
        int size = this.medications.size();
        this.medications.clear();
        notifyItemRangeRemoved(0, size);
    }
}
