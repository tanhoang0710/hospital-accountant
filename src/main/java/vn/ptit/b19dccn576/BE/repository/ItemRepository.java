package vn.ptit.b19dccn576.BE.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ptit.b19dccn576.BE.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i JOIN FETCH i.category " +
            "WHERE i.category.type.id = :type and month(i.lastModifiedDate) = :month " +
            "and year(i.lastModifiedDate) = :year")
    List<Item> getItemByTypeAndMonth(String type, String year, String month);

    Page<Item> findByCategoryTypeIdOrderByLastModifiedDateDesc(Pageable pageable, Long typeId);

    @Query("SELECT i FROM Item i JOIN FETCH i.category " +
            "WHERE i.category.type.id = :type " +
            "and year(i.lastModifiedDate) = :year")
    List<Item> getItemByTypeAndYear(String year, String type);
}
