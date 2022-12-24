package art.gallery.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="exhibition_work",
            joinColumns = @JoinColumn(name = "exhibition_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id")
    )
    private Set<Work> works = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Work> getWorks() {
        return works;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addWork(Work work) {
        this.works.add(work);
    }

    @Override
    public String toString() {
        return "Exhibition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
