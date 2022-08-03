package com.web.bookStore.bookModule;

import com.web.bookStore.shared.entitiy.BookEntity;
import com.web.bookStore.shared.model.ResponseObject;
import com.web.bookStore.uploadImageModule.ImageStorageServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.web.bookStore.bookModule.BookConstant.Message.SUCCESSFULLY;

@Log4j2
@CrossOrigin
//@RestController
@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("searchByTitle")
    public ResponseEntity<ResponseObject> searchBookByTitle(@RequestBody String keyword) {
        try {
            return ResponseEntity.ok().body(new ResponseObject(SUCCESSFULLY, bookService.searchByTitle(keyword)));
        } catch (Exception e) {
            log.error(String.format("Occur error: %s, \n %s", e.getMessage(), e));
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("book-list")
    public ResponseEntity<ResponseObject> getAllBooks() {
        try {
            return ResponseEntity.ok().body(new ResponseObject(SUCCESSFULLY, bookService.findAll()));
        } catch (Exception e) {
            log.error(String.format("Occur error: %s, \n %s", e.getMessage(), e));
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getBookById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(new ResponseObject(SUCCESSFULLY, bookService.findById(Integer.parseInt(id))));
        } catch (Exception e) {
            log.error(String.format("Occur error: %s, \n %s", e.getMessage(), e));
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseObject> addBook(@RequestBody BookEntity book) {
        try {
            return ResponseEntity.ok().body(new ResponseObject(SUCCESSFULLY, bookService.save(book)));
        } catch (Exception e) {
            log.error(String.format("Occur error: %s, \n %s", e.getMessage(), e));
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseObject> updateBook(@RequestBody BookEntity book) {
        try {
            return ResponseEntity.ok().body(new ResponseObject(SUCCESSFULLY, bookService.save(book)));
        } catch (Exception e) {
            log.error(String.format("Occur error: %s, \n %s", e.getMessage(), e));
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ResponseObject> removeBook(@PathVariable String id) {
        try {
            bookService.remove(Integer.parseInt(id));
            return ResponseEntity.ok().body(new ResponseObject(SUCCESSFULLY));
        } catch (Exception e) {
            log.error(String.format("Occur error: %s, \n %s", e.getMessage(), e));
            throw new RuntimeException(e.getMessage());
        }
    }

//    @PostMapping(value = "/add/image/{id}", consumes = {MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<String> uploadImage(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile image) {
//        try {
//            BookEntity book = bookService.findById(id);
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            Iterator<String> it = multipartRequest.getFileNames();
//            MultipartFile multipartFile = multipartRequest.getFile(it.next());
//            String fileName = id + ".png";

//            byte[] imageBytes = image.getBytes();
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/book/book-" + id)));
//            stream.write(imageBytes);
//            stream.close();
//
//            log.info("uploaded image: " + image.getOriginalFilename());
//
//            return new ResponseEntity<String>("Upload Success!", HttpStatus.OK);
//        } catch (Exception e) {
//            log.error(String.format("Occur error: %s, \n %s", e.getMessage(), e));
//            return new ResponseEntity<String>("Upload failed!", HttpStatus.BAD_REQUEST);
//        }
//    }


    @Autowired
    private ImageStorageServiceImpl storageService;

    @PostMapping("/add/image/{id}")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            String generatedFileName = storageService.storeFile(image);
            return ResponseEntity.ok(
                    new ResponseObject("Upload file successfully", generatedFileName)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject(e.getMessage())
            );
        }
    }

}
