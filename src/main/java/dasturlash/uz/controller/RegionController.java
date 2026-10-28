package dasturlash.uz.controller;

import dasturlash.uz.dto.RegionDto;
import dasturlash.uz.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final RegionService service;

    public RegionController(RegionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegionDto create(@Valid @RequestBody RegionDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<RegionDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RegionDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public RegionDto update(@PathVariable Long id, @RequestBody RegionDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
