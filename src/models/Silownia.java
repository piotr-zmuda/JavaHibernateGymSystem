package models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Silownia")
public class Silownia {
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
    private String nazwa;
    private String adres;
    private String kodPocztowy;

    @OneToMany(fetch = FetchType.EAGER)
    List<Trener> trainers = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Karnet> gymGymKarnets = new ArrayList<>();

    public Silownia(String nazwa, String adres, String kodPocztowy){
        this.nazwa=nazwa;
        this.adres=adres;
        this.kodPocztowy=kodPocztowy;
    }

    public String getAdres() {
        return adres;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public List<Karnet> getGymGymKarnets() {
        return gymGymKarnets;
    }

    public void addGymKarnet(Karnet karnet){
        if(this.gymGymKarnets.contains(karnet))
        {
            this.gymGymKarnets.add(karnet);
        }else{
            System.out.println("carnet is already assigned to this gym");
        }
    }

    public void setTrainers(List<Trener> trainers) {
        this.trainers = trainers;
    }

    public List<Trener> getTrainers() {
        return trainers;
    }

    public void addTrainer(Trener trainer){
            this.trainers.add(trainer);
            trainer.setSilownia(this);
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setGymGymKarnets(List<Karnet> gymKarnets) {
        this.gymGymKarnets = gymKarnets;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public Silownia(){}

    @Override
    public String toString() {
        return new StringBuilder().append(String.format("Typ: "+this.getClass().getSimpleName() + "\n Nazwa:\t%s\n Adres:\t%s\n Kod Pocztowy:\t%s\n", getNazwa(), getAdres(), getKodPocztowy())).toString();

    }
}
