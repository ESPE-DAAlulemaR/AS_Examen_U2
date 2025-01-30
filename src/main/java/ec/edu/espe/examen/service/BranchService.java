package ec.edu.espe.examen.service;

import ec.edu.espe.examen.dto.BranchDto;
import ec.edu.espe.examen.model.Branch;
import ec.edu.espe.examen.model.Holiday;
import ec.edu.espe.examen.repository.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void update(String id, Branch branchReq) {
        Branch branch = this.find(id);
        branch.setName(branchReq.getName());
        branch.setLastModifiedDate(LocalDateTime.now(ZoneId.systemDefault()));
        repository.save(branch);
    }

    public void addHoliday(String id, Holiday holiday) {
        Branch branch = this.find(id);
        branch.getHolidays().add(holiday);

        repository.save(branch);
    }

    public void removeHoliday(String id, String holidayName) {
        Branch branch = this.find(id);
        branch.getHolidays().removeIf(holiday -> holiday.getName().equalsIgnoreCase(holidayName));

        repository.save(branch);
    }

    public List<Holiday> getHolidays(String id) {
        Branch branch = this.find(id);
        return branch.getHolidays();
    }

    public Branch isNotHoliday(LocalDate date) {
        List<Branch> branches = this.all();

        for (Branch branch : branches)
            if (branch.getHolidays().stream().anyMatch(holiday -> holiday.getDate().equals(date)))
                return branch;

        return null;
    }
}
