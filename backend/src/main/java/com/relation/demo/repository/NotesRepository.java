package com.relation.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.relation.demo.entity.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

    // List<Notes> findByUserId(User userId);

    @Query("SELECT n FROM Notes n WHERE n.user.user_id = :userId")
    List<Notes> findByUserId(@Param("userId") int userId);
}
