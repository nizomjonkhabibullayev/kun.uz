package dasturlash.uz.service;

import dasturlash.uz.dto.ArticleLikeDto;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.ArticleLikeEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.exception.AppException;
import dasturlash.uz.repository.ArticleLikeRepository;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleLikeService {

    private final ArticleLikeRepository repository;
    private final ProfileRepository profileRepository;
    private final ArticleRepository articleRepository;

    public ArticleLikeService(ArticleLikeRepository repository,
                              ProfileRepository profileRepository,
                              ArticleRepository articleRepository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
        this.articleRepository = articleRepository;
    }

    public ArticleLikeDto create(ArticleLikeDto dto) {
        ArticleLikeEntity entity = new ArticleLikeEntity();
        copy(entity, dto);
        entity.setCreateDate(LocalDateTime.now());
        return toDto(repository.save(entity));
    }

    public List<ArticleLikeDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public ArticleLikeDto getById(Integer id) {
        return toDto(find(id));
    }

    public ArticleLikeDto update(Integer id, ArticleLikeDto dto) {
        ArticleLikeEntity entity = find(id);
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(find(id));
    }

    private ArticleLikeEntity find(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "ArticleLike topilmadi: " + id));
    }

    private void copy(ArticleLikeEntity entity, ArticleLikeDto dto) {
        entity.setProfile(findProfile(dto.getProfileId()));
        entity.setArticle(findArticle(dto.getArticleId()));
        entity.setStatus(dto.getStatus());
    }

    private ProfileEntity findProfile(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Profile topilmadi: " + id));
    }

    private ArticleEntity findArticle(UUID id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Article topilmadi: " + id));
    }

    private ArticleLikeDto toDto(ArticleLikeEntity entity) {
        ArticleLikeDto dto = new ArticleLikeDto();
        dto.setId(entity.getId());
        dto.setProfileId(entity.getProfile() != null ? entity.getProfile().getId() : null);
        dto.setArticleId(entity.getArticle() != null ? entity.getArticle().getId() : null);
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreateDate());
        return dto;
    }
}
