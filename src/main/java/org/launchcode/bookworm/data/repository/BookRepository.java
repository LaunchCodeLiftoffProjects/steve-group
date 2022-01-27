package org.launchcode.bookworm.data.repository;

import org.launchcode.bookworm.data.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}
