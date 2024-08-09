package org.gs;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie extends PanacheEntity{
    public String title;
    public String description;
    public String director;
    public String country;
    public String genre;
}
