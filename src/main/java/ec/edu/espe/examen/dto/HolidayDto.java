package ec.edu.espe.examen.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayDto {
    private String name;
    private LocalDate date;
}
