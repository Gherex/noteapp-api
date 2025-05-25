package com.gherex.noteapp.controllers;

import com.gherex.noteapp.entities.Note;
import com.gherex.noteapp.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getNotes(@RequestParam(required = false) String category) {
        List<Note> notes = (category != null)
                ? noteService.getNotesByCategoryName(category)
                : noteService.getAllNotes();

        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.getNoteById(id);
        return note.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        Note savedNote = noteService.createNote(note);
        return ResponseEntity
                .created(URI.create("/notes/" + savedNote.getId()))
                .body(savedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (!noteService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updatedNote(@PathVariable Long id, @Valid @RequestBody Note updatedNote) {
        Optional<Note> savedNote = noteService.updateNote(id, updatedNote);
        return savedNote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<Void> archiveNote(@PathVariable Long id) {
        boolean updated = noteService.archiveNote(id, true);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/unarchive")
    public ResponseEntity<Void> unarchiveNote(@PathVariable Long id) {
        boolean updated = noteService.archiveNote(id, false);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/archived")
    public ResponseEntity<List<Note>> getArchivedNotes(@RequestParam(required = false) String category) {
        List<Note> notes = (category != null)
                ? noteService.getArchivedNotesByCategory(category)
                : noteService.getArchivedNotes();

        return ResponseEntity.ok(notes);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Note>> getActiveNotes(@RequestParam(required = false) String category) {
        List<Note> notes = (category != null)
                ? noteService.getActiveNotesByCategory(category)
                : noteService.getActiveNotes();

        return ResponseEntity.ok(notes);
    }


}

