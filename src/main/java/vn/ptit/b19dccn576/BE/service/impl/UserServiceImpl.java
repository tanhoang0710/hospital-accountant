package vn.ptit.b19dccn576.BE.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ptit.b19dccn576.BE.model.User;
import vn.ptit.b19dccn576.BE.repository.RoleRepository;
import vn.ptit.b19dccn576.BE.repository.UserRepository;
import vn.ptit.b19dccn576.BE.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        var role = roleRepository.findById(user.getRole().getId());
        role.ifPresent(user::setRole);
        return userRepository.save(user);
    }
}
