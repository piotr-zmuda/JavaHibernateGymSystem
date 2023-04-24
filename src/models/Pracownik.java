package models;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity(name="Pracownik")
public class Pracownik extends Osoba {

    private int pensja;

    public Pracownik(String name, String lastName, int pesel, int pensja) {
        super(name, lastName, pesel);
        this.pensja=pensja;
    }


    public void setPensja(int pensja) {
        this.pensja = pensja;
    }
    @Basic
    public int getPensja() {
        return pensja;
    }

    public Pracownik(){

    }
}
