package vn.ptit.b19dccn576.BE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ptit.b19dccn576.BE.model.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAll();

    Optional<Type> findById(Long id);
}
