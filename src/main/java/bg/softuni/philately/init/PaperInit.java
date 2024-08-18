package bg.softuni.philately.init;

import bg.softuni.philately.model.entity.Paper;
import bg.softuni.philately.model.entity.PaperName;
import bg.softuni.philately.repository.PaperRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PaperInit implements CommandLineRunner {
    private final PaperRepository paperRepository;

    public PaperInit(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    @Override
    public void run(String... args) {
        long count = paperRepository.count();

        if (count == 0) {
            List<Paper> papers = new ArrayList<>();

            Arrays.stream(PaperName.values())
                    .forEach(paperName -> {
                        Paper paper = new Paper();
                        paper.setPaperName(paperName);
                        papers.add(paper);
                    });
            paperRepository.saveAll(papers);
        }
    }
}
