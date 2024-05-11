package intro;

import java.time.LocalDate;

public class Person {
    private int id;
   private String name;
   String lastName;
   LocalDate dob;

    public Person(int id, String name, String lastName, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "intro.Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
