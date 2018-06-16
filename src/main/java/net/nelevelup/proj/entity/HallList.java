package net.nelevelup.proj.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "halls_list", schema = "network_of_cinemas")
public class HallList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sessions_list", joinColumns = @JoinColumn(name = "hall_list_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    private List<HallList> sessions;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public List<HallList> getSessions() {
        return sessions;
    }

    public void setSessions(List<HallList> sessions) {
        this.sessions = sessions;
    }
}
