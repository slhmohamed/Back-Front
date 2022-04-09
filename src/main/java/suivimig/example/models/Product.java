package suivimig.example.models;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Product{
    @Id
    private Long id_prod;

    @Column(name = "name")
    private String name;

    /*-----------------------------------------------------------*/
    /*
    @OneToMany(targetEntity=Proc.class, mappedBy ="product")
    private List<Proc> procedures= new ArrayList<Proc>(); */

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_procs",
            joinColumns = { @JoinColumn(name = "id_proc") },
            inverseJoinColumns = { @JoinColumn(name = "id_prod") }
    )
    Set<Proc> procs = new HashSet<>();





    public Product() {
    }
}
