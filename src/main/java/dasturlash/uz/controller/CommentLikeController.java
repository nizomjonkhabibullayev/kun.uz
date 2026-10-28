package dasturlash.uz.controller;

import dasturlash.uz.dto.CommentLikeDto;
import dasturlash.uz.service.CommentLikeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment-like")
public class CommentLikeController {

    private final CommentLikeService service;

    public CommentLikeController(CommentLikeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentLikeDto create(@Valid @RequestBody CommentLikeDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CommentLikeDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CommentLikeDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CommentLikeDto update(@PathVariable Integer id, @Valid @RequestBody CommentLikeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
