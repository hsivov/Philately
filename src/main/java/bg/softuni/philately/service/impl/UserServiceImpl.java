package bg.softuni.philately.service.impl;

import bg.softuni.philately.model.dto.user.UserLoginBindingModel;
import bg.softuni.philately.model.dto.user.UserRegisterBindingModel;
import bg.softuni.philately.model.entity.User;
import bg.softuni.philately.repository.UserRepository;
import bg.softuni.philately.service.UserService;
import bg.softuni.philately.service.session.LoggedUser;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        String username = userLoginBindingModel.getUsername();
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())) {
            loggedUser.login(username);

            return true;
        }
        return false;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegisterBindingModel.getUsername());
        user.setEmail(userRegisterBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return userRepository.findByUsername(username) == null;
    }

    @Override
    public boolean isEmailUnique(String email) {
        return userRepository.findByEmail(email) == null;
    }

    @Override
    @Transactional
    public void logout() {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        user.getWishedStamps().clear();

        loggedUser.logout();
    }
}
