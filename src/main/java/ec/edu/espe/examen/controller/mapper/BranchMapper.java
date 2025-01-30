package ec.edu.espe.examen.controller.mapper;

import ec.edu.espe.examen.dto.BranchDto;
import ec.edu.espe.examen.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BranchMapper {
    BranchDto toDto(Branch branch);
    Branch toPersistence(BranchDto branchDto);
}
