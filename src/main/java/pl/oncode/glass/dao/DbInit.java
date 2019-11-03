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
        User dan = new User("dan",passwordEncoder.encode("dan123"), "Dan", "Kyoshek", "USER","");
        User admin = new User("admin",passwordEncoder.encode("admin123"), "Admin", "God", "ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager",passwordEncoder.encode("manager123"), "Manager", "Blach", "MANAGER","ACCESS_TEST1");

        List<User> users = Arrays.asList(dan,admin,manager);

        // Save to db
        this.userDao.saveAll(users);
    }
}