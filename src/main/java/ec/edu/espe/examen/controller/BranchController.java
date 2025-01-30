package ec.edu.espe.examen.controller;

import ec.edu.espe.examen.controller.mapper.BranchMapper;
import ec.edu.espe.examen.controller.mapper.HolidayMapper;
import ec.edu.espe.examen.dto.BranchDto;
import ec.edu.espe.examen.dto.HolidayDto;
import ec.edu.espe.examen.dto.MensajeDto;
import ec.edu.espe.examen.model.Branch;
import ec.edu.espe.examen.model.Holiday;
import ec.edu.espe.examen.service.BranchService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/v1/branches")
@CrossOrigin("*")
public class BranchController {
    private final BranchService service;
    private final BranchMapper mapper;
    private final HolidayMapper holidayMapper;

    public BranchController(
            BranchService service,
            BranchMapper mapper,
            HolidayMapper holidayMapper
    ) {
        this.service = service;
        this.mapper = mapper;
        this.holidayMapper = holidayMapper;
    }

    @GetMapping
    public ResponseEntity<List<Branch>> index() {
        List<Branch> branches = this.service.all();
        log.info("Branches: {}", branches);
        return ResponseEntity.status(HttpStatus.OK).body(branches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> show(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.find(id));
    }

    @PostMapping
    public ResponseEntity<Branch> store(@Valid @RequestBody BranchDto branchDto) {
        log.info("Requested Branch: {}", branchDto);

        Branch branch = this.mapper.toPersistence(branchDto);
        this.service.create(branch);

        log.info("Created Branch: {}", branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(branch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> store(@PathVariable("id") String id, @RequestBody BranchDto branchDto) {
        log.info("Requested Branch: {}", branchDto);

        Branch branch = this.mapper.toPersistence(branchDto);
        this.service.update(id, branch);

        log.info("Updated Branch: {}", branch);
        return ResponseEntity.status(HttpStatus.OK).body(branch);
    }

    @PostMapping("/{branchId}/holidays")
    public ResponseEntity<Holiday> storeHoliday(@PathVariable("branchId") String branchId, @RequestBody HolidayDto holidayDto) {
        log.info("Requested Holiday: {}", holidayDto);
        Holiday holiday = this.holidayMapper.toPersistence(holidayDto);
        this.service.addHoliday(branchId, holiday);

        log.info("Added Holiday: {}", holiday);
        return ResponseEntity.status(HttpStatus.CREATED).body(holiday);
    }

    @PatchMapping("/{branchId}/holidays")
    public ResponseEntity<Holiday> deleteHoliday(@PathVariable("branchId") String branchId, @RequestBody HolidayDto holidayDto) {
        log.info("Requested Holiday: {}", holidayDto);
        Holiday holiday = this.holidayMapper.toPersistence(holidayDto);
        this.service.removeHoliday(branchId, holiday.getName());

        log.info("Removed Holiday: {}", holiday);
        return ResponseEntity.status(HttpStatus.OK).body(holiday);
    }

    @GetMapping("/{branchId}/holidays")
    public ResponseEntity<List<Holiday>> showHolidays(@PathVariable("branchId") String branchId) {
        List<Holiday> holidays = this.service.getHolidays(branchId);

        log.info("Holidays: {}", holidays);
        return ResponseEntity.status(HttpStatus.OK).body(holidays);
    }

    @GetMapping("/{branchId}/holidays/{date}")
    public ResponseEntity<?> verifyHolidayDate(
            @PathVariable("branchId") String branchId,
            @PathVariable("date") String date
    ) {
        log.info("Requested Date: {}", date);
        LocalDate localDate = LocalDate.parse(date);
        Branch branch = this.service.isNotHoliday(localDate);

        if (branch == null) {
            MensajeDto mensajeDto = new MensajeDto();
            mensajeDto.setMessage("La fecha no es feriado en ninguna sucursal");

            return ResponseEntity.status(HttpStatus.OK).body(mensajeDto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(branch);
    }
}
