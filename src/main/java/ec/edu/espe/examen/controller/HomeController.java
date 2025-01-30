package ec.edu.espe.examen.controller;

import ec.edu.espe.examen.dto.MensajeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HomeController {
    @GetMapping
    public ResponseEntity<MensajeDto> home() {
        MensajeDto homeDto = new MensajeDto();
        homeDto.setMessage("Alulema Dannyel - Examen U2");

        return ResponseEntity.ok(homeDto);
    }
}
