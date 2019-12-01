package pl.oncode.glass.web.controller;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.web.bind.annotation.*;
        import pl.oncode.glass.service.AdminControllerService;
        import pl.oncode.glass.web.dto.addUser.AddUserDto;
        import pl.oncode.glass.web.dto.deleteUser.DeleteUserDto;
        import pl.oncode.glass.web.dto.fetchUser.FetchUserDto;

        import javax.validation.Valid;
        import java.util.List;



@RestController
@CrossOrigin
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private AdminControllerService adminControllerService;

    public AdminController(AdminControllerService adminControllerService) {
        this.adminControllerService = adminControllerService;
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

    @GetMapping("admin/users")
    public List<FetchUserDto> users(){
        logger.info("/admin/users");
        return adminControllerService.fetchUsers();
    }
}