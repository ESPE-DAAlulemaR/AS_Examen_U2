package ec.edu.espe.examen.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Holiday {
    private String name;
    private LocalDate date;
}
