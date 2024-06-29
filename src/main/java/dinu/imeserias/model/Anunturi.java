package dinu.imeserias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    @ManyToMany
    @JoinTable(
            name = "anunturi_servicii",
            joinColumns = @JoinColumn(name = "idanunt"),
            inverseJoinColumns = @JoinColumn(name = "idserviciu")
    )
    private Set<Servicii> servicii = new HashSet<>();
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

    public Set<Servicii> getServicii() {
        return servicii;
    }

    public void setServicii(Set<Servicii> servicii) {
        this.servicii = servicii;
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

        Anunturi anunturi = (Anunturi) o;

        if (idanunt != anunturi.idanunt) return false;
        if (iduser != anunturi.iduser) return false;
        if (localizare != anunturi.localizare) return false;
        if (numeAnunt != null ? !numeAnunt.equals(anunturi.numeAnunt) : anunturi.numeAnunt != null) return false;
        if (descriereAnunt != null ? !descriereAnunt.equals(anunturi.descriereAnunt) : anunturi.descriereAnunt != null)
            return false;
        if (dataAdaugare != null ? !dataAdaugare.equals(anunturi.dataAdaugare) : anunturi.dataAdaugare != null)
            return false;
        if (dataActualizare != null ? !dataActualizare.equals(anunturi.dataActualizare) : anunturi.dataActualizare != null)
            return false;
        if (numarTelefon != null ? !numarTelefon.equals(anunturi.numarTelefon) : anunturi.numarTelefon != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idanunt;
        result = 31 * result + iduser;
        result = 31 * result + (numeAnunt != null ? numeAnunt.hashCode() : 0);
        result = 31 * result + (descriereAnunt != null ? descriereAnunt.hashCode() : 0);
        result = 31 * result + (dataAdaugare != null ? dataAdaugare.hashCode() : 0);
        result = 31 * result + (dataActualizare != null ? dataActualizare.hashCode() : 0);
        result = 31 * result + localizare;
        result = 31 * result + (numarTelefon != null ? numarTelefon.hashCode() : 0);
        return result;
    }
}
