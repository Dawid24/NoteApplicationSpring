package paciorek.dawid.pl.NoteApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import paciorek.dawid.pl.NoteApplication.exception.ResourceFoundException;
import paciorek.dawid.pl.NoteApplication.model.Note;
import paciorek.dawid.pl.NoteApplication.repository.NoteRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId).orElseThrow(()-> new ResourceFoundException("Note", "id", noteId));
    }

    @PostMapping("/notes/{id}")
    public Note updateNoteById(@PathVariable(value = "id")Long noteId, Note noteDetails) {
        Note note = noteRepository.findById(noteId).orElseThrow(()-> new ResourceFoundException("Note", "id", noteId));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        return noteRepository.save(note);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(()-> new ResourceFoundException("Note", "id", noteId));
        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }

    /*public void deleteNoteById(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(()-> new ResourceFoundException("Note", "id", noteId));
        noteRepository.deleteById(note.getId());*/
    //}


}
