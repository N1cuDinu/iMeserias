package dinu.imeserias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "anunturi", schema = "imeserias")
public class Anunturi {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idanunt")
    private int idanunt;
    @Basic
    @Column(name = "iduser")
    private int iduser;
    @Basic
    @Column(name = "numeAnunt")
    private String numeAnunt;
    @Basic
    @Column(name = "descriereAnunt")
    private String descriereAnunt;
    @Basic
    @Column(name = "idserviciu")
    private int idserviciu;
    @Basic
    @Column(name = "dataAdaugare")
    private Timestamp dataAdaugare;
    @Basic
    @Column(name = "dataActualizare")
    private Timestamp dataActualizare;
    @Basic
    @Column(name = "localizare")
    private int localizare;
    @Basic
    @Column(name = "numarTelefon")
    private String numarTelefon;

    public int getIdanunt() {
        return idanunt;
    }

    public void setIdanunt(int idanunt) {
        this.idanunt = idanunt;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNumeAnunt() {
        return numeAnunt;
    }

    public void setNumeAnunt(String numeAnunt) {
        this.numeAnunt = numeAnunt;
    }

    public String getDescriereAnunt() {
        return descriereAnunt;
    }

    public void setDescriereAnunt(String descriereAnunt) {
        this.descriereAnunt = descriereAnunt;
    }

    public int getIdserviciu() {
        return idserviciu;
    }

    public void setIdserviciu(int idserviciu) {
        this.idserviciu = idserviciu;
    }

    public Timestamp getDataAdaugare() {
        return dataAdaugare;
    }

    public void setDataAdaugare(Timestamp dataAdaugare) {
        this.dataAdaugare = dataAdaugare;
    }

    public Timestamp getDataActualizare() {
        return dataActualizare;
    }

    public void setDataActualizare(Timestamp dataActualizare) {
        this.dataActualizare = dataActualizare;
    }

    public int getLocalizare() {
        return localizare;
    }

    public void setLocalizare(int localizare) {
        this.localizare = localizare;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Anunturi that = (Anunturi) o;

        if (idanunt != that.idanunt) return false;
        if (iduser != that.iduser) return false;
        if (idserviciu != that.idserviciu) return false;
        if (localizare != that.localizare) return false;
        if (numeAnunt != null ? !numeAnunt.equals(that.numeAnunt) : that.numeAnunt != null) return false;
        if (descriereAnunt != null ? !descriereAnunt.equals(that.descriereAnunt) : that.descriereAnunt != null)
            return false;
        if (dataAdaugare != null ? !dataAdaugare.equals(that.dataAdaugare) : that.dataAdaugare != null) return false;
        if (dataActualizare != null ? !dataActualizare.equals(that.dataActualizare) : that.dataActualizare != null)
            return false;
        if (numarTelefon != null ? !numarTelefon.equals(that.numarTelefon) : that.numarTelefon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idanunt;
        result = 31 * result + iduser;
        result = 31 * result + (numeAnunt != null ? numeAnunt.hashCode() : 0);
        result = 31 * result + (descriereAnunt != null ? descriereAnunt.hashCode() : 0);
        result = 31 * result + idserviciu;
        result = 31 * result + (dataAdaugare != null ? dataAdaugare.hashCode() : 0);
        result = 31 * result + (dataActualizare != null ? dataActualizare.hashCode() : 0);
        result = 31 * result + localizare;
        result = 31 * result + (numarTelefon != null ? numarTelefon.hashCode() : 0);
        return result;
    }
}
