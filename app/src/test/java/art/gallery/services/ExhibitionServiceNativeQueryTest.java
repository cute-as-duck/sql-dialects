package art.gallery.services;

import art.gallery.models.Exhibition;
import art.gallery.models.Work;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExhibitionServiceNativeQueryTest {

    private ExhibitionServiceNativeQuery exhibitionService = new ExhibitionServiceNativeQuery();
    private WorkServiceCriteria workService = new WorkServiceCriteria();

    @Test
    void findAll() {
        System.out.println(exhibitionService.findAll());
    }

    @Test
    void addExhibition() {
        Exhibition exhibition = new Exhibition();
        exhibition.setTitle("Impressionism");
        System.out.println(exhibitionService.addExhibition(exhibition));
    }

    @Test
    void deleteById() {
        exhibitionService.deleteById(2);
    }

    @Test
    void clear() {
        exhibitionService.clear();
    }

    @Test
    void addWork() {
        Work work = new Work();
        work.setTitle("Lake Kawaguchi");
        Work savedWork = workService.addWork(work);
        Exhibition exhibition = new Exhibition();
        exhibition.setTitle("Japanese Art");
        Exhibition savedExhibition = exhibitionService.addExhibition(exhibition);
        exhibitionService.addWorkToExhibition(savedExhibition, savedWork);
    }
}