package com.gherex.noteapp.services;

import com.gherex.noteapp.entities.Category;
import com.gherex.noteapp.entities.Note;
import com.gherex.noteapp.repositories.CategoryRepository;
import com.gherex.noteapp.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;

    public NoteServiceImpl(NoteRepository noteRepository, CategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Note createNote(Note note) {
        // para ignorar lo que venga del body y forzar el valor inicial
        note.setArchived(false);
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());

        if (note.getCategory() == null || note.getCategory().getId() == null) {
            Category defaultCategory = categoryRepository.findByName("Otro")
                    .orElseThrow(() -> new IllegalStateException("La categoría 'Otro' no existe."));
            note.setCategory(defaultCategory);
        }

        return noteRepository.save(note);
    }

    @Override
    public boolean existsById(Long id) {
        return noteRepository.existsById(id);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Optional<Note> updateNote(Long id, Note updatedNote) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isEmpty()) return Optional.empty();

        Note existingNote = optionalNote.get();
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        existingNote.setCategory(updatedNote.getCategory());
        existingNote.setUpdatedAt(LocalDateTime.now());

        Note savedNote = noteRepository.save(existingNote);
        return Optional.of(savedNote);
    }

    @Override
    public boolean archiveNote(Long id, boolean archive) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isEmpty()) return false;

        Note note = optionalNote.get();
        note.setArchived(archive);
        note.setUpdatedAt(LocalDateTime.now());
        noteRepository.save(note);
        return true;
    }

    @Override
    public List<Note> getArchivedNotes() {
        return noteRepository.findByArchivedTrue();
    }

    @Override
    public List<Note> getActiveNotes() {
        return noteRepository.findByArchivedFalse();
    }

    @Override
    public List<Note> getNotesByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return noteRepository.findByCategory(category);
    }

    public List<Note> getArchivedNotesByCategory(String categoryName) {
        return noteRepository.findByArchivedAndCategory_Name(true, categoryName);
    }

    public List<Note> getActiveNotesByCategory(String categoryName) {
        return noteRepository.findByArchivedAndCategory_Name(false, categoryName);
    }

}
