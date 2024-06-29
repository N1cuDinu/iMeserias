package dinu.imeserias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reviewuri {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idreview")
    private int idreview;
    @Basic
    @Column(name = "idanunt")
    private int idanunt;
    @Basic
    @Column(name = "idUser")
    private int idUser;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

        Reviewuri reviewuri = (Reviewuri) o;

        if (idreview != reviewuri.idreview) return false;
        if (idanunt != reviewuri.idanunt) return false;
        if (idUser != reviewuri.idUser) return false;
        if (stars != reviewuri.stars) return false;
        if (descrierereview != null ? !descrierereview.equals(reviewuri.descrierereview) : reviewuri.descrierereview != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idreview;
        result = 31 * result + idanunt;
        result = 31 * result + idUser;
        result = 31 * result + stars;
        result = 31 * result + (descrierereview != null ? descrierereview.hashCode() : 0);
        return result;
    }
}
