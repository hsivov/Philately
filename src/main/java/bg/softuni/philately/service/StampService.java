package bg.softuni.philately.service;

import bg.softuni.philately.model.dto.stamp.AddStampBindingModel;
import bg.softuni.philately.model.dto.stamp.StampHomeViewModel;

public interface StampService {
    boolean add(AddStampBindingModel addStampBindingModel);

    StampHomeViewModel getHomeViewData();

    void toWishlist(Long id);

    void removeFromWishlist(Long id);

    void buy();
}
