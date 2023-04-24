package models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Klient")
public class Klient extends Osoba{

    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private int bankAccountNumber;

    @OneToMany(mappedBy = "klient",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Karnet> karnets = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    List<TreningPersonalny> personalTrainingList = new ArrayList<>();

    public Klient(String name, String lastName, int pesel, String login, String password, String email, int bankAccountNumber) {
        super(name, lastName, pesel);
        this.login=login;
        this.password=password;
        this.email=email;
        this.bankAccountNumber=bankAccountNumber;

    }
    public Klient(){ }

    @OneToMany(mappedBy = "klient",cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Karnet> getKarnets() {
        return karnets;
    }



    public void addTraining(TreningPersonalny training){
        this.personalTrainingList.add(training);
        training.setKlient(this);
    }

    public void setPersonalTrainingList(List<TreningPersonalny> personalTrainingList) {
        this.personalTrainingList = personalTrainingList;
    }

    public List<TreningPersonalny> getPersonalTrainingList() {
        return personalTrainingList;
    }


    public void setKarnets(List<Karnet> karnets) {
        this.karnets = karnets;
    }

    public void addCarnet(Karnet karnet)
    {
        this.karnets.add(karnet);
        karnet.setKlient(this);
    }

    @Basic
    @Column(length = 20)
    public String getLogin() {
        return login;
    }
    @Basic
    @Column(length = 20)
    public String getPassword() {
        return password;
    }
    @Basic
    public String getEmail() {
        return email;
    }
    @Basic
    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(String.format("Typ:\t"+this.getClass().getSimpleName() + "\n Imie:\t%s\n Nazwisko:\t%s\n pesel:\t%s\n login:\t%s\n email:\t%s\n", getName(), getLastName(), Integer.toString(getPesel()), this.getLogin(), getEmail())).toString();
    }
}
