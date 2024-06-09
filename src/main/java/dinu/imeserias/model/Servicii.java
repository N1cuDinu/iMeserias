package dinu.imeserias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "servicii", schema = "imeserias")
public class Servicii {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idserviciu")
    private int idserviciu;
    @Basic
    @Column(name = "numeServiciu")
    private String numeServiciu;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Servicii that = (Servicii) o;

        if (idserviciu != that.idserviciu) return false;
        if (numeServiciu != null ? !numeServiciu.equals(that.numeServiciu) : that.numeServiciu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idserviciu;
        result = 31 * result + (numeServiciu != null ? numeServiciu.hashCode() : 0);
        return result;
    }
}
