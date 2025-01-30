package ec.edu.espe.examen.service;

import ec.edu.espe.examen.model.Branch;
import ec.edu.espe.examen.repository.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Slf4j
public class BranchService {
    private final BranchRepository repository;

    public BranchService(BranchRepository repository) {
        this.repository = repository;
    }

    public List<Branch> all() {
        return repository.findAll();
    }

    public Branch find(String id) {
        return repository.findById(id).orElse(null);
    }

    public void create(Branch branch) {
        branch.setCreationDate(LocalDateTime.now(ZoneId.systemDefault()));
        branch.setLastModifiedDate(LocalDateTime.now(ZoneId.systemDefault()));
        repository.save(branch);
    }

    public void update(Branch branch) {
        branch.setLastModifiedDate(LocalDateTime.now(ZoneId.systemDefault()));
        repository.save(branch);
    }
}
