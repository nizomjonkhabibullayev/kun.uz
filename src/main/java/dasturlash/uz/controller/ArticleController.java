package dasturlash.uz.controller;

import dasturlash.uz.dto.ArticleDto;
import dasturlash.uz.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto create(@Valid @RequestBody ArticleDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ArticleDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ArticleDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ArticleDto update(@PathVariable UUID id, @Valid @RequestBody ArticleDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
