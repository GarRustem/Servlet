package ru.appline.logic;

public class User {
    private String name;
    private String surname;
    private double salary;
// Добавляем все конструкторы комбинацией Alt + Insert
    public User(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }
// Добавляем геттеры и сеттеры комбинацией Alt + Insert

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
