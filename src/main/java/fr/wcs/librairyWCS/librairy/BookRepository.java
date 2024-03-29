package fr.wcs.librairyWCS.librairy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByTitleContainingOrDescriptionContaining(String title, String description);
}
