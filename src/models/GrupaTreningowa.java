package models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "GrupaTreningowa")
public class GrupaTreningowa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    typTreningu typTreningu;
    stopenZaawansowania stopenZaawansowania;

    public GrupaTreningowa(typTreningu typTreningu, stopenZaawansowania stopenZaawansowania)
    {
        this.typTreningu=typTreningu;
        this.stopenZaawansowania=stopenZaawansowania;
    }

    GrupaTreningowa(){}

    public models.stopenZaawansowania getStopenZaawansowania() {
        return stopenZaawansowania;
    }

    public void setStopenZaawansowania(models.stopenZaawansowania stopenZaawansowania) {
        this.stopenZaawansowania = stopenZaawansowania;
    }

    public void setTypTreningu(models.typTreningu typTreningu) {
        this.typTreningu = typTreningu;
    }

    public models.typTreningu getTypTreningu() {
        return typTreningu;
    }
}
enum typTreningu{Silowy,Cardio,Crossfit}
enum stopenZaawansowania{podstawowy, srednioZaawansowany, zaawansowany}
