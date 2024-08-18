package bg.softuni.philately.service.impl;

import bg.softuni.philately.model.dto.stamp.*;
import bg.softuni.philately.model.entity.Paper;
import bg.softuni.philately.model.entity.Stamp;
import bg.softuni.philately.model.entity.User;
import bg.softuni.philately.repository.PaperRepository;
import bg.softuni.philately.repository.StampRepository;
import bg.softuni.philately.repository.UserRepository;
import bg.softuni.philately.service.StampService;
import bg.softuni.philately.service.session.LoggedUser;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StampServiceImpl implements StampService {
    private final PaperRepository paperRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final StampRepository stampRepository;
    private final ModelMapper modelMapper;

    public StampServiceImpl(PaperRepository paperRepository, UserRepository userRepository, LoggedUser loggedUser, StampRepository stampRepository, ModelMapper modelMapper) {
        this.paperRepository = paperRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.stampRepository = stampRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(AddStampBindingModel addStampBindingModel) {
        Paper paper = paperRepository.findByPaperName(addStampBindingModel.getPaperName());
        User user = userRepository.findByUsername(loggedUser.getUsername());

        if (paper != null && user != null) {
            Stamp stamp = new Stamp();
            stamp.setName(addStampBindingModel.getStampName());
            stamp.setDescription(addStampBindingModel.getDescription());
            stamp.setImageUrl(addStampBindingModel.getImageUrl());
            stamp.setPrice(addStampBindingModel.getPrice());
            stamp.setPaper(paper);
            stamp.setOwner(user);

            stampRepository.save(stamp);

            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public StampHomeViewModel getHomeViewData() {
        User currentUser = userRepository.findByUsername(loggedUser.getUsername());
        List<Stamp> stamps = stampRepository.findAll();

        List<MyStampsDTO> myStamps = stamps.stream()
                .filter(stamp -> stamp.getOwner().getUsername().equals(loggedUser.getUsername()) && isPurchasable(stamp))
                .map(stamp -> modelMapper.map(stamp, MyStampsDTO.class))
                .toList();

        List<OfferedStampsDTO> offeredStamps = stamps.stream()
                .filter(stamp -> !stamp.getOwner().getUsername().equals(loggedUser.getUsername()) && isPurchasable(stamp))
                .map(stamp -> modelMapper.map(stamp, OfferedStampsDTO.class))
                .toList();

        List<MyPurchasesDTO> myPurchases = currentUser.getPurchasedStamps().stream()
                .map(stamp -> modelMapper.map(stamp, MyPurchasesDTO.class))
                .toList();

        List<MyWishlistDTO> myWishlist = currentUser.getWishedStamps().stream()
                .map(stamp -> modelMapper.map(stamp, MyWishlistDTO.class))
                .toList();

        return new StampHomeViewModel(myStamps, offeredStamps, myPurchases, myWishlist);
    }

    private boolean isPurchasable(Stamp stamp) {
        List<User> users = userRepository.findAll();

        return users.stream()
                .noneMatch(user -> user.getPurchasedStamps().contains(stamp));
    }

    @Override
    @Transactional
    public void toWishlist(Long id) {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        Optional<Stamp> optionalStamp = stampRepository.findById(id);

        optionalStamp.ifPresent(stamp -> user.getWishedStamps().add(stamp));
    }

    @Override
    @Transactional
    public void removeFromWishlist(Long id) {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        Optional<Stamp> optionalStamp = stampRepository.findById(id);

        optionalStamp.ifPresent(stamp -> user.getWishedStamps().remove(stamp));
    }

    @Override
    @Transactional
    public void buy() {
        User user = userRepository.findByUsername(loggedUser.getUsername());

        user.getPurchasedStamps().addAll(user.getWishedStamps());

        user.getWishedStamps().clear();
    }
}
