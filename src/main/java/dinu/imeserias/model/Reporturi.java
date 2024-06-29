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
    @Column(name = "idanuntreported")
    private Integer idanuntreported;

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

    public Integer getIdanuntreported() {
        return idanuntreported;
    }

    public void setIdanuntreported(Integer idanuntreported) {
        this.idanuntreported = idanuntreported;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reporturi reporturi = (Reporturi) o;

        if (idreport != reporturi.idreport) return false;
        if (descriereReport != null ? !descriereReport.equals(reporturi.descriereReport) : reporturi.descriereReport != null)
            return false;
        if (decizie != null ? !decizie.equals(reporturi.decizie) : reporturi.decizie != null) return false;
        if (iduserreporting != null ? !iduserreporting.equals(reporturi.iduserreporting) : reporturi.iduserreporting != null)
            return false;
        if (idanuntreported != null ? !idanuntreported.equals(reporturi.idanuntreported) : reporturi.idanuntreported != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idreport;
        result = 31 * result + (descriereReport != null ? descriereReport.hashCode() : 0);
        result = 31 * result + (decizie != null ? decizie.hashCode() : 0);
        result = 31 * result + (iduserreporting != null ? iduserreporting.hashCode() : 0);
        result = 31 * result + (idanuntreported != null ? idanuntreported.hashCode() : 0);
        return result;
    }
}
