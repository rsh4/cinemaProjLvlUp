package net.nelevelup.proj.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "costs", schema = "network_of_cinemas")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "film_kind")
    private Integer filmKind;

    @Column(name = "display_day")
    private Integer displayDay;

    @Column(name = "cost")
    private Integer cost;

    @ManyToMany(mappedBy = "costs", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Film> films;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFilmKind() {
        return filmKind;
    }

    public void setFilmKind(Integer filmKind) {
        this.filmKind = filmKind;
    }

    public Integer getDisplayDay() {
        return displayDay;
    }

    public void setDisplayDay(Integer displayDay) {
        this.displayDay = displayDay;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public List<Film> getProducts() {
        return films;
    }

    public void setProducts(List<Film> films) {
        this.films = films;
    }
}
