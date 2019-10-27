package fr.wcs.librairyWCS.librairy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;

	// List book
	@GetMapping("/books")
	public List<Book> index() {
		return bookRepository.findAll();
	}

	// Search By Word
	@PostMapping("/books/search")
	public List<Book> searchBook(@RequestBody Map<String, String> body) {
		String searchWord = body.get("text");
		return bookRepository.findByTitleContainingOrDescriptionContaining(searchWord, searchWord);
	}

	// Create Book
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	// Update Book
	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable int id, @RequestBody Book book) {
		Book bookToUpdate = bookRepository.findById(id).get();
		bookToUpdate.setTitle(book.getTitle());
		bookToUpdate.setAuthor(book.getAuthor());
		bookToUpdate.setDescription(book.getDescription());
		return bookRepository.save(bookToUpdate);
	}

	// Delete Book
	@DeleteMapping("/books/{id}")
	public boolean deleteBook(@PathVariable int id) {
		bookRepository.deleteById(id);
		return true;
	}

}
