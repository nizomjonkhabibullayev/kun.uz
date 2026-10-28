package dasturlash.uz.service;

import dasturlash.uz.dto.ArticleTypeDto;
import dasturlash.uz.entity.ArticleTypeEntity;
import dasturlash.uz.exception.AppException;
import dasturlash.uz.repository.ArticleTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTypeService {

    private final ArticleTypeRepository repository;

    public ArticleTypeService(ArticleTypeRepository repository) {
        this.repository = repository;
    }

    public ArticleTypeDto create(ArticleTypeDto dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public List<ArticleTypeDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public ArticleTypeDto getById(Long id) {
        return toDto(find(id));
    }

    public ArticleTypeDto update(Long id, ArticleTypeDto dto) {
        ArticleTypeEntity entity = find(id);
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(find(id));
    }

    private ArticleTypeEntity find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "ArticleType topilmadi: " + id));
    }

    private void copy(ArticleTypeEntity entity, ArticleTypeDto dto) {
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        if (dto.getVisible() != null) {
            entity.setVisible(dto.getVisible());
        }
    }

    private ArticleTypeDto toDto(ArticleTypeEntity entity) {
        ArticleTypeDto dto = new ArticleTypeDto();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
