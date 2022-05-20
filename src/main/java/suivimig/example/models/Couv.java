package suivimig.example.models;

import javax.persistence.*;

@Entity
public class Couv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="valeur")
    private String valeur;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Couv() {
        super();
    }

    public Couv(String valeur) {
        this.valeur = valeur;
    }
}
