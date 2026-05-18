package com.project.UserOperationsManagement.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.UserOperationsManagement.Entity.Note;
import com.project.UserOperationsManagement.Entity.User;
import com.project.UserOperationsManagement.Repository.NoteRepository;
import com.project.UserOperationsManagement.Repository.UserRepository;
import com.project.UserOperationsManagement.service.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private NoteService noteService;

    // Create Note
    @PostMapping
    public ResponseEntity<?> createNote(@Valid @RequestBody Note note) {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUser(user);

        Note savedNote = noteRepository.save(note);

        return ResponseEntity.ok(savedNote);
    }

    // Get All Notes of Logged-in User
    @GetMapping
    public ResponseEntity<?> getAllNotes() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Note> notes = noteRepository.findByUser(user);

        return ResponseEntity.ok(notes);
    }

    // Get Single Note by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {

        Note note = noteRepository.findById(id).orElse(null);

        if (note == null) {
            return ResponseEntity.badRequest()
                    .body("Note not found");
        }

        return ResponseEntity.ok(note);
    }

    // Update Note
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id,
                                        @RequestBody Note updatedNote) {

        Note note = noteRepository.findById(id).orElse(null);

        if (note == null) {
            return ResponseEntity.badRequest()
                    .body("Note not found");
        }

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());

        noteRepository.save(note);

        return ResponseEntity.ok("Note updated successfully");
    }

    // Delete Note
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {

        Note note = noteRepository.findById(id).orElse(null);

        if (note == null) {
            return ResponseEntity.badRequest()
                    .body("Note not found");
        }

        noteRepository.delete(note);

        return ResponseEntity.ok("Note deleted successfully");
    }
    
    @PutMapping("/archive/{id}")
    public ResponseEntity<?> archiveNote(@PathVariable Long id) {

        return ResponseEntity.ok(noteService.archiveNote(id));
    }
}


