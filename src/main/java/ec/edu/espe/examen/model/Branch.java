package ec.edu.espe.examen.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@ToString
@Document(collation = "branches")
public class Branch {
    @Id
    private String _id;

    private String emailAddress;
    private String name;
    private String phoneNumber;
    private String state;

    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;

    private List<Holiday> holidays;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(_id, branch._id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_id);
    }
}
