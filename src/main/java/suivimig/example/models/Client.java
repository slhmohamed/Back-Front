package suivimig.example.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "Client",
        uniqueConstraints ={
                @UniqueConstraint(name= "Client_name",columnNames = "name")
        }
)

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;



    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,optional = false) //updated recently by chahra
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client() {
        super();
    }

    public Client orElse(Client client) {
        return new Client();
    }
}