package com.ciurezu.gheorghe.dragos.greenlight;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Address;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Role;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.StandardCategories;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AchievementLevelDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CategoryDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;
import com.ciurezu.gheorghe.dragos.greenlight.service.AchievementService;
import com.ciurezu.gheorghe.dragos.greenlight.service.CategoryService;
import com.ciurezu.gheorghe.dragos.greenlight.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GreenLightApplication {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(GreenLightApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService, AchievementService achievementService,
                          CategoryService categoryService) throws BadRequestException, ConflictException {
        return args -> {
//         init(userService,achievementService,categoryService);
        };
    }

    void init(UserService userService, AchievementService achievementService,
              CategoryService categoryService) throws BadRequestException {
        userService.saveRole(new Role(null, "ROLE_USER"));
        userService.saveRole(new Role(null, "ROLE_ADMIN"));
        userService.saveRole(new Role(null, "ROLE_SHOPPER"));
        initCategories(categoryService);
        initAchievements(achievementService);

        User user = new User();
        user.setId(null);
        user.setPassword("1234");
        user.setUsername("dragoss");
        user.setCoins(20L);


        Address entity = new Address();
        entity.setId(null);
        entity.setStreet("salut");
        entity.setTown("Dragos");
        entity.setStreet("Hello");
        entity.setStreetNr(37);

        user.setAddress(entity);
        userService.saveUser(user);
        user.setAddress(null);

        user.setId(null);
        user.setPassword("1234");
        user.setUsername("dragos2");
        user.setCoins(40L);
        userService.saveUser(user);

        user.setId(null);
        user.setPassword("1234");
        user.setUsername("greenday");
        user.setCoins(10L);

        userService.saveUser(user);
        user.setId(null);
        user.setPassword("1234");
        user.setUsername("charliechaplin");

        user.setCoins(15L);

        userService.saveUser(user);

        user.setId(null);
        user.setPassword("1234");
        user.setUsername("christopher");
        user.setCoins(18L);
        userService.saveUser(user);

        userService.addRoleToUser("christopher", "ROLE_USER");
        userService.addRoleToUser("dragoss", "ROLE_USER");
        userService.addRoleToUser("dragoss","ROLE_ADMIN");
        UserDTO userr = new UserDTO();
        userr.setUsername(user.getUsername());

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("dragoss");
        achievementService.addLevel0Achievements(userDTO);
    }

    private void initCategories(CategoryService categoryService) throws BadRequestException {
        CategoryDTO dto1 = new CategoryDTO();
        dto1.setName(StandardCategories.METAL);

        CategoryDTO dto2 = new CategoryDTO();
        dto2.setName(StandardCategories.PLASTIC);

        CategoryDTO dto3 = new CategoryDTO();
        dto3.setName(StandardCategories.GLASS);

        categoryService.saveCategory(dto1);
        categoryService.saveCategory(dto2);
        categoryService.saveCategory(dto3);
    }

    private void initAchievements(AchievementService achievementService) {
        initMetalAchievement(achievementService);
    }

    private void initMetalAchievement(AchievementService achievementService) {
        AchievementLevelDTO achMetalLvl0 = new AchievementLevelDTO();
        CategoryDTO metalCategory = new CategoryDTO();
        metalCategory.setName(StandardCategories.METAL);
        achMetalLvl0.setMaxPoints(100);
        achMetalLvl0.setCategory(metalCategory);
        achMetalLvl0.setLevel(0);
        achMetalLvl0.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_bronz_metal.png?generation=1687355661767428&alt=media");
        achMetalLvl0.setAchievementName("Metal Collector I");

        AchievementLevelDTO achMetalLvl1 = new AchievementLevelDTO();
        achMetalLvl1.setMaxPoints(100);
        achMetalLvl1.setCategory(metalCategory);
        achMetalLvl1.setLevel(1);
        achMetalLvl1.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_argint_metal.png?generation=1687355697064833&alt=media");
        achMetalLvl1.setAchievementName("Metal Collector II");

        AchievementLevelDTO achMetalLvl2 = new AchievementLevelDTO();
        achMetalLvl2.setMaxPoints(100);
        achMetalLvl2.setCategory(metalCategory);
        achMetalLvl2.setLevel(2);
        achMetalLvl2.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_aur_metal.png?generation=1687355727626713&alt=media");
        achMetalLvl2.setAchievementName("Metal Collector III");

        ///Plastic

        AchievementLevelDTO achPlasticLvl0 = new AchievementLevelDTO();
        CategoryDTO plasticCategory = new CategoryDTO();
        plasticCategory.setName(StandardCategories.PLASTIC);
        achPlasticLvl0.setMaxPoints(100);
        achPlasticLvl0.setCategory(plasticCategory);
        achPlasticLvl0.setLevel(0);
        achPlasticLvl0.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_bronz_plastic.jpg?generation=1687355761480961&alt=media");
        achPlasticLvl0.setAchievementName("Plastic Collector I");

        AchievementLevelDTO achPlasticLvl1 = new AchievementLevelDTO();
        achPlasticLvl1.setMaxPoints(100);
        achPlasticLvl1.setCategory(plasticCategory);
        achPlasticLvl1.setLevel(1);
        achPlasticLvl1.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_argint_plastic.jpg?generation=1687355786088422&alt=media");
        achPlasticLvl1.setAchievementName("Plastic Collector II");

        AchievementLevelDTO achPlasticLvl2 = new AchievementLevelDTO();
        achPlasticLvl2.setMaxPoints(100);
        achPlasticLvl2.setCategory(plasticCategory);
        achPlasticLvl2.setLevel(2);
        achPlasticLvl2.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_aur_plastic.jpg?generation=1687355806538652&alt=media");
        achPlasticLvl2.setAchievementName("Plastic Collector III");

        ///Glass
        AchievementLevelDTO glassLvl0 = new AchievementLevelDTO();
        CategoryDTO glassCategory = new CategoryDTO();
        glassCategory.setName(StandardCategories.GLASS);
        glassLvl0.setMaxPoints(100);
        glassLvl0.setCategory(glassCategory);
        glassLvl0.setLevel(0);
        glassLvl0.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_bronz.png?generation=1687355836231176&alt=media");
        glassLvl0.setAchievementName("Glass Collector I");

        AchievementLevelDTO glassLvl1 = new AchievementLevelDTO();
        glassLvl1.setMaxPoints(100);
        glassLvl1.setCategory(glassCategory);
        glassLvl1.setLevel(1);
        glassLvl1.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_argint.png?generation=1687355866462507&alt=media");
        glassLvl1.setAchievementName("Glass Collector II");

        AchievementLevelDTO glassLvl2 = new AchievementLevelDTO();
        metalCategory.setName("metal");
        glassLvl2.setMaxPoints(100);
        glassLvl2.setCategory(glassCategory);
        glassLvl2.setLevel(2);
        glassLvl2.setImgLink("https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/medalie_aur.jpg?generation=1687355890975966&alt=media");
        glassLvl2.setAchievementName("Glass Collector III");

        achievementService.saveAchievementLevel(achMetalLvl0);
        achievementService.saveAchievementLevel(achMetalLvl1);
        achievementService.saveAchievementLevel(achMetalLvl2);
        achievementService.saveAchievementLevel(achPlasticLvl0);
        achievementService.saveAchievementLevel(achPlasticLvl1);
        achievementService.saveAchievementLevel(achPlasticLvl2);
        achievementService.saveAchievementLevel(glassLvl0);
        achievementService.saveAchievementLevel(glassLvl1);
        achievementService.saveAchievementLevel(glassLvl2);
    }

}
