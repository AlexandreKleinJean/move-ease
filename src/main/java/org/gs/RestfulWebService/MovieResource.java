package org.gs.RestfulWebService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/movie")
public class MovieResource {

    private Logger LOGGER = Logger.getLogger(MovieResource.class);

    @Inject
    Validator validator; 

    @Inject
    MovieRepository movieRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMovies() {
        LOGGER.debug("Get all movies from the database");
        List<Movie> movies = movieRepository.listAll();
        return Response.ok(movies).build();
    }

    /*@GET
    @Path("/{genre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieByGenre(@PathParam("genre") String genre) {
        System.out.println("Rechercher des films du genre : " + genre);
        List<Movie> movies = movieRepository.findByGenre(genre);
        if (movies.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(movies).build();
        }
    }*/

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieById(@PathParam("id") Long id) {
        LOGGER.debug("Get one movie by id");
        return movieRepository.findByIdOptional(id)
            .map(movie -> Response.ok(movie).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMovie(Movie movie) {

        Set<ConstraintViolation<Movie>> validate = validator.validate(movie);
        if(validate.isEmpty()){
            movieRepository.persist(movie);
            return Response.status(Response.Status.CREATED).entity(movie).build();
        } else {
            String customErrors = validate.stream()
                .map(customError -> customError.getMessage())
                .collect(Collectors.joining(","));
            return Response.status(Response.Status.BAD_REQUEST).entity(customErrors).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovieById(@PathParam("id") Long id) {
        boolean deleted = movieRepository.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
