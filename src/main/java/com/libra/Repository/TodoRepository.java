package com.libra.Repository;

import com.libra.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findByCreatedBy(final String createdBy);

    int countByCreatedBy(final String username);

    List<Todo> deleteByCreatedBy(final String createdBy);

}
