package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static  Model getInstance() {
        return instance;
    }

// Создадим конструктор Model в котором проиницаилизируем поле model.
    private Model() {
        model = new HashMap<>();
        model.put(1, new User ("Alex", "Smith", 100000));
        model.put(2, new User ("John", "Anders", 101100));
        model.put(3, new User ("Ajar", "Avorn", 107000));
    }

    // Пропишем метод для добавления новых Пользователей.
    public void add(User user, int id) {
        model.put(id, user);
    }

    // Реализуем метод для удаления существующих Пользователей.

    public void del(int id) {
        model.remove(id);
    }

    // Реализуем метод для обновления существующих Пользователей.

    public void upd(User user, int id) {
        model.replace(id, user);
    }

    // Создадим еще один метод, при помощи которого можно получать Пользователей из Map.
    public Map<Integer, User> getFromList() {
        return model;
    }
}
