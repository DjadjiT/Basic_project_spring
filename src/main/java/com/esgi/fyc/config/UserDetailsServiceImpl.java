package com.esgi.fyc.config;


import com.esgi.fyc.exception.UserException;
import com.esgi.fyc.model.Users;
import com.esgi.fyc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.jboss.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Users user = null;
        try {
            user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserException("User Not Found with username: " + username));
        } catch (UserException e) {
            Logger.getLogger(UserDetailsServiceImpl.class).error(e.getMessage());
        }

        assert user != null;
        return UserDetailsImpl.build(user);
    }
}
