package bg.softuni.philately.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "papers")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PaperName paperName;

    @Column(nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaperName getPaperName() {
        return paperName;
    }

    public void setPaperName(PaperName paperName) {
        this.paperName = paperName;
        this.setDescription(paperName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void setDescription(PaperName name) {
        String description = "";

        switch (name) {
            case WOVE_PAPER -> description = "Has an even texture without any particular distinguishing features.";
            case GRANITE_PAPER -> description = "Has tiny specks of coloured fibre in it, which can usually be seen with the naked eye.";
            case LAID_PAPER -> description = "When held up to the light, shows parallel lines of greater or less width running across the stamp.";
        }

        this.description = description;
    }
}
