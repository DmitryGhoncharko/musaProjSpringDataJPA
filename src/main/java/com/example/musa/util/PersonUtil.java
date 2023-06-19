package com.example.musa.util;

import com.example.musa.model.Person;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonUtil {
    public Person getOldestPerson(List<Person> personList) {
        personList.sort(Comparator.comparingInt(Person::getYearOfBorn));
        Person oldestPerson = personList.get(personList.size() - 1);
        List<Person> personListOnYearOfBorn = new ArrayList<>(personList.stream().filter(x -> oldestPerson.getYearOfBorn() == x.getYearOfBorn()).toList());
        personListOnYearOfBorn.sort(Comparator.comparingInt(Person::getMonthOfBorn));
        return personListOnYearOfBorn.get(personListOnYearOfBorn.size() - 1);
    }

    public int getAverageAge(List<Person> personList) {
        int allYearsSum = personList.stream().mapToInt(Person::getYearOfBorn).sum();
        int averageYear = allYearsSum / personList.size();
        Date date = new Date();
        return date.getYear() + 1900 - averageYear;
    }
    public List<Person> findPersonMoreThanAverageAge(List<Person> personList){
        int averageAge = getAverageAge(personList);
        int averageYear = new Date().getYear()-averageAge;
        return personList.stream().filter(x->x.getYearOfBorn()>averageYear).collect(Collectors.toList());
    }

    public List<Person> sortBySurNameReverse(List<Person> personList){
        personList.sort(Comparator.comparing(Person::getSurName));
        Collections.reverse(personList);
        return personList;
    }

}
