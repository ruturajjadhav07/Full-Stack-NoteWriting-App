package com.relation.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relation.demo.dto.NotesDto;
import com.relation.demo.entity.Notes;
import com.relation.demo.entity.User;
import com.relation.demo.repository.NotesRepository;
import com.relation.demo.repository.UserRepository;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private UserRepository userRepository;

    // create notes
    public Notes createNotes(int id, Notes note) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }

        note.setDate(java.sql.Date.valueOf(LocalDate.now()));
        note.setCompleted(false);
        note.setUser(user);

        return notesRepository.save(note);

    }

    // find all notes by one user
    public List<NotesDto> getNotesByUserId(int userId) {
        @SuppressWarnings("unused")
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        List<Notes> notesList = notesRepository.findByUserId(userId);

        return notesList.stream().map(note -> new NotesDto(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getDate(),
                note.isCompleted(),
                note.getUser().getUser_id())).collect(Collectors.toList());
    }

    // edit notes
    public Notes editByUserId(int id, int noteId, Notes note) {
        @SuppressWarnings("unused")
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        Notes existingNote = notesRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id: " + noteId));

        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }

        if (existingNote.getUser().getUser_id() != id) {
            throw new IllegalArgumentException("Note does not belong to the specified user");

        }

        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        existingNote.setCompleted(note.isCompleted());

        return notesRepository.save(existingNote);
    }

    public void deleteByUserId(int id, int noteId) {
        @SuppressWarnings("unused")
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        Notes existingNote = notesRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id: " + noteId));

        if (existingNote.getUser().getUser_id() != id) {
            throw new IllegalArgumentException("You cant delete this note");
        }

        notesRepository.delete(existingNote);
    }
}
