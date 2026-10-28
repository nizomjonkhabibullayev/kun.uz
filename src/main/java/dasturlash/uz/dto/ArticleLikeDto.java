package dasturlash.uz.dto;

import dasturlash.uz.enums.ArticleLikeStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ArticleLikeDto {
    private Integer id;

    @NotNull
    private Long profileId;

    @NotNull
    private UUID articleId;

    @NotNull
    private ArticleLikeStatus status;

    private LocalDateTime createdDate;
}
