package com.project.UserOperationsManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.UserOperationsManagement.Entity.Note;
import com.project.UserOperationsManagement.Entity.User;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByUser(User user);

}