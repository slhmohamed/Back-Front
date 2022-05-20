package suivimig.example.models;

import javax.persistence.*;

@Entity
public class ProfilProc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="valeur")
    private String valeur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public ProfilProc() {
        super();
    }

    public ProfilProc(String valeur) {
        this.valeur = valeur;
    }
}
