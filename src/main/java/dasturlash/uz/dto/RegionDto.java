package dasturlash.uz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegionDto {
    private Long id;

    @NotNull
    @Positive
    private Integer orderNumber;

    @NotBlank
    @Size(max = 100)
    private String nameUz;

    @Size(max = 100)
    private String nameRu;

    @Size(max = 100)
    private String nameEn;

    private Boolean visible;
    private LocalDateTime createdDate;
}
