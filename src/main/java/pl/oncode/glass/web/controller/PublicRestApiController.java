package pl.oncode.glass.web.controller;

        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import pl.oncode.glass.dao.UserDao;
        import pl.oncode.glass.model.User;

        import java.util.List;

@RestController
@RequestMapping("api/public")
@CrossOrigin
public class PublicRestApiController {
    private UserDao userDao;

    public PublicRestApiController(UserDao userDao) {
        this.userDao = userDao;
    }

    // Available to all authenticated users
    @GetMapping("test")
    public String test1(){
        return "API Test";
    }

    // Available to managers
    @GetMapping("management/reports")
    public String reports(){
        return "Some report data";
    }

    // Available to ROLE_ADMIN
    @GetMapping("admin/users")
    public List<User> users(){
        return this.userDao.findAllUsers();
    }
}