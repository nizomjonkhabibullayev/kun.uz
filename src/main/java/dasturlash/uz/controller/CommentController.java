package dasturlash.uz.controller;

import dasturlash.uz.dto.CommentDto;
import dasturlash.uz.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@Valid @RequestBody CommentDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CommentDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CommentDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CommentDto update(@PathVariable Integer id, @Valid @RequestBody CommentDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
