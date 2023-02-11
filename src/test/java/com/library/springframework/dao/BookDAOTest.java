package com.library.springframework.dao;

import com.library.springframework.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookDAOTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  private BookDAO bookDAO;

  @BeforeEach
  void setUp() {
    bookDAO = new BookDAO(jdbcTemplate);
  }

  @Test
  void save() {
    Book book = new Book(1, "Title 1", "Author 1", 2020);
    bookDAO.save(book);
    verify(jdbcTemplate).update("INSERT INTO Book(title, author,year_of_production) " +
        "VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYearOfProduction());
  }
}
