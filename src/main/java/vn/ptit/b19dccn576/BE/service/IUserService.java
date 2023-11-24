package vn.ptit.b19dccn576.BE.service;

import vn.ptit.b19dccn576.BE.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User save(User user);
}
