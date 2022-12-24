package art.gallery.services;

import art.gallery.models.Artist;
import art.gallery.models.Work;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkServiceCriteriaTest {

    private WorkServiceCriteria workServiceCriteria = new WorkServiceCriteria();
    private ArtistServiceJpql artistServiceJpql = new ArtistServiceJpql();

    @Test
    void findAll() {
        System.out.println(workServiceCriteria.findAll());
    }

    @Test
    void getWorkById() {
        System.out.println(workServiceCriteria.getWorkById(1));
    }

    @Test
    void deleteById() {
        workServiceCriteria.deleteById(1);
    }

    @Test
    void clear() {
        workServiceCriteria.clear();
    }

    @Test
    void addWork() {
        Artist artist = new Artist();
        artist.setFirstName("Katsushika");
        artist.setLastName("Hokusai");
        artistServiceJpql.addArtist(artist);
        Work work = new Work();
        work.setTitle("Red Fuji");
        work.setArtist(artist);
        System.out.println(workServiceCriteria.addWork(work));
    }
}