package johny.dotsville;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import johny.dotsville.domain.entities.Actor;
import johny.dotsville.domain.entities.Category;
import johny.dotsville.domain.entities.City;
import johny.dotsville.domain.entities.Country;
import johny.dotsville.domain.entities.Film;
import johny.dotsville.domain.entities.FilmCategory;
import johny.dotsville.domain.entities.Language;

public class App 
{
    private EntityManager manager;

    private App() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dvdrental-pu");
        this.manager = factory.createEntityManager();
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App app = new App();
        app.linkFilmWithCategory();

    }

    private void linkFilmWithCategory() {
        Category category = new Category();
        category.setName("Триллер");

        Film film = new Film();
        film.setTitle("Багровые реки");
        Language lang = manager.find(Language.class, 2);
        film.setLanguage(lang);

        try {
            manager.getTransaction().begin();
            manager.persist(film);
            manager.persist(category);
            FilmCategory fc = new FilmCategory(film, category);
            manager.persist(fc);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
        }
    }

    private void getFilm() {
        Film film = manager.find(Film.class, 133);
        System.out.println(film);
    }

    private void addCity() {
        Country country = new Country();
        country.setCountry("Италия");
        City city = new City();
        city.setCity("Рим");
        city.setCountry(country);
        try {
            manager.getTransaction().begin();
//            manager.persist(country);
            manager.persist(city);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
        }
    }

    private void getCity(long id) {
        City city = manager.find(City.class, id);
        System.out.println(city.getCity());
        System.out.println(city.getCountry().getCountry());
    }

    private void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        manager.getTransaction().begin();
        manager.persist(category);
        manager.getTransaction().commit();
    }

    private void addLanguge(String name) {
        Language language = new Language();
        language.setName(name);
        manager.getTransaction().begin();
        manager.persist(language);
        manager.getTransaction().commit();
    }
    private void addCountry() {
        Country country = new Country();
        country.setCountry("Зимбабве");
        manager.getTransaction().begin();
        manager.persist(country);
        manager.getTransaction().commit();
    }

    private void getActorById(long id) {
        Actor actor = manager.find(Actor.class, id);
        System.out.println(actor);
    }
}
