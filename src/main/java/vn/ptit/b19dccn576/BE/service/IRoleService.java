package vn.ptit.b19dccn576.BE.service;

import vn.ptit.b19dccn576.BE.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();

    Role save(Role role);
}
