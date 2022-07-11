package johny.dotsville;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transaction;
import johny.dotsville.domain.entities.Actor;
import johny.dotsville.domain.entities.Address;
import johny.dotsville.domain.entities.Category;
import johny.dotsville.domain.entities.City;
import johny.dotsville.domain.entities.Country;
import johny.dotsville.domain.entities.Customer;
import johny.dotsville.domain.entities.Film;
import johny.dotsville.domain.entities.FilmActor;
import johny.dotsville.domain.entities.FilmCategory;
import johny.dotsville.domain.entities.Inventory;
import johny.dotsville.domain.entities.Language;
import johny.dotsville.domain.entities.Payment;
import johny.dotsville.domain.entities.Rental;
import johny.dotsville.domain.entities.Staff;
import johny.dotsville.domain.entities.Store;
import org.hibernate.CacheMode;
import org.hibernate.jpa.QueryHints;

import java.time.LocalDateTime;
import java.util.List;

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
//        app.getActorsFilms2(58);
        app.getActorsGenres2();
    }

    public void getActorsGenres2() {
        long actorId = 148;
        TypedQuery<Category> tqry = manager
                .createNamedQuery("get_actor_genres", Category.class)
                .setParameter("actorId", actorId);
        List<Category> categories = tqry.getResultList();
        for (Category cat : categories) {
            System.out.println(cat.getName());
        }
    }

    public void getActorsGenres() {
        TypedQuery<Category> tqry = manager
                .createNamedQuery("get_actor_genres", Category.class);
        List<Category> categories = tqry.getResultList();
        for (Category cat : categories) {
            System.out.println(cat.getName());
        }
    }

//    // id 58 снялся в 32 фильмах
//    public void getActorsFilms() {
//        long actorId = 58;
//        TypedQuery<FilmActor> tqry = manager
//                .createNamedQuery("get_actor_genres", FilmActor.class)
//                .setParameter("actorId", actorId);
//        List<FilmActor> fa = tqry.getResultList();
//        int filmsCount = 1;
//        for (FilmActor item : fa) {
//            System.out.println(String.format("Фильм #%d: %d", filmsCount, item.getId().getFilmId()));
//            filmsCount++;
//        }
//    }

    public void getCitiesOfCountry(String countryName) {
        TypedQuery<City> tqry = manager
                .createNamedQuery("get_country_cities", City.class)
                .setParameter("name", countryName);
        List<City> cities = tqry.getResultList();
        for (City city : cities) {
            System.out.println("Город: " + city.getName());
        }
    }

    public void updateNamedQuery(String name, String newName) {
        manager.getTransaction().begin();
        Query tq = manager
                .createNamedQuery("UPDATE_COUNTRY")
                .setParameter("name", name)
                .setParameter("newName", newName);
        int updated = tq.executeUpdate();
        manager.getTransaction().commit();
        System.out.println("Обновлено записей: " + updated);
    }

    public void implicitHintSetting(String searchName) {
        TypedQuery<Country> tquery = manager
                .createNamedQuery("GET_COUNTRY_BY_EXACT_NAME", Country.class)
                .setHint("org.hibernate.readOnly", true)
//                .setHint("org.hibernate.cacheable", true)
                .setHint(QueryHints.HINT_CACHEABLE, true)
//                .setHint(QueryHints.HINT_CACHE_MODE, CacheMode.GET)
                .setHint("org.hibernate.cacheMode", CacheMode.GET)
                .setParameter("name", searchName);
        Country country = tquery.getSingleResult();
        System.out.println(country.getName());
    }

    public void testReadOnlyNamedQuery(String name) {
        TypedQuery<Country> tquery = manager
                .createNamedQuery("find_country_by_name", Country.class)
                .setParameter("name", name);
        Country country = tquery.getSingleResult();

        System.out.println("Нашли: " + country.getName());
        // Хинт readOnly не запрещает менять найденную сущность, он для другого вообще
//        long id = country.getId();
//        country.setName(newName);
//        manager.getTransaction().begin();
//        manager.persist(country);
//        manager.getTransaction().commit();
//
//        country = manager.find(Country.class, id);
//        System.out.println("Имя должно измениться: " + country.getName());
    }

    public void useNakedSimpleQuery(String name) {
        Query tquery = manager.createQuery(
            "select c from Country c " +
            "where name = :name"
        );
        tquery.setParameter("name", name);
        Country country = (Country)tquery.getSingleResult();
        System.out.println(country.getName());
    }

    public void useNakedQuery(String name) {
        TypedQuery<Country> tquery = manager.createQuery(
            "select c from Country c " +
            "where name = :name", Country.class
        );
        tquery.setParameter("name", name);
        Country country = tquery.getSingleResult();
        System.out.println(country.getName());
    }

    private void getCountryByNameAndReplace(String name, String newName) {
        Country country = manager
                .createNamedQuery("GET_COUNTRY_BY_EXACT_NAME", Country.class)
                .setParameter("name", name)
                .setHint("org.hibernate.readOnly", true)
                .getSingleResult();
        System.out.println("Нашли: " + country.getName());
        long id = country.getId();
        country.setName(newName);
        manager.getTransaction().begin();
        manager.persist(country);
        manager.getTransaction().commit();

        country = manager.find(Country.class, id);
        System.out.println("Имя должно измениться: " + country.getName());
    }

    private void getCountryWhereNameStartsWith(String name) {
        List<Country> countries = manager
                .createNamedQuery("GET_COUNTRY_BY_LIKE_NAME", Country.class)
                .setParameter("name", name + "%")
                .getResultList();
        for (Country country : countries) {
            System.out.println(country.getName());
        }
    }

//    private void getCountryByIdNamedQuery() {
//        Country country = manager
//                .createNamedQuery("get_country_by_id_nq", Country.class)
//                .setParameter("id", "20")
//                .getSingleResult();
//        System.out.println(country);
//    }

    private void deleteCountryById(long id) {
        Country country = manager.find(Country.class, id);
        EntityTransaction tr = manager.getTransaction();
        try {
            tr.begin();
            manager.remove(country);
            tr.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tr.rollback();
        }
    }

    private void getCountryByName(String name) {
        Country country = manager
                .createNamedQuery("GET_COUNTRY_BY_EXACT_NAME", Country.class)
                .setParameter("name", name)
                .getSingleResult();
        System.out.println(country);
    }

    private void addCountry2(String name) {
        //Country country = new Country();
        //country.setName(name);
        Country country = manager.find(Country.class, 139);
//        country.setLastUpdate(LocalDateTime.of(2045, 12, 31, 5, 40, 35));
        country.setName("Мексика");
        System.out.println("До сохранения");
        System.out.println(country.getName());
        System.out.println(country.getLastUpdate());
        manager.getTransaction().begin();
        manager.persist(country);
        manager.getTransaction().commit();
        System.out.println("После сохранения");
        System.out.println(country.getName());
        System.out.println(country.getLastUpdate());
    }

    private void getCountry() {
        Country country = manager.find(Country.class, 23);
        System.out.println(String.format("В стране %s есть такие города:", country.getName()));
        for (City city : country.getCities()) {
            System.out.println(city.getName());
        }
    }

    private void getPayment() {
        Payment payment = manager.find(Payment.class, 17503);
        System.out.println("Клиент: " + payment.getCustomer().toString());
        System.out.println("Сотрудник: " + payment.getStaff().toString());
        System.out.println("Сумма: " + payment.getAmount());
        System.out.println("Фильм: " + payment.getRental().getInventory().getFilm().toString());
    }

    private void getRental() {
        Rental rental = manager.find(Rental.class, 1);
        System.out.println("Выдано: " + rental.getRentalDate());
        System.out.println("Возвращено: " + rental.getReturnDate());
        System.out.println("Клиент: " + rental.getCustomer().toString());
        System.out.println("Сотрудник: " + rental.getStaff().toString());
        System.out.println("Фильм:" + rental.getInventory().getFilm().toString());
        System.out.println("Магазин:" + rental.getInventory().getStore().toString());
    }

    private void getInventory() {
        Inventory inv = manager.find(Inventory.class, 1);
        String film = inv.getFilm().getTitle();
        String store = inv.getStore().getAddress().getAddress();
    }

    private void getCustomer() {
        Customer customer = manager.find(Customer.class, 599);
    }

    private void getStaff() {
        Staff staff = manager.find(Staff.class, 1);
    }

    private void getAddress() {
        Address address = manager.find(Address.class, 605);
        System.out.println(address.getCity().getName());
    }

    private void addCityAndCountry() {
        Country country = new Country();
        country.setName("Россия");

        City city = new City();
        city.setName("Москва");
        city.setCountry(country);

        country.getCities().add(city);

        manager.getTransaction().begin();
        manager.persist(country);
        manager.getTransaction().commit();
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
        String category = film.getFilmCategory().stream().findFirst().get().getCategory().getName();
        String lang = film.getLanguage().getName();
//        System.out.println(film);
        System.out.println(category);
        System.out.println(lang);
    }

    private void addCity() {
        Country country = new Country();
        country.setName("Италия");
        City city = new City();
        city.setName("Рим");
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
        System.out.println(city.getName());
        System.out.println(city.getCountry().getName());
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

    private void getActorById(long id) {
        Actor actor = manager.find(Actor.class, id);
        System.out.println(actor);
    }
}
