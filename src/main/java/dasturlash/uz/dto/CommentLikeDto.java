package dasturlash.uz.dto;

import dasturlash.uz.enums.CommentLikeStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentLikeDto {
    private Integer id;

    @NotNull
    private Long profileId;

    @NotNull
    private Integer commentId;

    @NotNull
    private CommentLikeStatus status;

    private LocalDateTime createdDate;
}
