package com.gherex.noteapp.repositories;

import com.gherex.noteapp.entities.Category;
import com.gherex.noteapp.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByArchivedTrue();

    List<Note> findByArchivedFalse();

    List<Note> findByCategory(Category category);

    List<Note> findByArchivedAndCategory_Name(boolean b, String categoryName);
}