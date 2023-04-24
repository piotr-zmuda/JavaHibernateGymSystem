package models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "Wyposazenie")
public class Wyposazenie {
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
    String typ;
    int ilosc;

    public Wyposazenie(String typ, int ilosc, type type){
            this.typ=typ;
            this.ilosc=ilosc;
            this.typ=type.toString();
    }
    public Wyposazenie(){

    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
enum type{sztanga,hantle,pilka_lekarska};
