package vn.ptit.b19dccn576.BE.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ptit.b19dccn576.BE.model.Type;
import vn.ptit.b19dccn576.BE.repository.TypeRepository;
import vn.ptit.b19dccn576.BE.service.ITypeService;

import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {
    private TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }
}
