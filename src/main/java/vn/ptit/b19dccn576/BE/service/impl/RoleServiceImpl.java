package vn.ptit.b19dccn576.BE.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ptit.b19dccn576.BE.model.Role;
import vn.ptit.b19dccn576.BE.repository.RoleRepository;
import vn.ptit.b19dccn576.BE.service.IRoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    private RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }
}
