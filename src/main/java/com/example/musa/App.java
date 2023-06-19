package com.example.musa;

import com.example.musa.model.Person;
import com.example.musa.repository.PersonRepository;
import com.example.musa.util.PersonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class App {
    private final PersonRepository personRepository;

    private final PersonUtil personUtil;
    @Autowired
    public App(PersonRepository personRepository, PersonUtil personUtil) {
        this.personRepository = personRepository;
        this.personUtil = personUtil;
    }
    public void start(){
      savePersonInDatabase();
        List<Person> personList = personRepository.findAll();
        System.out.println("Average age: " + personUtil.getAverageAge(personList));
        System.out.println("Oldest person: \n" + personUtil.getOldestPerson(personList));
        System.out.println("Persons more than average age: \n" + personUtil.findPersonMoreThanAverageAge(personList));
        System.out.println("Sorted list: \n" + personUtil.sortBySurNameReverse(personList));
    }

    public void savePersonInDatabase(){
        Person person1 = Person.builder().name("Vasya").surName("Some").yearOfBorn(1999).monthOfBorn(12).build();
        Person person2 = Person.builder().name("Vasya2").surName("Some2").yearOfBorn(1867).monthOfBorn(2).build();
        Person person3 = Person.builder().name("Vasya3").surName("Some3").yearOfBorn(1890).monthOfBorn(8).build();
        Person person4 = Person.builder().name("Vasya4").surName("Some4").yearOfBorn(1999).monthOfBorn(12).build();
        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);
        personRepository.flush();
    }
}
