package com.giang.repository;

import com.giang.repository.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("SELECT p.imgLink FROM Picture p WHERE p.postId = ?1")
    List<String> findImgByPostId(Integer postId);
}
