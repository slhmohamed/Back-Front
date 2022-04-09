package suivimig.example.models;

import javax.persistence.*;

@Entity
public class Couv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String op;
    private String prog;
    private String couv;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    public String getCouv() {
        return couv;
    }

    public void setCouv(String couv) {
        this.couv = couv;
    }

    public Couv(String op, String prog, String couv) {
        this.op = op;
        this.prog = prog;
        this.couv = couv;
    }

    @Override
    public String toString() {
        return "Couv{" +
                "id=" + id +
                ", op='" + op + '\'' +
                ", prog='" + prog + '\'' +
                ", couv='" + couv + '\'' +
                '}';
    }

    public Couv() {
    super();
    }
}
