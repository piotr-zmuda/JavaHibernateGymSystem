package models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "Sala")
public class Sala {
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

    int numerSali;
    int pojemnoscSaliWOsobach;

    public Sala(int numerSali, int pojemnoscSaliWOsobach)
    {
        this.numerSali=numerSali;
        this.pojemnoscSaliWOsobach=pojemnoscSaliWOsobach;
    }

    public Sala(){

    }

    public int getNumerSali() {
        return numerSali;
    }

    public void setNumerSali(int numerSali) {
        this.numerSali = numerSali;
    }

    public int getPojemnoscSaliWOsobach() {
        return pojemnoscSaliWOsobach;
    }

    public void setPojemnoscSaliWOsobach(int pojemnoscSaliWOsobach) {
        this.pojemnoscSaliWOsobach = pojemnoscSaliWOsobach;
    }
}
