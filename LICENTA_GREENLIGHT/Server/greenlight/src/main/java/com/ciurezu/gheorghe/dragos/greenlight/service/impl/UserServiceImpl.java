package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Address;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Role;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.AchievementLevelEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.CompletedAchievementEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.InProgressAchievementEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleUserCatDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.TransferMoneyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.*;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;
import com.ciurezu.gheorghe.dragos.greenlight.repository.*;
import com.ciurezu.gheorghe.dragos.greenlight.service.UpdateUserService;
import com.ciurezu.gheorghe.dragos.greenlight.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService, UpdateUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final InProgressAchievementRepository inProgressAchievementRepository;
    private final AchievementLevelRepository achievementLevelRepository;
    private final CompletedAchievementRepository completedAchievementRepository;
    private final ModelMapper mapper;

    private final String USER_NOT_FOUND = "User not found in database";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        } else {
            log.info("User found in database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<AchievementLevelEntity> achievements = achievementLevelRepository.findAllByLevel0AndName(user.getUsername());


        return userRepository.save(user);
    }

    @Override
    public Boolean updateUserPhoto(UserWithPhotoDTO userPhoto) throws BadRequestException {
        User user = userRepository.findByUsername(userPhoto.getUsername());
        if (user == null) {
            throw new BadRequestException("User not found");
        }
        user.setPhotoUrl(userPhoto.getPhotoUrl());
        user = userRepository.save(user);

        return user.getPhotoUrl().equals(userPhoto.getPhotoUrl());
    }

    @Override
    public UserDTO saveUserToDB(UserDTO user) throws ConflictException {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userEntity = userRepository.findByUsername(user.getUsername());

        if (userEntity != null) {
            throw new ConflictException("User Already Exists");
        }

        userEntity = mapper.map(user, User.class);

        userEntity = userRepository.save(userEntity);

        return mapper.map(userEntity, UserDTO.class);
    }

    @Override
    public Boolean saveUserAddress(UserWithAddressDTO userWithAddressDTO) throws BadRequestException {
        User user = userRepository.findByUsername(userWithAddressDTO.getUser().getUsername());

        if (user == null) {
            throw new BadRequestException("User doesn't exists");
        }
        Address addressEntity = mapper.map(userWithAddressDTO.getAddress(), Address.class);
        addressEntity = addressRepository.save(addressEntity);

        user.setAddress(addressEntity);
        user = userRepository.save(user);

        return user.getAddress().getId() != null;
    }

    @Override
    public UserTokensDTO getSimpleUser(String username) throws BadRequestException {
        if (username == null) {
            throw new BadRequestException("User not found");
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("User not found");
        }

        UserTokensDTO userTokensDTO = mapper.map(user, UserTokensDTO.class);

        return userTokensDTO;
    }

    @Override
    public List<UserPctImg> getUsersByPoints() {
        List<User> userEntities = userRepository.findAllByOrderByScoreDesc();

        return userEntities.stream()
                .map(userEntity -> mapper.map(userEntity, UserPctImg.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean updateUserCoinsAndPoints(SimpleUserCatDTO user) {
        User userEntity = mapper.map(user, User.class);
        int updatedUsers = userRepository.updateCoins(1L, 1L, userEntity.getUsername());
        if (updatedUsers > 0) {
            return true;
        }
        throw new UsernameNotFoundException(USER_NOT_FOUND);
    }

    @Override
    public Boolean updateUserAchievements(SimpleUserCatDTO userDto) throws BadRequestException {
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            throw new BadRequestException("User not found");
        }

        InProgressAchievementEntity inProgress = inProgressAchievementRepository.
                findByUser_UsernameAndAchievementLvl_CategoryEntity_CategoryName(userDto.getUsername(), userDto.getCategory());
        if (inProgress == null) {

            List<CompletedAchievementEntity> completedAchievement =
                    completedAchievementRepository
                            .findByUser_UsernameAndAchievementLvl_CategoryEntityCategoryName
                                    (user.getUsername(), userDto.getCategory());

            if (completedAchievement.size() > 0) {
                return false;
            }
            throw new BadRequestException("Bad request");
        }

        Integer newProgressValue = inProgress.getCurrentProgress() + 50;

        if (newProgressValue >= inProgress.getAchievementLvl().getMaxPoints()) {
            // mark the current achievement completed
            CompletedAchievementEntity completedAchievement = new CompletedAchievementEntity();
            completedAchievement.setUser(inProgress.getUser());
            completedAchievement.setAchievementLvl(inProgress.getAchievementLvl());
            completedAchievementRepository.save(completedAchievement);

            // add the new achievement
            String categoryName = inProgress.getAchievementLvl().getCategoryEntity().getCategoryName();
            Integer newLevel = inProgress.getAchievementLvl().getLevel() + 1;
            AchievementLevelEntity achievementLevel = achievementLevelRepository
                    .findByCategoryNameAndLevel(categoryName, newLevel);

            if (achievementLevel == null) {
                inProgressAchievementRepository.delete(inProgress);
                return false;
            }

            inProgress.setCurrentProgress(0);
            inProgress.setAchievementLvl(achievementLevel);
            inProgressAchievementRepository.save(inProgress);

        } else {
            inProgress.setCurrentProgress(newProgressValue);
            inProgressAchievementRepository.save(inProgress);
        }
        return true;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    @Override
    public Boolean addAchievementToUser(UserDTO user, AchievementDTO dto, AchievementLevelDTO achievementLevelDTO) {
//        UserEntity userEntity = (UserEntity) mapper.DtoToEntity(user);
//        userEntity = userRepository.findByUsername(userEntity.getUsername());
//
//        AchievementLevelEntity achLvlEntity = (AchievementLevelEntity) mapper.DtoToEntity(achievementLevelDTO);
//        achLvlEntity = achievementLevelRepository.findByAchievementName(achLvlEntity.getAchievementName());
//
//        InProgressAchievementEntity achEntity = new InProgressAchievementEntity();
//        achEntity.setAchievementLvl(achLvlEntity);
//        achEntity.setCurrentProgress(0);
//        achEntity.setUser(userEntity);

        return null;
    }

    @Override
    public SimpleUserWithAddressDTO getUser(String username) throws BadRequestException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new BadRequestException("User not found");
        }
        return mapper.map(user, SimpleUserWithAddressDTO.class);
    }

    @Override
    public String checkUserExistence(String user) throws BadRequestException {
        User user1 = userRepository.findByUsername(user);
        if (user1 == null) {
            throw new BadRequestException("User not found");
        }
        return user1.getUsername();
    }


    @Override
    public Boolean updateUserEmail(String username, String email) throws BadRequestException {
        if (username == null || username.length() < 1) {
            throw new BadRequestException("send a valid username");
        }
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("User doesn't exists");
        }

        if (userRepository.findByEmail(email) != null) {
            throw new BadRequestException("Email already exists");
        }

        user.setEmail(email);
        user = userRepository.save(user);

        return user.getEmail().equals(email);
    }

    @Override
    public Boolean updateUserPassword(String username, String oldPass, String newPass) throws BadRequestException {
        if (username == null || username.length() < 1) {
            throw new BadRequestException("send a valid username");
        }
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("User doesn't exists");
        }

        if (oldPass == null || newPass == null || newPass.length() < 6 || oldPass.length() == 0) {
            throw new BadRequestException("New password should have more than 6 characters!");
        }

        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new BadRequestException("Old password is not recognized!");
        }

        String newCryptedPassword = passwordEncoder.encode(newPass);
        user.setPassword(newCryptedPassword);

        user = userRepository.save(user);

        return user.getPassword().equals(newCryptedPassword);
    }

    @Override
    public Boolean updatePhoneNumber(String username, String phoneNumber) throws BadRequestException {
        if (username == null || username.length() < 1) {
            throw new BadRequestException("send a valid username");
        }
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("User doesn't exists");
        }

        if (phoneNumber.length() != 10) {
            throw new BadRequestException("Invalid phone number, must be of ten value");
        }

        user.setPhoneNumber(phoneNumber);

        user = userRepository.save(user);
        return user.getPhoneNumber().equals(phoneNumber);
    }

    @Override
    public Boolean updateUserStreet(String username, String street) throws BadRequestException {
        if (username == null || username.length() < 1) {
            throw new BadRequestException("send a valid username");
        }
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("User doesn't exists");
        }

        if (street.length() < 4) {
            throw new BadRequestException("Invalid street");
        }

        user.getAddress().setStreet(street);

        user = userRepository.save(user);

        return user.getAddress().getStreet().equals(street);
    }

    @Override
    public Boolean updateUserStreetNr(String username, String streetNr) throws BadRequestException {
        if (username == null || username.length() < 1) {
            throw new BadRequestException("send a valid username");
        }
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("User doesn't exists");
        }

        int streetNrInt = Integer.parseInt(streetNr);
        if (streetNrInt < 1) {
            throw new BadRequestException("Street number must be positive");
        }
        user.getAddress().setStreetNr(streetNrInt);
        user = userRepository.save(user);

        return user.getAddress().getStreetNr() == streetNrInt;
    }

    @Override
    public Boolean updateUserTown(String username, String town) throws BadRequestException {
        if (username == null || username.length() < 1) {
            throw new BadRequestException("send a valid username");
        }
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadRequestException("User doesn't exists");
        }

        if (town.length() < 3) {
            throw new BadRequestException("Invalid town");
        }
        if (user.getAddress() == null) {
            Address adr = new Address();

            user.setAddress(adr);
        }
        user.getAddress().setTown(town);

        user = userRepository.save(user);

        return user.getAddress().getTown().equals(town);
    }

    @Override
    public Boolean checkForUsernameEmail(UserWithEmailDTO userWithEmailDTO) throws BadRequestException {
        if (userRepository.findByEmail(userWithEmailDTO.getEmail()) != null) {
            throw new BadRequestException("Email already exists!");
        }

        if (userRepository.findByUsername(userWithEmailDTO.getUsername()) != null) {
            throw new BadRequestException("Username already exists!");
        }
        return true;
    }

    @Override
    public Boolean transferMoney(TransferMoneyDTO dto) throws BadRequestException {

        User transferer = userRepository.findByUsername(dto.getUserThatTransfer());

        User receiver = userRepository.findByUsername(dto.getUserThatReceive());

        if(transferer == null){
            throw new BadRequestException("User that transfer doesn't exists");
        }

        if(receiver == null){
            throw new BadRequestException("User that receive doesn't exists!");
        }

        if( transferer.getCoins() - dto.getMoney() < 0 ){
            throw new BadRequestException("User doesn't have enough money");
        }

        transferer.setCoins(transferer.getCoins() - dto.getMoney() );
        receiver.setCoins(receiver.getCoins() + dto.getMoney() );

        userRepository.save(transferer);
        userRepository.save(receiver);
        return true;
    }
}
