package projekti.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Praise;

public interface PraiseRepository extends JpaRepository<Praise, Long> {
}
