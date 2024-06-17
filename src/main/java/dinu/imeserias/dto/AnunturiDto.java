package dinu.imeserias.dto;

import lombok.Builder;
import lombok.Data;


import java.sql.Timestamp;

@Data
@Builder
public class AnunturiDto {
    private int idanunt;
    private int iduser;
    private String numeAnunt;
    private String descriereAnunt;
    private int idserviciu;
    private Timestamp dataAdaugare;
    private Timestamp dataActualizare;
    private int localizare;
    private String numarTelefon;
}
