package com.libra.Repository;

import com.libra.Models.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, Integer> {

    List<TodoModel> findByCreatedBy(final String createdBy);

    int countByCreatedBy(final String username);

    List<TodoModel> deleteByCreatedBy(final String createdBy);

}
