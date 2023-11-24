package vn.ptit.b19dccn576.BE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ptit.b19dccn576.BE.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c JOIN FETCH c.type " +
            "WHERE c.type.id = :type " +
            "and month(c.lastModifiedDate) = :month " +
            "and c.percentage > 0 " +
            "and year(c.lastModifiedDate) = :year")
    List<Category> getCategoriesByTypeAndMonth(String type, String year, String month);
}
