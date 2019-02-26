package com.giang.repository;

import com.giang.repository.entity.PostCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCustomRepository extends JpaRepository<PostCustom, Long> {
    @Query("SELECT DISTINCT p.id FROM PostCustom p LEFT JOIN p.listBenefit b ON p.id = b.postId WHERE b.benefitId in ?1 AND p.typeId = ?2 AND p.location LIKE ?3")
    List<Integer> filter(List<Integer> benefitIds, Integer typeId, String location);


}
