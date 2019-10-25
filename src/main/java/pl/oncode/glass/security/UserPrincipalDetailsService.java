package pl.oncode.glass.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.oncode.glass.dao.UserDao;
import pl.oncode.glass.model.User;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserDao userDao;

    public UserPrincipalDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userDao.findByUsername(s);

        return new UserPrincipal(user);
    }
}
