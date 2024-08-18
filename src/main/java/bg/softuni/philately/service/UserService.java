package bg.softuni.philately.service;

import bg.softuni.philately.model.dto.user.UserLoginBindingModel;
import bg.softuni.philately.model.dto.user.UserRegisterBindingModel;

public interface UserService {
    boolean login(UserLoginBindingModel userLoginBindingModel);

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    void logout();

    boolean isUsernameUnique(String username);

    boolean isEmailUnique(String email);
}
