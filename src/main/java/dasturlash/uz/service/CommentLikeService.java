package dasturlash.uz.service;

import dasturlash.uz.dto.CommentLikeDto;
import dasturlash.uz.entity.CommentEntity;
import dasturlash.uz.entity.CommentLikeEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.exception.AppException;
import dasturlash.uz.repository.CommentLikeRepository;
import dasturlash.uz.repository.CommentRepository;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentLikeService {

    private final CommentLikeRepository repository;
    private final ProfileRepository profileRepository;
    private final CommentRepository commentRepository;

    public CommentLikeService(CommentLikeRepository repository,
                              ProfileRepository profileRepository,
                              CommentRepository commentRepository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
        this.commentRepository = commentRepository;
    }

    public CommentLikeDto create(CommentLikeDto dto) {
        CommentLikeEntity entity = new CommentLikeEntity();
        copy(entity, dto);
        entity.setCreateDate(LocalDateTime.now());
        return toDto(repository.save(entity));
    }

    public List<CommentLikeDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public CommentLikeDto getById(Integer id) {
        return toDto(find(id));
    }

    public CommentLikeDto update(Integer id, CommentLikeDto dto) {
        CommentLikeEntity entity = find(id);
        copy(entity, dto);
        return toDto(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.delete(find(id));
    }

    private CommentLikeEntity find(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "CommentLike topilmadi: " + id));
    }

    private void copy(CommentLikeEntity entity, CommentLikeDto dto) {
        entity.setProfile(findProfile(dto.getProfileId()));
        entity.setComment(findComment(dto.getCommentId()));
        entity.setStatus(dto.getStatus());
    }

    private ProfileEntity findProfile(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Profile topilmadi: " + id));
    }

    private CommentEntity findComment(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Comment topilmadi: " + id));
    }

    private CommentLikeDto toDto(CommentLikeEntity entity) {
        CommentLikeDto dto = new CommentLikeDto();
        dto.setId(entity.getId());
        dto.setProfileId(entity.getProfile() != null ? entity.getProfile().getId() : null);
        dto.setCommentId(entity.getComment() != null ? entity.getComment().getId() : null);
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreateDate());
        return dto;
    }
}
