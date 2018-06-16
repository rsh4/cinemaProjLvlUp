package net.nelevelup.proj.entity;

import javax.persistence.*;

@Entity
@Table(name = "sessions_list", schema = "network_of_cinemas")
public class SessionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hall_list_id")
    private HallList hallList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id")
    private Session session;

    public SessionList(){

    }

    public SessionList(HallList hallList, Session session){
        this.hallList = hallList;
        this.session = session;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HallList getHallList() {
        return hallList;
    }

    public void setHallList(HallList hallList) {
        this.hallList = hallList;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
