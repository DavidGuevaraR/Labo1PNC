package Service;

import Model.Entity.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonService {
    private final List<Person> persons = new ArrayList<>();

    public void addPerson(Person person) {
        persons.add(person);
    }

    public List<Person> getPersons() {
        return persons;
    }
    public Person findPersonByDui(String dui) {
        for (Person person : persons) {
            if (person.getDui().equals(dui)) {
                return person;
            }
        }
        return null;
    }
}
