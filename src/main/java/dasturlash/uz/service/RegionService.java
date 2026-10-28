package dasturlash.uz.service;

import dasturlash.uz.dto.RegionDto;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.exception.AppException;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }

    public RegionDto create(RegionDto dto) {
        RegionEntity entity = new RegionEntity();
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public List<RegionDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public RegionDto getById(Long id) {
        return toDto(find(id));
    }

    public RegionDto update(Long id, RegionDto dto) {
        RegionEntity entity = find(id);
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(find(id));
    }

    private RegionEntity find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Region topilmadi: " + id));
    }

    private void copy(RegionEntity entity, RegionDto dto) {
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        if (dto.getVisible() != null) {
            entity.setVisible(dto.getVisible());
        }
    }

    private RegionDto toDto(RegionEntity entity) {
        RegionDto dto = new RegionDto();
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
