package dinu.imeserias.service;

import dinu.imeserias.dto.AnunturiDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AnunturiService {
    List<AnunturiDto> findAllAnunturi();
}
