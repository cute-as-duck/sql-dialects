package art.gallery.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany(mappedBy = "works")
    private Set<Exhibition> exhibitionEntities = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        artist.getWorks().add(this);
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Work{" +
                "title='" + title + '\'' +
                '}';
    }
}
