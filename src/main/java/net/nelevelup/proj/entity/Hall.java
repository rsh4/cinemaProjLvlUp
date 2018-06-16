package net.nelevelup.proj.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "halls", schema = "network_of_cinemas")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "hall_type")
    private Integer hallType;

    @Column(name = "structure")
    private Integer structure;

    @ManyToMany(mappedBy = "halls", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cinema> cinemas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHallType() {
        return hallType;
    }

    public void setHallType(Integer hallType) {
        this.hallType = hallType;
    }

    public Integer getStructure() {
        return structure;
    }

    public void setStructure(Integer structure) {
        this.structure = structure;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }
}
