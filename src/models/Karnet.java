package models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Karnet")
public class Karnet {
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

    @NotNull
    private boolean paid;
    @NotNull
    private LocalDateTime sinceWhen;
    @NotNull
    private LocalDateTime sinceTo;
    @NotNull
    private int monthlyCost;

    @ManyToOne
    private Klient klient;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Silownia> carnetsGymCarnets = new ArrayList<>();

    public Karnet(boolean paid, LocalDateTime sinceWhen, LocalDateTime sinceTo, int monthlyCost){
        this.paid=paid;
        this.sinceWhen=sinceWhen;
        this.sinceTo=sinceTo;
        this.monthlyCost=monthlyCost;
    }

    public Karnet(){

    }

    public List<Silownia> getCarnetsGymKarnets() {
        return carnetsGymCarnets;
    }

    public void setCarnetsGymKarnets(List<Silownia> carnetsGymKarnets) {
        this.carnetsGymCarnets = carnetsGymKarnets;
    }

    public void addcarnetGymKarnet(Silownia silownia){
        this.carnetsGymCarnets.add(silownia);
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
    @Basic
    public boolean getPaid() {
        return paid;
    }
    @Basic
    public LocalDateTime getSinceTo() {
        return sinceTo;
    }
    @Basic
    public LocalDateTime getSinceWhen() {
        return sinceWhen;
    }
    @Basic
    public int getMonthlyCost() {
        return monthlyCost;
    }


    @Override
    public String toString() {
        return new StringBuilder().append(String.format("Typ: "+this.getClass().getSimpleName() + "\n Opłacone:\t%s\n Od kiedy:\t%s\n Do kiedy:\t%s\n Koszt miesięczny:\t%s\n", String.valueOf(getPaid()), getSinceWhen().toString(), getSinceTo().toString(), Integer.toString(getMonthlyCost()))).toString();
    }
}
