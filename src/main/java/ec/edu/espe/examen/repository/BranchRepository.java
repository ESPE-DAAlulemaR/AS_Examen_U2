package ec.edu.espe.examen.repository;

import ec.edu.espe.examen.model.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<Branch, String> {
}
