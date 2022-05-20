package suivimig.example.models;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    /*-----------------------------------------------------------*/
    /*
    @OneToMany(targetEntity=Proc.class, mappedBy ="product")
    private List<Proc> procedures= new ArrayList<Proc>(); */
    /*
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_procs",
            joinColumns = { @JoinColumn(name = "id_proc") },
            inverseJoinColumns = { @JoinColumn(name = "id_prod") }
    )
    Set<Proc> procs = new HashSet<>(); */

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                   // CascadeType.PERSIST,
                    CascadeType.ALL,

                   // CascadeType.MERGE
            })

    @JoinTable(name = "product_procs",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "proc_id") })
    private Set<Proc> procs = new HashSet<>();



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

    public Product() {
        super();
    }

    public Product(String name) {
        this.name = name;
    }

    public Set<Proc> getProcs() {
        return procs;
    }

    public void setProcs(Set<Proc> procs) {
        this.procs = procs;
    }

    public void addProc(Proc proc) {
        this.procs.add(proc);
       // proc.getProducts().add(this);
    }



    public void removeProc(long procId) {
        System.out.println(procId);
        System.out.println("procId");
        Proc proc = this.procs.stream().filter(t -> t.getId() == procId).findFirst().orElse(null);
        System.out.println(proc);
        if (proc != null) this.procs.remove(proc);
        proc.getProducts().remove(this);
    }


}
