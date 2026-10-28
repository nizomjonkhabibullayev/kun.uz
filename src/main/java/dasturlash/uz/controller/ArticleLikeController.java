package dasturlash.uz.controller;

import dasturlash.uz.dto.ArticleLikeDto;
import dasturlash.uz.service.ArticleLikeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article-like")
public class ArticleLikeController {

    private final ArticleLikeService service;

    public ArticleLikeController(ArticleLikeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleLikeDto create(@Valid @RequestBody ArticleLikeDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ArticleLikeDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ArticleLikeDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ArticleLikeDto update(@PathVariable Integer id, @Valid @RequestBody ArticleLikeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
