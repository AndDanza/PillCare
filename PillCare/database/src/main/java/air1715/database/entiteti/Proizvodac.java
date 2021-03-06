package air1715.database.entiteti;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrea on 30.10.2017.
 */
@Table(database = NaslovnicaBazePodataka.class)

public class Proizvodac extends BaseModel implements Serializable {
    @PrimaryKey
    @Column int id;

    @Column String naziv;

    @Column String slika;

    public Proizvodac() {
    }

    public Proizvodac(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt("id");
        this.naziv = jsonObject.getString("naziv");
        this.slika = jsonObject.getString("slika");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public List<Proizvodac> getAll(){
        return SQLite.select().from(Proizvodac.class).queryList();
    }
}
