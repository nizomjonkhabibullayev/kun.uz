package dasturlash.uz.entity;

import dasturlash.uz.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "Article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column()
    private String title;
    private String description;
    private String content;
    private Long share_count;
    private Integer image_id;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "moderator_id")
    private ProfileEntity moderator;
    @ManyToOne
    @JoinColumn(name = "published_id")
    private ProfileEntity published;
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;
    private LocalDateTime createDate;
    private LocalDateTime publishedDate;
    private Boolean visible;
    private Long view;

}
