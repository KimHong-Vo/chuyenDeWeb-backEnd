package com.web.bookStore.resources;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.bookStore.entities.Book;
import com.web.bookStore.services.BookService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/book")
public class BookResource {
	
	@Autowired
	BookService bookService; // auto generate an implement of book service class
	
//	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/book-list")
	public ResponseEntity<List<Book>> getAllBook(){
		return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
	}
// VRUS ABOVE METHOD
//	@RequestMapping("/book-list")
//	public List<Book> getAllBook(){
//		return bookService.findAll();
//	}
	
//	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/{id}")// dynamic url
	public Optional<Book> findBookById(@PathVariable("id") String id) {
		try {
			long parsedId = Long.parseLong(id);
			return bookService.findOne(parsedId);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}
//	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/add")
    public Book addnewBook(@RequestBody Book book) {
        return bookService.saveBook(book);

    }

	@PostMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
//	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping( "/add/image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("id") Long id,
            HttpServletResponse response, HttpServletRequest request
    ) {
        try {
            Optional<Book> book = bookService.findOne(id);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id + ".png";


            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/book/" + fileName)));
            stream.write(bytes);
            stream.close();

            return new ResponseEntity<String>("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Upload failed!", HttpStatus.BAD_REQUEST);
        }
        
    }
	 @PostMapping("/searchBook")
	    public List<Book> searchBook(@RequestBody String keyword) {
	        List<Book> bookList = bookService.blurrySearch(keyword);

	        return bookList;
	 }

//	 @CrossOrigin(origins = "http://localhost:8080")
	 @PostMapping("/remove")
    public ResponseEntity<String> removeBook(@RequestBody String id) {
        bookService.removeOne(Long.parseLong(id));
        return new ResponseEntity("Book Deleted", HttpStatus.OK);
    }
}
