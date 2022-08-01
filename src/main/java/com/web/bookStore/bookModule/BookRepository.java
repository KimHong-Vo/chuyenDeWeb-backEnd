package com.web.bookStore.bookModule;

import com.web.bookStore.shared.entitiy.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    @Query(value = "SELECT b.* FROM book b WHERE b.title LIKE %:title%", nativeQuery = true)
    List<BookEntity> searchByTitle(@Param("title") String title);
}
