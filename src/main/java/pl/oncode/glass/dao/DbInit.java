package pl.oncode.glass.dao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.oncode.glass.model.User;
import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userDao.deleteAll();

        // Crete users
        User dan = new User.UserBuilder()
                .setUsername("dan")
                .setPassword(passwordEncoder.encode("dan123"))
                .setFirstname("Dan").setLastname("Kyoshek")
                .setRoles("USER")
                .setPermissions("")
                .createUser();
        User admin = new User.UserBuilder()
                .setUsername("admin")
                .setPassword(passwordEncoder.encode("admin123"))
                .setFirstname("Admin")
                .setLastname("God")
                .setRoles("ADMIN")
                .setPermissions("ACCESS_TEST1,ACCESS_TEST2")
                .createUser();
        User manager = new User.UserBuilder()
                .setUsername("manager")
                .setPassword(passwordEncoder.encode("manager123"))
                .setFirstname("Manager")
                .setLastname("Blach")
                .setRoles("MANAGER")
                .setPermissions("ACCESS_TEST1")
                .createUser();

        List<User> users = Arrays.asList(dan,admin,manager);

        // Save to db
        this.userDao.saveAll(users);
    }
}