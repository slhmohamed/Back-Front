package suivimig.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name  ="proc")

public class Proc{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proc_generator")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProfilProc profilProc;

    private String traitement;
    private String sprint;
    private String jiraDev;
    private String quiDev;
    private String jiraQa;
    private String quiQa;
    private String jiraJas;
    private String commentaireJas;
    private String dateMAJ;
    private String commentaireMig;


    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Couv couverture;


    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Statut statutDev;


    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Statut statutQa;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Statut statutTrad;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Statut statutJasper;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Scrum scrum;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra

    private PrdSp prdSp;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Priorite prio;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Priorite prioJas;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade =   CascadeType.ALL ,
            mappedBy = "procs")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Statut getStatutDev() {
        return statutDev;
    }

    public void setStatutDev(Statut statutDev) {
        this.statutDev = statutDev;
    }

    public Statut getStatutQa() {
        return statutQa;
    }

    public void setStatutQa(Statut statutQa) {
        this.statutQa = statutQa;
    }

    public Statut getStatutTrad() {
        return statutTrad;
    }

    public void setStatutTrad(Statut statutTrad) {
        this.statutTrad = statutTrad;
    }

    public Statut getStatutJasper() {
        return statutJasper;
    }

    public void setStatutJasper(Statut statutJasper) {
        this.statutJasper = statutJasper;
    }

    public Scrum getScrum() {
        return scrum;
    }

    public void setScrum(Scrum scrum) {
        this.scrum = scrum;
    }

    public PrdSp getPrdSp() {
        return prdSp;
    }

    public void setPrdSp(PrdSp prdSp) {
        this.prdSp = prdSp;
    }

    public Priorite getPrio() {
        return prio;
    }

    public void setPrio(Priorite prio) {
        this.prio = prio;
    }

    public Priorite getPrioJas() {
        return prioJas;
    }

    public void setPrioJas(Priorite prioJas) {
        this.prioJas = prioJas;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getJiraDev() {
        return jiraDev;
    }

    public void setJiraDev(String jiraDev) {
        this.jiraDev = jiraDev;
    }

    public String getQuiDev() {
        return quiDev;
    }

    public void setQuiDev(String quiDev) {
        this.quiDev = quiDev;
    }

    public String getJiraQa() {
        return jiraQa;
    }

    public void setJiraQa(String jiraQa) {
        this.jiraQa = jiraQa;
    }

    public String getQuiQa() {
        return quiQa;
    }

    public void setQuiQa(String quiQa) {
        this.quiQa = quiQa;
    }

    public String getJiraJas() {
        return jiraJas;
    }

    public void setJiraJas(String jiraJas) {
        this.jiraJas = jiraJas;
    }

    public String getCommentaireJas() {
        return commentaireJas;
    }

    public void setCommentaireJas(String commentaireJas) {
        this.commentaireJas = commentaireJas;
    }

    public String getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(String dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public String getCommentaireMig() {
        return commentaireMig;
    }

    public void setCommentaireMig(String commentaireMig) {
        this.commentaireMig = commentaireMig;
    }

    public ProfilProc getProfilProc() {
        return profilProc;
    }

    public void setProfilProc(ProfilProc profilProc) {
        this.profilProc = profilProc;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public Couv getCouverture() {
        return couverture;
    }

    public void setCouverture(Couv couverture) {
        this.couverture = couverture;
    }



    public Proc() {
        super();
    }


    @Override
    public String toString() {
        return "Proc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profilProc=" + profilProc +
                ", traitement='" + traitement + '\'' +
                ", sprint='" + sprint + '\'' +
                ", jiraDev='" + jiraDev + '\'' +
                ", quiDev='" + quiDev + '\'' +
                ", jiraQa='" + jiraQa + '\'' +
                ", quiQa='" + quiQa + '\'' +
                ", jiraJas='" + jiraJas + '\'' +
                ", commentaireJas='" + commentaireJas + '\'' +
                ", dateMAJ='" + dateMAJ + '\'' +
                ", commentaireMig='" + commentaireMig + '\'' +
                ", couverture=" + couverture +
                ", statutDev=" + statutDev +
                ", statutQa=" + statutQa +
                ", statutTrad=" + statutTrad +
                ", statutJasper=" + statutJasper +
                ", scrum=" + scrum +
                ", prdSp=" + prdSp +
                ", prio=" + prio +
                ", prioJas=" + prioJas +
                '}';
    }
}
