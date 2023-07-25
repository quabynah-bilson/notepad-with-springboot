package com.example.notepad.controller;

import com.example.notepad.service.NoteService;
import com.example.notepad.entity.NoteEntity;
import com.example.notepad.model.CreateNoteModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class NoteController {
    final NoteService service;

    @GetMapping("/notes")
    ResponseEntity<List<NoteEntity>> getNotes() {
        return ResponseEntity.ok(service.getNotes());
    }

    @PostMapping("/notes")
    ResponseEntity<NoteEntity> createNote(@RequestBody CreateNoteModel body) {
        try {
            return ResponseEntity.ok(service.createNoteEntity(body));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/notes/{id}")
    ResponseEntity<NoteEntity> updateNote(@PathVariable("id") String id, @RequestBody CreateNoteModel body) {
        try {
            return ResponseEntity.ok(service.updateNoteEntity(id, body));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + id + " not found", e);
        }
    }

    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable("id") String id) {
        service.deleteNote(id);
    }

    @GetMapping("/notes/{id}")
    ResponseEntity<NoteEntity> getNoteById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(service.getNoteById(id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + id + " not found", e);
        }
    }
}
