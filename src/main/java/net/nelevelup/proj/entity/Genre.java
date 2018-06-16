package net.nelevelup.proj.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres", schema = "network_of_cinemas")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "genre")
    private String genre;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Film> films;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
