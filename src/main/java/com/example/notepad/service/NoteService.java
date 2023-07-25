package com.example.notepad.service;

import com.example.notepad.entity.NoteEntity;
import com.example.notepad.model.CreateNoteModel;
import com.example.notepad.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteService {
    private final NoteRepository repository;

    @Autowired
    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // create a function to get notes from the repository
    public List<NoteEntity> getNotes() {
        return repository.findAll();
    }

    public NoteEntity createNoteEntity(CreateNoteModel body) {
        var note = new NoteEntity();
        note.setTitle(body.title());
        note.setDescription(body.description());
        note.setId(UUID.randomUUID());
        return repository.save(note);
    }

    public NoteEntity updateNoteEntity(String id, CreateNoteModel body) {
        var note = new NoteEntity();
        note.setId(UUID.fromString(id));
        note.setTitle(body.title());
        note.setDescription(body.description());
        repository.findById(note.getId())
                .ifPresentOrElse(
                        (n) -> {
                            n.setTitle(note.getTitle());
                            n.setDescription(note.getDescription());
                            repository.save(n);
                        },
                        () -> {
                            throw new RuntimeException("Note not found");
                        }
                );
        return note;
    }

    public void deleteNote(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    public NoteEntity getNoteById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }
}
