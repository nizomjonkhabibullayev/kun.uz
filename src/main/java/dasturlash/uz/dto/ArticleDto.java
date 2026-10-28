package dasturlash.uz.dto;

import dasturlash.uz.enums.ArticleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ArticleDto {
    private UUID id;

    @NotBlank
    @Size(max = 220)
    private String title;

    @NotBlank
    @Size(max = 999)
    private String description;

    @NotBlank
    private String content;

    private Long shareCount;
    private Integer imageId;

    @NotNull
    private Long regionId;

    @NotNull
    private Long categoryId;

    private Long moderatorId;
    private Long publishedId;

    private ArticleStatus status;
    private Boolean visible;
    private Long view;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
}
