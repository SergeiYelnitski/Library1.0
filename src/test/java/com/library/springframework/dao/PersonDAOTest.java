package com.library.springframework.dao;

import com.library.springframework.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonDAOTest {
  @Mock
  private JdbcTemplate jdbcTemplate;

  private PersonDAO personDAO;

  @BeforeEach
  void setUp() {
    personDAO = new PersonDAO(jdbcTemplate);
  }

  @Test
  void save_shouldInsertPersonIntoDB() {
    Person person = new Person(1, "John Doe", 1980);

    personDAO.save(person);

    verify(jdbcTemplate).update("INSERT INTO Person(name, birthday) VALUES(?, ?)",
        person.getName(), person.getBirthday());
  }
}
