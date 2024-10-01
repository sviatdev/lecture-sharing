package org.sviatdev.lecturesharing.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sviatdev.lecturesharing.models.Lecture;

public interface LectureDao extends JpaRepository<Lecture, Long> {

    @Query("SELECT l FROM Lecture l WHERE l.id = :id")
    Lecture findLectureById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Lecture l WHERE l.id = :id")
    void removeLecture(@Param("id") Long id);


}
