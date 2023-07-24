package com.example.notepad;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class NoteController {
    final NoteService service;

    @GetMapping("/notes")
    List<NoteEntity> getNotes() {
        return service.getNotes();
    }

    @PostMapping("/notes")
    NoteEntity createNote(@RequestBody CreateNoteModel body) {
        return service.createNoteEntity(body);
    }

    @PutMapping("/notes/{id}")
    NoteEntity updateNote(@PathVariable("id") String id, @RequestBody CreateNoteModel body) {
        return service.updateNoteEntity(id, body);
    }

    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable("id") String id) {
        service.deleteNote(id);
    }

    @GetMapping("notes/{id}")
    NoteEntity getNoteById(@PathVariable("id") String id) {
        try {
            return service.getNoteById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + id + " not found", e);
        }
    }
}
