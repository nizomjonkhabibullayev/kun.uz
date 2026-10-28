package dasturlash.uz.service;

import dasturlash.uz.dto.CommentDto;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.CommentEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.exception.AppException;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.CommentRepository;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final ProfileRepository profileRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository repository,
                          ProfileRepository profileRepository,
                          ArticleRepository articleRepository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
        this.articleRepository = articleRepository;
    }

    public CommentDto create(CommentDto dto) {
        CommentEntity entity = new CommentEntity();
        copy(entity, dto);
        entity.setCreate(LocalDateTime.now());
        if (entity.getVisible() == null) {
            entity.setVisible(true);
        }
        return toDto(repository.save(entity));
    }

    public List<CommentDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public CommentDto getById(Integer id) {
        return toDto(find(id));
    }

    public CommentDto update(Integer id, CommentDto dto) {
        CommentEntity entity = find(id);
        copy(entity, dto);
        entity.setUpdate(LocalDateTime.now());
        return toDto(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(find(id));
    }

    private CommentEntity find(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Comment topilmadi: " + id));
    }

    private void copy(CommentEntity entity, CommentDto dto) {
        entity.setContent(dto.getContent());
        entity.setReplay(dto.getReplyId());
        entity.setProfile(findProfile(dto.getProfileId()));
        entity.setArticle(findArticle(dto.getArticleId()));
        if (dto.getVisible() != null) {
            entity.setVisible(dto.getVisible());
        }
    }

    private ProfileEntity findProfile(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Profile topilmadi: " + id));
    }

    private ArticleEntity findArticle(UUID id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Article topilmadi: " + id));
    }

    private CommentDto toDto(CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setProfileId(entity.getProfile() != null ? entity.getProfile().getId() : null);
        dto.setArticleId(entity.getArticle() != null ? entity.getArticle().getId() : null);
        dto.setReplyId(entity.getReplay());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreate());
        dto.setUpdatedDate(entity.getUpdate());
        return dto;
    }
}
