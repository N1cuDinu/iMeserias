package dinu.imeserias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Servicii {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idserviciu")
    private int idserviciu;
    @Basic
    @Column(name = "numeServiciu")
    private String numeServiciu;
    @ManyToMany(mappedBy = "servicii")
    private Set<Anunturi> anunturi = new HashSet<>();
    public int getIdserviciu() {
        return idserviciu;
    }

    public void setIdserviciu(int idserviciu) {
        this.idserviciu = idserviciu;
    }

    public String getNumeServiciu() {
        return numeServiciu;
    }

    public void setNumeServiciu(String numeServiciu) {
        this.numeServiciu = numeServiciu;
    }
    public Set<Anunturi> getAnunturi() {
        return anunturi;
    }

    public void setAnunturi(Set<Anunturi> anunturi) {
        this.anunturi = anunturi;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Servicii servicii = (Servicii) o;

        if (idserviciu != servicii.idserviciu) return false;
        if (numeServiciu != null ? !numeServiciu.equals(servicii.numeServiciu) : servicii.numeServiciu != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idserviciu;
        result = 31 * result + (numeServiciu != null ? numeServiciu.hashCode() : 0);
        return result;
    }
}
