package dinu.imeserias.model;

import jakarta.persistence.*;

@Entity
public class Rol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idroluri")
    private int idrol;
    @Basic
    @Column(name = "numeRol")
    private String numeRol;

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idroluri) {
        this.idrol = idroluri;
    }

    public String getNumeRol() {
        return numeRol;
    }

    public void setNumeRol(String numeRol) {
        this.numeRol = numeRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rol rol = (Rol) o;

        if (idrol != rol.idrol) return false;
        if (numeRol != null ? !numeRol.equals(rol.numeRol) : rol.numeRol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrol;
        result = 31 * result + (numeRol != null ? numeRol.hashCode() : 0);
        return result;
    }
}
