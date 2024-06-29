package dinu.imeserias.dto;

import dinu.imeserias.model.Servicii;
import dinu.imeserias.model.Utilizatori;
import lombok.Builder;
import lombok.Data;


import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
public class AnunturiDto {
    private int idanunt;
    private int iduser;
    private String numeAnunt;
    private String descriereAnunt;
    private Set<String> servicii;
    private Timestamp dataAdaugare;
    private Timestamp dataActualizare;
    private int localizare;
    private String numarTelefon;
}
