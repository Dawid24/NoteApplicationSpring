package paciorek.dawid.pl.NoteApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paciorek.dawid.pl.NoteApplication.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
