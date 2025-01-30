package ec.edu.espe.examen.controller.mapper;

import ec.edu.espe.examen.dto.BranchDto;
import ec.edu.espe.examen.dto.HolidayDto;
import ec.edu.espe.examen.model.Branch;
import ec.edu.espe.examen.model.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HolidayMapper {
    HolidayDto toDto(Holiday holiday);
    Holiday toPersistence(HolidayDto holidayDto);
}
