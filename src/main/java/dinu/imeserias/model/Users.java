package dinu.imeserias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users", schema = "imeserias")
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iduser")
    private int iduser;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "tipUtilizator")
    private String tipUtilizator;
    @Basic
    @Column(name = "dataSignUp")
    @CreatedDate
    private Timestamp dataSignUp;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipUtilizator() {
        return tipUtilizator;
    }

    public void setTipUtilizator(String tipUtilizator) {
        this.tipUtilizator = tipUtilizator;
    }

    public Timestamp getDataSignUp() {
        return dataSignUp;
    }

    public void setDataSignUp(Timestamp dataSignUp) {
        this.dataSignUp = dataSignUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users that = (Users) o;

        if (iduser != that.iduser) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (tipUtilizator != null ? !tipUtilizator.equals(that.tipUtilizator) : that.tipUtilizator != null)
            return false;
        if (dataSignUp != null ? !dataSignUp.equals(that.dataSignUp) : that.dataSignUp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iduser;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (tipUtilizator != null ? tipUtilizator.hashCode() : 0);
        result = 31 * result + (dataSignUp != null ? dataSignUp.hashCode() : 0);
        return result;
    }
}
