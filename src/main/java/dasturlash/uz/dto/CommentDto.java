package dasturlash.uz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CommentDto {
    private Integer id;

    @NotBlank
    private String content;

    @NotNull
    private Long profileId;

    @NotNull
    private UUID articleId;

    @Positive
    private Integer replyId;

    private Boolean visible;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
