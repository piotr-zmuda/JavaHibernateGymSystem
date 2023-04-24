package models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Zajecia")
public class Zajecia {
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

    LocalDateTime dataRozpoczecia;
    LocalDateTime godzinaRozpoczecia;
    LocalDateTime godzinaZakonczenia;
    boolean czySieOdbyly;

    public Zajecia(LocalDateTime dataRozpoczecia, LocalDateTime godzinaRozpoczecia, LocalDateTime godzinaZakonczenia, boolean czySieOdbyly)
    {
        this.dataRozpoczecia=dataRozpoczecia;
        this.godzinaRozpoczecia=godzinaRozpoczecia;
        this.godzinaZakonczenia=godzinaZakonczenia;
        this.czySieOdbyly=czySieOdbyly;
    }
    public Zajecia(){}

    public LocalDateTime getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public LocalDateTime getGodzinaRozpoczecia() {
        return godzinaRozpoczecia;
    }

    public LocalDateTime getGodzinaZakonczenia() {
        return godzinaZakonczenia;
    }

    public void setCzySieOdbyly(boolean czySieOdbyly) {
        this.czySieOdbyly = czySieOdbyly;
    }

    public void setDataRozpoczecia(LocalDateTime dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public void setGodzinaRozpoczecia(LocalDateTime godzinaRozpoczecia) {
        this.godzinaRozpoczecia = godzinaRozpoczecia;
    }

    public void setGodzinaZakonczenia(LocalDateTime godzinaZakonczenia) {
        this.godzinaZakonczenia = godzinaZakonczenia;
    }
}
