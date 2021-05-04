package com.libra.Repository;

import com.libra.Models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

    List<Notes> findByCreatedBy(final String createdBy);

    int countByCreatedBy(final String username);

    List<Notes> deleteByCreatedBy(final String createdBy);

}
