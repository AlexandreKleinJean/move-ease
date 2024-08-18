package org.gs.RestfulWebService;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie>{
    
    public List<Movie> findByGenre(String genre) {
        return list("LOWER(genre)", genre.toLowerCase());
    }

}
