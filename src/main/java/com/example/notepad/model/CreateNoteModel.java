package com.example.notepad.model;

/**
 * Represents a model for creating a new note.
 *
 * <p>
 * The {@code CreateNoteModel} class encapsulates the information required to create a new note.
 * It includes the title and description of the note.
 * </p>
 *
 * <p>
 * This class provides a convenient way to pass the necessary data for note creation
 * between different parts of the application.
 * </p>
 *
 * <p>
 * This class is an immutable record, meaning its values cannot be modified after creation.
 * </p>
 *
 * @param title The title of the note.
 * @param description The description of the note.
 */
public record CreateNoteModel(String title, String description) {
}
