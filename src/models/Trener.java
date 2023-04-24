package models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Trener")
public class Trener extends Pracownik {

    int phoneNumber;

    int costOfTraining;
    @ManyToOne(fetch = FetchType.EAGER)
    Silownia silownia;

    @OneToMany(fetch = FetchType.EAGER)
    List<TreningPersonalny> personalTrainingList = new ArrayList<>();

    public Trener(String name, String lastName, int pesel, int pensja, int phoneNumber, int costOfTraining) {
        super(name, lastName, pesel, pensja);
        this.phoneNumber=phoneNumber;
        this.costOfTraining=costOfTraining;
    }

    public void addTraining(TreningPersonalny training){
        this.personalTrainingList.add(training);
        training.setTrainer(this);
    }

    public void setPersonalTrainingList(List<TreningPersonalny> personalTrainingList) {
        this.personalTrainingList = personalTrainingList;
    }

    public List<TreningPersonalny> getPersonalTrainingList() {
        return personalTrainingList;
    }

    public Silownia getSilownia() {
        return silownia;
    }

    public void setSilownia(Silownia silownia) {
        this.silownia = silownia;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCostOfTraining() {
        return costOfTraining;
    }

    public void setCostOfTraining(int costOfTraining) {
        this.costOfTraining = costOfTraining;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Trener(){}

    @Override
    public String toString() {
        return new StringBuilder().append(String.format("Typ: "+this.getClass().getSimpleName() + "\n Imie:\t%s\n Nazwisko:\t%s\n Pensja:\t%s\n Numer telefonu:\t%s\n", getName(),getLastName(),getPensja(), getPhoneNumber())).toString();
    }
}
