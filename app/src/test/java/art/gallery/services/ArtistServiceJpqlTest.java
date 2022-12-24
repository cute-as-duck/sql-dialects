package art.gallery.services;

import art.gallery.models.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistServiceJpqlTest {

    private ArtistServiceJpql artistServiceJpql = new ArtistServiceJpql();

    @Test
    void findAll() {
        System.out.println(artistServiceJpql.findAll());
    }

    @Test
    void findById() {
        System.out.println(artistServiceJpql.findById(6));
    }

    @Test
    void addArtist() {
        Artist artist = new Artist();
        artist.setFirstName("Auguste");
        artist.setLastName("Renoir");
        System.out.println(artistServiceJpql.addArtist(artist));
    }

    @Test
    void deleteById() {
        artistServiceJpql.deleteById(8);
    }

    @Test
    void clear() {
        artistServiceJpql.clear();
    }
}