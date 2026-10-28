package dasturlash.uz.service;

import dasturlash.uz.dto.ArticleDto;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.exception.AppException;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.CategoryRepository;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final ProfileRepository profileRepository;

    public ArticleService(ArticleRepository articleRepository,
                          RegionRepository regionRepository,
                          CategoryRepository categoryRepository,
                          ProfileRepository profileRepository) {
        this.articleRepository = articleRepository;
        this.regionRepository = regionRepository;
        this.categoryRepository = categoryRepository;
        this.profileRepository = profileRepository;
    }

    public ArticleDto create(ArticleDto dto) {
        ArticleEntity entity = new ArticleEntity();
        copy(entity, dto);
        entity.setCreateDate(LocalDateTime.now());
        if (entity.getStatus() == null) {
            entity.setStatus(ArticleStatus.NotPublished);
        }
        if (entity.getVisible() == null) {
            entity.setVisible(true);
        }
        if (entity.getView() == null) {
            entity.setView(0L);
        }
        return toDto(articleRepository.save(entity));
    }

    public List<ArticleDto> getAll() {
        return articleRepository.findAll().stream().map(this::toDto).toList();
    }

    public ArticleDto getById(UUID id) {
        return toDto(find(id));
    }

    public ArticleDto update(UUID id, ArticleDto dto) {
        ArticleEntity entity = find(id);
        copy(entity, dto);
        return toDto(articleRepository.save(entity));
    }

    public void delete(UUID id) {
        articleRepository.delete(find(id));
    }

    private ArticleEntity find(UUID id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Article topilmadi: " + id));
    }

    // dto dagi id'lar orqali bog'liq entity'larni topib qo'yadi
    private void copy(ArticleEntity entity, ArticleDto dto) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setShare_count(dto.getShareCount());
        entity.setImage_id(dto.getImageId());
        entity.setPublishedDate(dto.getPublishedDate());

        entity.setRegion(findRegion(dto.getRegionId()));
        entity.setCategory(findCategory(dto.getCategoryId()));
        entity.setModerator(findProfile(dto.getModeratorId()));
        entity.setPublished(findProfile(dto.getPublishedId()));

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getVisible() != null) {
            entity.setVisible(dto.getVisible());
        }
        if (dto.getView() != null) {
            entity.setView(dto.getView());
        }
    }

    private RegionEntity findRegion(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Region topilmadi: " + id));
    }

    private CategoryEntity findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Category topilmadi: " + id));
    }

    // moderator va published ixtiyoriy, id berilmasa null qoladi
    private ProfileEntity findProfile(Long id) {
        if (id == null) {
            return null;
        }
        return profileRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Profile topilmadi: " + id));
    }

    private ArticleDto toDto(ArticleEntity entity) {
        ArticleDto dto = new ArticleDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setShareCount(entity.getShare_count());
        dto.setImageId(entity.getImage_id());
        dto.setRegionId(entity.getRegion() != null ? entity.getRegion().getId() : null);
        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setModeratorId(entity.getModerator() != null ? entity.getModerator().getId() : null);
        dto.setPublishedId(entity.getPublished() != null ? entity.getPublished().getId() : null);
        dto.setStatus(entity.getStatus());
        dto.setVisible(entity.getVisible());
        dto.setView(entity.getView());
        dto.setCreatedDate(entity.getCreateDate());
        dto.setPublishedDate(entity.getPublishedDate());
        return dto;
    }
}
