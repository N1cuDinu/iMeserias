package dinu.imeserias.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users", schema = "imeserias")
public class Utilizator {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iduser")
    private int iduser;
    @Basic
    @Column(name = "idRol")
    private int idRol;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "dataSignUp")
    @CreatedDate
    private Timestamp dataSignUp;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilizator that = (Utilizator) o;

        if (iduser != that.iduser) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (idRol != that.idRol) return false;
        if (dataSignUp != null ? !dataSignUp.equals(that.dataSignUp) : that.dataSignUp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iduser;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + idRol;
        result = 31 * result + (dataSignUp != null ? dataSignUp.hashCode() : 0);
        return result;
    }
}
