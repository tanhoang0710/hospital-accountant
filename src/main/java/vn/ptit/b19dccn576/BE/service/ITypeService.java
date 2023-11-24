package vn.ptit.b19dccn576.BE.service;

import vn.ptit.b19dccn576.BE.model.Type;

import java.util.List;

public interface ITypeService {
    List<Type> getAllTypes();

    Type save(Type type);
}
