package ec.edu.espe.examen.controller;

import ec.edu.espe.examen.controller.mapper.BranchMapper;
import ec.edu.espe.examen.dto.BranchDto;
import ec.edu.espe.examen.model.Branch;
import ec.edu.espe.examen.service.BranchService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@CrossOrigin("*")
@Slf4j
public class BranchController {
    private final BranchService service;
    private final BranchMapper mapper;

    public BranchController(BranchService service, BranchMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public EntityResponse<?> store(@Valid @RequestBody BranchDto branchDto) {
        Branch branch = this.mapper.toPersistence(branchDto);
        System.out.println(branch);
        return null;
    }
}
