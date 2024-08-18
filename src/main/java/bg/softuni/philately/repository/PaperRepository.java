package bg.softuni.philately.repository;

import bg.softuni.philately.model.entity.Paper;
import bg.softuni.philately.model.entity.PaperName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
    Paper findByPaperName(PaperName paperName);
}
