package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleTypeRepository
        extends JpaRepository<ArticleTypeEntity, Long> {
}