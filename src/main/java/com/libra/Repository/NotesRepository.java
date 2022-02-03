package com.libra.Repository;

import com.libra.Models.NotesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NotesModel, Integer> {

    List<NotesModel> findByCreatedBy(final String createdBy);

    int countByCreatedBy(final String username);

    List<NotesModel> deleteByCreatedBy(final String createdBy);

}
