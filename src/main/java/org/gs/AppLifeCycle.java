package org.gs;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

import org.gs.RestfulWebService.Movie;
import org.gs.RestfulWebService.MovieRepository;
import org.jboss.logging.Logger;
import jakarta.inject.Inject;

@ApplicationScoped
public class AppLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(AppLifeCycle.class);

    @Inject
    MovieRepository movieRepository;

    void onStart(@Observes StartupEvent ev) {
        
        Optional<Movie> movieUp = movieRepository.findByIdOptional(2L);
        if(movieUp.isPresent()) {
            LOGGER.info("Application démarrée, films :" + movieUp.get().getTitle());
        } else {
            LOGGER.error("Application démarrée, pas de film trouvé");
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("Application arretée");
    }
}

