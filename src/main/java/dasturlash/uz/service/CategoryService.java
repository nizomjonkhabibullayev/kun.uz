package dasturlash.uz.service;

import dasturlash.uz.dto.CategoryDto;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.exception.AppException;
import dasturlash.uz.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public CategoryDto create(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public List<CategoryDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public CategoryDto getById(Long id) {
        return toDto(find(id));
    }

    public CategoryDto update(Long id, CategoryDto dto) {
        CategoryEntity entity = find(id);
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(find(id));
    }

    private CategoryEntity find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Category topilmadi: " + id));
    }

    private void copy(CategoryEntity entity, CategoryDto dto) {
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        if (dto.getVisible() != null) {
            entity.setVisible(dto.getVisible());
        }
    }

    private CategoryDto toDto(CategoryEntity entity) {
        CategoryDto dto = new CategoryDto();
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
