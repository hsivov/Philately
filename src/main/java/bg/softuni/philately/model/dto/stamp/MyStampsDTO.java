package bg.softuni.philately.model.dto.stamp;

import bg.softuni.philately.model.entity.PaperName;

public class MyStampsDTO {

    private String name;
    private String description;
    private String imageUrl;
    private String price;
    private PaperName paperName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public PaperName getPaperName() {
        return paperName;
    }

    public void setPaperName(PaperName paperName) {
        this.paperName = paperName;
    }
}
