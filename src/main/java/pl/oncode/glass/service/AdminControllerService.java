package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.UserDao;
import pl.oncode.glass.model.User;
import pl.oncode.glass.web.dto.addUser.AddUserDto;
import pl.oncode.glass.web.dto.deleteUser.DeleteUserDto;
import pl.oncode.glass.web.dto.fetchUser.FetchUserDto;

import java.util.ArrayList;
import java.util.List;

@Service(value = "adminControllerService")
public class AdminControllerService {

    private Logger logger = LoggerFactory.getLogger(AdminControllerService.class);

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    public AdminControllerService(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public void addUser(AddUserDto addUserDto) {
        // encode password using password encoder
        addUserDto.setPassword(passwordEncoder.encode(addUserDto.getPassword()));

        // change addUserDto to User
        User user = addUserDto.createUser();
        userDao.save(user);
    }

    public List<FetchUserDto> fetchUsers() {
        List<FetchUserDto> users = new ArrayList<>();
        userDao.findAllUsers().forEach(user -> users.add(FetchUserDto.createFetchUserDto(user)));

        return users;
    }

    public void deleteUser(DeleteUserDto deleteUserDto) {
        User user = userDao.findById(deleteUserDto.getId());
        logger.info("Deleting user with id=" + user.getId());
        userDao.delete(user);
    }
}
