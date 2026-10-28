package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;
    @Column(name = "create_date")
    private LocalDateTime create;
    @Column(name = "update_date")
    private LocalDateTime update;
    private String content;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;
    private Integer replay;
    private Boolean visible;

}
