package dasturlash.uz.entity;

import dasturlash.uz.enums.ArticleLikeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "ArticleLike")
public class ArticleLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;
    private LocalDateTime createDate;
    @Enumerated(EnumType.STRING)
    private ArticleLikeStatus status;

}
