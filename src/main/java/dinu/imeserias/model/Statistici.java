package dinu.imeserias.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Statistici {
    @Basic
    @Column(name = "idanunt")
    private int idanunt;
    @Basic
    @Column(name = "numarVizionari")
    private int numarVizionari;
    @Id
    private Long id;

    public int getIdanunt() {
        return idanunt;
    }

    public void setIdanunt(int idanunt) {
        this.idanunt = idanunt;
    }

    public int getNumarVizionari() {
        return numarVizionari;
    }

    public void setNumarVizionari(int numarVizionari) {
        this.numarVizionari = numarVizionari;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistici that = (Statistici) o;

        if (idanunt != that.idanunt) return false;
        if (numarVizionari != that.numarVizionari) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idanunt;
        result = 31 * result + numarVizionari;
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
