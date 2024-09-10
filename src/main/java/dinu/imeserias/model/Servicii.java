package dinu.imeserias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Servicii {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idserviciu")
    private int idserviciu;

    @Column(name = "numeServiciu", nullable = false)
    private String numeServiciu;

    // Întrucât relația ManyToMany nu mai este relevantă, am eliminat setul de `Anunturi`

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Servicii servicii = (Servicii) o;

        if (idserviciu != servicii.idserviciu) return false;
        return numeServiciu != null ? numeServiciu.equals(servicii.numeServiciu) : servicii.numeServiciu == null;
    }

    @Override
    public int hashCode() {
        int result = idserviciu;
        result = 31 * result + (numeServiciu != null ? numeServiciu.hashCode() : 0);
        return result;
    }
}
