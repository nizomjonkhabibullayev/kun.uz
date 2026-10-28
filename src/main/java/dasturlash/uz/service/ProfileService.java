package dasturlash.uz.service;

import dasturlash.uz.dto.ProfileDto;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.exception.AppException;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public ProfileDto create(ProfileDto dto) {
        ProfileEntity entity = new ProfileEntity();
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public List<ProfileDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public ProfileDto getById(Long id) {
        return toDto(find(id));
    }

    public ProfileDto update(Long id, ProfileDto dto) {
        ProfileEntity entity = find(id);
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.delete(find(id));
    }

    private ProfileEntity find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Profile topilmadi: " + id));
    }

    private void copy(ProfileEntity entity, ProfileDto dto) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        if (dto.getVisible() != null) {
            entity.setVisible(dto.getVisible());
        }
        entity.setPhoto_id(dto.getPhotoId());
    }

    // Parol javobga qaytarilmaydi
    private ProfileDto toDto(ProfileEntity entity) {
        ProfileDto dto = new ProfileDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPhotoId(entity.getPhoto_id());
        return dto;
    }
}
