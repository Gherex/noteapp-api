package com.gherex.noteapp.services;

import com.gherex.noteapp.entities.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    List<Note> getAllNotes();

    Optional<Note> getNoteById(Long id);

    Note createNote(Note note);

    boolean existsById(Long id);

    void deleteNote(Long id);

    Optional<Note> updateNote(Long id, Note updatedNote);

    boolean archiveNote(Long id, boolean archive);

    List<Note> getArchivedNotes();

    List<Note> getActiveNotes();

    List<Note> getNotesByCategoryName(String category);

    List<Note> getArchivedNotesByCategory(String category);

    List<Note> getActiveNotesByCategory(String category);
}
