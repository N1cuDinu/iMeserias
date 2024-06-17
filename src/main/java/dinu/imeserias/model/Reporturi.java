package dinu.imeserias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reporturi", schema = "imeserias")
public class Reporturi {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idreport")
    private int idreport;
    @Basic
    @Column(name = "descriereReport")
    private String descriereReport;
    @Basic
    @Column(name = "decizie")
    private String decizie;
    @Basic
    @Column(name = "iduserreporting")
    private Integer iduserreporting;
    @Basic
    @Column(name = "iduserreported")
    private Integer iduserreported;

    public int getIdreport() {
        return idreport;
    }

    public void setIdreport(int idreport) {
        this.idreport = idreport;
    }

    public String getDescriereReport() {
        return descriereReport;
    }

    public void setDescriereReport(String descriereReport) {
        this.descriereReport = descriereReport;
    }

    public String getDecizie() {
        return decizie;
    }

    public void setDecizie(String decizie) {
        this.decizie = decizie;
    }

    public Integer getIduserreporting() {
        return iduserreporting;
    }

    public void setIduserreporting(Integer iduserreporting) {
        this.iduserreporting = iduserreporting;
    }

    public Integer getIduserreported() {
        return iduserreported;
    }

    public void setIduserreported(Integer iduserreported) {
        this.iduserreported = iduserreported;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reporturi that = (Reporturi) o;

        if (idreport != that.idreport) return false;
        if (descriereReport != null ? !descriereReport.equals(that.descriereReport) : that.descriereReport != null)
            return false;
        if (decizie != null ? !decizie.equals(that.decizie) : that.decizie != null) return false;
        if (iduserreporting != null ? !iduserreporting.equals(that.iduserreporting) : that.iduserreporting != null)
            return false;
        if (iduserreported != null ? !iduserreported.equals(that.iduserreported) : that.iduserreported != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idreport;
        result = 31 * result + (descriereReport != null ? descriereReport.hashCode() : 0);
        result = 31 * result + (decizie != null ? decizie.hashCode() : 0);
        result = 31 * result + (iduserreporting != null ? iduserreporting.hashCode() : 0);
        result = 31 * result + (iduserreported != null ? iduserreported.hashCode() : 0);
        return result;
    }
}
