package pl.oncode.glass.web.controller;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.web.bind.annotation.*;
        import pl.oncode.glass.dao.UserDao;
        import pl.oncode.glass.model.User;
        import pl.oncode.glass.service.AdminControllerService;
        import pl.oncode.glass.web.dto.addUser.AddUserDto;
        import pl.oncode.glass.web.dto.deleteUser.DeleteUserDto;
        import pl.oncode.glass.web.dto.fetchUser.FetchUserDto;

        import javax.validation.Valid;
        import java.util.List;

@RestController
@RequestMapping("api/public")
@CrossOrigin
public class PublicRestApiController {
    private Logger logger = LoggerFactory.getLogger(PublicRestApiController.class);

    private AdminControllerService adminControllerService;

    private UserDao userDao;

    public PublicRestApiController(AdminControllerService adminControllerService, UserDao userDao) {
        this.adminControllerService = adminControllerService;
        this.userDao = userDao;
    }

    // Available to all authenticated users
    @GetMapping("test")
    public String test1(){
        return "API Test";
    }

    @PostMapping("admin/user")
    public void addUser(@Valid @RequestBody AddUserDto addUserDto) {
        logger.info("/admin/user : Add new user " + addUserDto.getUsername());
        adminControllerService.addUser(addUserDto);
    }

    @CrossOrigin(origins = "*", methods = RequestMethod.DELETE)
    @DeleteMapping("/admin/user")
    public void deleteUser(@RequestBody DeleteUserDto deleteUserDto) {
        logger.info("/admin/user: Delete a user " + deleteUserDto);
        adminControllerService.deleteUser(deleteUserDto);
    }


    // Available to ROLE_ADMIN
    @GetMapping("admin/users")
    public List<FetchUserDto> users(){
        logger.info("/admin/users");
        return adminControllerService.fetchUsers();
    }
}