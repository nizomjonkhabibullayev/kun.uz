package dasturlash.uz.controller;

import dasturlash.uz.dto.ArticleTypeDto;
import dasturlash.uz.service.ArticleTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article-type")
public class ArticleTypeController {

    private final ArticleTypeService service;

    public ArticleTypeController(ArticleTypeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleTypeDto create(@Valid @RequestBody ArticleTypeDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ArticleTypeDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ArticleTypeDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ArticleTypeDto update(@PathVariable Long id, @Valid @RequestBody ArticleTypeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
