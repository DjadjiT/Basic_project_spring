package com.esgi.fyc.service;

import com.esgi.fyc.enumeration.RoleEnum;
import com.esgi.fyc.exception.UserException;
import com.esgi.fyc.model.Roles;
import com.esgi.fyc.model.UserDto;
import com.esgi.fyc.model.Users;
import com.esgi.fyc.repository.RoleRepository;
import com.esgi.fyc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void registerUser(UserDto userDto) throws UserException {
        Users user = fromDTOtoModel(userDto);
        Optional<Users> optionalUsers = userRepository.findByUsername(userDto.getUsername());
        if(optionalUsers.isPresent())
            userRepository.save(user);
        else
            throw new UserException("User already exist : "+userDto.getUsername());
    }

    public Users fromDTOtoModel(UserDto userDto) {
        Users user = new Users();
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setRoles(new Roles(RoleEnum.ROLE_USER.toString()));
        return userRepository.save(user);
    }

    public Users findByUsername(String username) throws UserException {
        Optional<Users> user = userRepository.findByUsername(username);
        if (user.isPresent())
            return user.get();
        throw new UserException("User doesn't exist :" + username);
    }

    public List<Users> findAllUserByRole(String roleName) throws UserException {
        Optional<Roles> role = roleRepository.findByRoleName(roleName);
        if(role.isPresent()){
            return role.get().getUsers();
        }
        throw new UserException("Role doesn't exist :" + roleName);
    }

    public void delete(Users user) {
        userRepository.delete(user);
    }
}
