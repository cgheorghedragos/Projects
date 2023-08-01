package com.ciurezu.gheorghe.dragos.greenlight.controller;


import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Role;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.StandardRoles;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleUserCatDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.TransferMoneyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserWithPhotoDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.response.GreenLightResponse;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserWithAddressDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserWithEmailDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;
import com.ciurezu.gheorghe.dragos.greenlight.service.AchievementService;
import com.ciurezu.gheorghe.dragos.greenlight.service.AddressService;
import com.ciurezu.gheorghe.dragos.greenlight.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final AchievementService achievementService;
    private final AddressService addressService;

    @GetMapping("/get-user")
    public ResponseEntity<GreenLightResponse<?>> getUser(@RequestParam("user") String username) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.getUser(username), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @GetMapping("/check-user")
    public ResponseEntity<GreenLightResponse<?>> checkUserAvailability(@RequestParam("user") String username) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.checkUserExistence(username), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/update-user-photo")
    public ResponseEntity<GreenLightResponse<?>> updateUserPhoto(@Valid @RequestBody UserWithPhotoDTO dto) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.updateUserPhoto(dto), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/update-user-email")
    public ResponseEntity<GreenLightResponse<?>> updateUserEmail(@RequestParam("user") String username,
                                                                 @RequestParam("email") String email) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.updateUserEmail(username, email), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/update-user-town")
    public ResponseEntity<GreenLightResponse<?>> updateUserTown(@RequestParam("user") String username,
                                                                @RequestParam("town") String town) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.updateUserTown(username, town), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/update-user-street")
    public ResponseEntity<GreenLightResponse<?>> updateUserStreet(@RequestParam("user") String username,
                                                                  @RequestParam("street") String street) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.updateUserStreet(username, street), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/update-user-street-nr")
    public ResponseEntity<GreenLightResponse<?>> updateUserStreetNr(@RequestParam("user") String username,
                                                                    @RequestParam("street_nr") String street_nr) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.updateUserStreetNr(username, street_nr), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/update-password")
    public ResponseEntity<GreenLightResponse<?>> updateUserPassword(@RequestParam("user") String username,
                                                                    @RequestParam("old_password") String oldPassword,
                                                                    @RequestParam("new_password") String newPassword) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.updateUserPassword(username, oldPassword, newPassword), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/update-phone-number")
    public ResponseEntity<GreenLightResponse<?>> updateUserPhoneNumber(@RequestParam("user") String username,
                                                                       @RequestParam("phone_number") String phoneNumber) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.updatePhoneNumber(username, phoneNumber), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @GetMapping("/all/desc-by-points")
    public ResponseEntity<GreenLightResponse<?>> getAllUsersOrderByCoins() {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.getUsersByPoints(), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @GetMapping("/get-user-money")
    public ResponseEntity<GreenLightResponse<?>> getUserToken(@RequestParam("username") String username) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.getSimpleUser(username), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/save2")
    public ResponseEntity<?> saveUser2(@RequestBody @Valid UserDTO user, BindingResult bindingResult) throws ConflictException {
        ResponseEntity<?> response = VoucherController.getResponseEntity(bindingResult);
        if (response != null) return response;

        user = userService.saveUserToDB(user);
        userService.addRoleToUser(user.getUsername(), StandardRoles.USER);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/user/save2").toUriString());
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(achievementService.addLevel0Achievements(user), "success");
        return ResponseEntity.created(uri).body(greenLightResponse);
    }


    @PutMapping("/add-address")
    public ResponseEntity<?> addUserAddress(@RequestBody @Valid UserWithAddressDTO userAddress, BindingResult bindingResult) throws BadRequestException {

        ResponseEntity<?> response = VoucherController.getResponseEntity(bindingResult);
        if (response != null) return response;

        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.saveUserAddress(userAddress), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/user-recycling")
    public ResponseEntity<GreenLightResponse<?>> updateCoinsForRecycling(@RequestBody @Valid SimpleUserCatDTO simpleUserCatDTO) throws BadRequestException {

        userService.updateUserCoinsAndPoints(simpleUserCatDTO);
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(userService.updateUserAchievements(simpleUserCatDTO), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/transfer-money")
    public ResponseEntity<?> transferMoney(@RequestBody @Valid TransferMoneyDTO transferMoneyDTO, BindingResult bindingResult) throws BadRequestException {

        ResponseEntity<?> response = VoucherController.getResponseEntity(bindingResult);
        if (response != null) return response;

        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<Boolean>(userService.transferMoney(transferMoneyDTO),"success");

        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/check-username-and-email")
    public ResponseEntity<?> checkUserWithEmail(@Valid @RequestBody UserWithEmailDTO user, BindingResult bindingResult) throws BadRequestException {

        ResponseEntity<?> response = VoucherController.getResponseEntity(bindingResult);
        if (response != null) return response;
        return ResponseEntity.ok().body(new GreenLightResponse<>(userService.checkForUsernameEmail(user), "success"));
    }




    @PostMapping
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }


    @Data
    class RoleToUserForm {
        private String username;
        private String roleName;
    }
}
