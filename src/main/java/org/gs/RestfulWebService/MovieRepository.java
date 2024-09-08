package org.gs.RestfulWebService;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie>{
    
    public List<Movie> findByTitle(String title) {
        return list("LOWER(title) LIKE ?1", "%" + title.toLowerCase() + "%");
        //LIKE ?1 "%" titre "%" = requete JPQL cherchant un mot partiel dans le titre
    }

}
