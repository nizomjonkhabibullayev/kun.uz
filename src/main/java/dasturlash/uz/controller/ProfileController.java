package dasturlash.uz.controller;

import dasturlash.uz.dto.ProfileDto;
import dasturlash.uz.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ProfileDto create(@Valid @RequestBody ProfileDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ProfileDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProfileDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ProfileDto update(@PathVariable Long id, @RequestBody ProfileDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
