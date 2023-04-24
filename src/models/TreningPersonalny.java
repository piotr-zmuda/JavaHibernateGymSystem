package models;

import com.sun.istack.NotNull;
import models.Klient;
import models.Trener;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Trening_personalny")
public class TreningPersonalny {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Trener trainer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Klient klient;

    private int cost;
    private LocalDateTime date;

    public TreningPersonalny(int cost, LocalDateTime date){
        this.date=date;
        this.cost=cost;
    }
    public TreningPersonalny(){}

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTrainer(Trener trainer) {
        this.trainer = trainer;
    }

    public Klient getKlient() {
        return klient;
    }

    public int getCost() {
        return cost;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Trener getTrainer() {
        return trainer;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(String.format("Typ: "+this.getClass().getSimpleName() + "\n Imie trenera:\t%s\n Nazwisko trenera:\t%s\n Telefon trenera:\t%s\n Imie klient:\t%s\n Nazwisko klient:\t%s\n Koszt:\t%s\n Data:\t%s\n", getTrainer().getName(),getTrainer().getLastName(),getTrainer().getPhoneNumber(), getKlient().getName(),getKlient().getLastName(), getCost(), getDate())).toString();
    }
}
