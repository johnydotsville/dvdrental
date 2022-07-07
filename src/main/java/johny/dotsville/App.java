package johny.dotsville;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import johny.dotsville.domain.entities.Actor;
import johny.dotsville.domain.entities.Address;
import johny.dotsville.domain.entities.Category;
import johny.dotsville.domain.entities.City;
import johny.dotsville.domain.entities.Country;
import johny.dotsville.domain.entities.Customer;
import johny.dotsville.domain.entities.Film;
import johny.dotsville.domain.entities.FilmCategory;
import johny.dotsville.domain.entities.Inventory;
import johny.dotsville.domain.entities.Language;
import johny.dotsville.domain.entities.Payment;
import johny.dotsville.domain.entities.Rental;
import johny.dotsville.domain.entities.Staff;
import johny.dotsville.domain.entities.Store;

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
        app.getPayment();
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
    private void addCountry() {
        Country country = new Country();
        country.setName("Зимбабве");
        manager.getTransaction().begin();
        manager.persist(country);
        manager.getTransaction().commit();
    }

    private void getActorById(long id) {
        Actor actor = manager.find(Actor.class, id);
        System.out.println(actor);
    }
}
