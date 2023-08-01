package com.ciurezu.gheorghe.dragos.greenlight.service;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Role;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleUserCatDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.TransferMoneyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.*;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Boolean updateUserPhoto(UserWithPhotoDTO userPhoto) throws BadRequestException;
    UserDTO saveUserToDB(UserDTO user) throws ConflictException;

    Boolean saveUserAddress(UserWithAddressDTO userWithAddressDTO) throws BadRequestException;

    UserTokensDTO getSimpleUser(String username) throws BadRequestException;

    List<UserPctImg> getUsersByPoints();

    Boolean updateUserCoinsAndPoints(SimpleUserCatDTO user);

    Boolean updateUserAchievements(SimpleUserCatDTO user) throws BadRequestException;

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);
    void removeRoleToUser(String username, String roleName);


    Boolean addAchievementToUser(UserDTO user, AchievementDTO dto, AchievementLevelDTO achievementLevelDTO);

    SimpleUserWithAddressDTO getUser(String username) throws BadRequestException;

    String checkUserExistence(String user) throws BadRequestException;
    Boolean updateUserEmail(String user, String email) throws BadRequestException;
    Boolean updateUserPassword(String user, String oldPass,String newPass) throws BadRequestException;
    Boolean updatePhoneNumber(String user, String phoneNumber) throws BadRequestException;

    Boolean updateUserStreet(String user, String street) throws BadRequestException;
    Boolean updateUserStreetNr(String user,String streetNr) throws BadRequestException;
    Boolean updateUserTown(String user,String town) throws BadRequestException;

    Boolean checkForUsernameEmail(UserWithEmailDTO userWithEmailDTO) throws BadRequestException;

    Boolean transferMoney(TransferMoneyDTO dto) throws BadRequestException;


}
