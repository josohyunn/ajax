package shop.mtcoding.ajax.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TechRepository extends JpaRepository<Tech, Integer> {

    // select * from tech_tb where category_id = :categoryId
    @Query("select t from Tech t where t.category.id = :categoryId")
    List<Tech> findByCategoryId(@Param("categoryId") Integer categoryId);
}
