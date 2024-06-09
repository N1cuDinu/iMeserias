package dinu.imeserias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reviewuri", schema = "imeserias")
public class Reviewuri {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idreview")
    private int idreview;
    @Basic
    @Column(name = "idanunt")
    private int idanunt;
    @Basic
    @Column(name = "stars")
    private int stars;
    @Basic
    @Column(name = "descrierereview")
    private String descrierereview;

    public int getIdreview() {
        return idreview;
    }

    public void setIdreview(int idreview) {
        this.idreview = idreview;
    }

    public int getIdanunt() {
        return idanunt;
    }

    public void setIdanunt(int idanunt) {
        this.idanunt = idanunt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescrierereview() {
        return descrierereview;
    }

    public void setDescrierereview(String descrierereview) {
        this.descrierereview = descrierereview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reviewuri that = (Reviewuri) o;

        if (idreview != that.idreview) return false;
        if (idanunt != that.idanunt) return false;
        if (stars != that.stars) return false;
        if (descrierereview != null ? !descrierereview.equals(that.descrierereview) : that.descrierereview != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idreview;
        result = 31 * result + idanunt;
        result = 31 * result + stars;
        result = 31 * result + (descrierereview != null ? descrierereview.hashCode() : 0);
        return result;
    }
}
