package fri.uni_lj.si.taskManagementService.model;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;

    //@OneToMany
    //private List<Task> tasks;

    public Board() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
