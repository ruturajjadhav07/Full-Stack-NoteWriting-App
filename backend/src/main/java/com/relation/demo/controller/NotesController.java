package com.relation.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.relation.demo.dto.NotesDto;
import com.relation.demo.entity.Notes;
import com.relation.demo.service.NotesService;

@RestController
@RequestMapping("/user")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PostMapping("/{id}")
    public ResponseEntity<String> createNotes(@PathVariable int id, @RequestBody Notes note) {
        try {
            notesService.createNotes(id, note);
            return ResponseEntity.ok("Note added succesfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/notes/{userId}")
    public ResponseEntity<?> getNotesByUser(@PathVariable int userId) {
        try {
            List<NotesDto> notesList = notesService.getNotesByUserId(userId);
            return ResponseEntity.ok(notesList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{userId}/{noteId}/edit")
    public ResponseEntity<?> editByUserId(@PathVariable int userId, @PathVariable int noteId, @RequestBody Notes note) {
        try {
            notesService.editByUserId(userId, noteId, note);
            return ResponseEntity.ok("Edited Successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{userId}/note/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable int userId, @PathVariable int noteId) {
        try {
            notesService.deleteByUserId(userId, noteId);
            return ResponseEntity.ok("Note deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}
