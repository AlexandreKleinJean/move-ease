package org;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.gs.Movie;
import org.gs.MovieRepository;
import org.gs.MovieResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class MovieResourceTestMockito {

    @InjectMock
    MovieRepository movieRepository;

    @Inject
    MovieResource movieResource;

    private Movie movie;

    @BeforeEach
    void setUp() {
    movie = new Movie();
    movie.setTitle("FirstMovie");
    movie.setDescription("MyFirstMovie");
    movie.setCountry("Planet");
    movie.setDirector("Me");
    movie.setId(1L);
    }

    @Test
    void getAll() {
    List<Movie> movies = new ArrayList<>();
    movies.add(movie);
    Mockito.when(movieRepository.listAll()).thenReturn(movies);
    Response response = movieResource.getAllMovies();
    assertNotNull(response);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    assertNotNull(response.getEntity());
    @SuppressWarnings("unchecked")
    List<Movie> entity = (List<Movie>) response.getEntity();
    assertFalse(entity.isEmpty());
    assertEquals("FirstMovie", entity.get(0).getTitle());
    assertEquals(1L, entity.get(0).getId());
    assertEquals("MyFirstMovie", entity.get(0).getDescription());
    assertEquals("Planet", entity.get(0).getCountry());
    assertEquals("Me", entity.get(0).getDirector());
    }

    @Test
    void createOK() {
  
      Mockito.doNothing().when(movieRepository).persist(ArgumentMatchers.any(Movie.class));
  
      Mockito.when(movieRepository.isPersistent(ArgumentMatchers.any(Movie.class))).thenReturn(true);
  
      Movie newMovie = new Movie();
      newMovie.setTitle("SecondMovie");
      newMovie.setDescription("MySecondMovie");
      newMovie.setCountry("Planet");
      newMovie.setDirector("Me");
      Response response = movieResource.createMovie(newMovie);
      assertNotNull(response);
      assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
      assertNotNull(response.getLocation());
      assertNull(response.getEntity());
    }

    @Test
    void deleteByIdOK() {
      Mockito.when(movieRepository.deleteById(1L)).thenReturn(true);
      Response response = movieResource.deleteMovieById(1L);
      assertNotNull(response);
      assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
      assertNull(response.getEntity());
    }
}
