package models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@MappedSuperclass
public abstract class Osoba {
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

    String name;
    String lastName;
    int pesel;

    Osoba(String name, String lastName, int pesel)
    {
        this.name=name;
        this.lastName=lastName;
        this.pesel=pesel;
    }

    public Osoba() {

    }
    @Column(length = 255)
    public void setName(String name) {
        this.name = name;
    }
    @Column(length = 255)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(length = 9)
    public void setPesel(int pesel) {
        this.pesel = pesel;
    }
    @Basic
    public int getPesel() {
        return pesel;
    }
    @Basic
    public String getName() {
        return name;
    }
    @Basic
    public String getLastName() {
        return lastName;
    }
}
