package com.library.springframework.controller;

import com.library.springframework.dao.BookDAO;
import com.library.springframework.dao.PersonDAO;
import com.library.springframework.controllers.BooksController;
import com.library.springframework.models.Book;
import com.library.springframework.models.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BooksControllerTest {

  @InjectMocks
  private BooksController booksController;

  @Mock
  private BookDAO bookDAO;

  @Mock
  private PersonDAO personDAO;

  @Mock
  private Model model;

  @Mock
  private Book book;

  @Mock
  private Person person;

  @Mock
  private BindingResult bindingResult;

  @Before
  public void setUp() {
    when(bookDAO.index()).thenReturn(null);
    when(bookDAO.show(anyInt())).thenReturn(book);
    when(bookDAO.getBookOwner(anyInt())).thenReturn(Optional.of(person));
    when(personDAO.index()).thenReturn(null);
  }

  @Test
  public void index_ShouldReturnIndex_WhenCalled() {
    String result = booksController.index(model);
    verify(bookDAO, times(1)).index();
    verify(model, times(1)).addAttribute("books", null);
    assertEquals("books/index", result);
  }

  @Test
  public void newBook_ShouldReturnNew_WhenCalled() {
    String result = booksController.newBook(book);
    assertEquals("books/new", result);
  }

  @Test
  public void create_ShouldReturnRedirect_WhenBindingResultHasErrors() {
    when(bindingResult.hasErrors()).thenReturn(true);
    String result = booksController.create(book, bindingResult);
    verify(bookDAO, times(0)).save(book);
    assertEquals("books/new", result);
  }
}

