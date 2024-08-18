package bg.softuni.philately.model.dto.stamp;

import bg.softuni.philately.model.entity.PaperName;
import bg.softuni.philately.model.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public class AddStampBindingModel {

    @Size(min = 5, max = 20, message = "Name length must be between 5 and 20 characters!")
    private String stampName;
    @Size(min = 5, max = 25, message = "Description length must be between 5 and 20 characters!")
    private String description;
    @URL(message = "Please enter valid image URL!")
    private String imageUrl;
    @Positive
    private BigDecimal price;
    @NotNull(message = "You must select a type of paper!")
    private PaperName paperName;
    private User owner;

    public String getStampName() {
        return stampName;
    }

    public void setStampName(String stampName) {
        this.stampName = stampName;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PaperName getPaperName() {
        return paperName;
    }

    public void setPaperName(PaperName paperName) {
        this.paperName = paperName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
