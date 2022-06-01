package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")

public class ServletAdd extends HttpServlet {
    private AtomicInteger count = new AtomicInteger(4);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html; charset=utf-8");
//        request.setCharacterEncoding("utf-8");
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//// Создаем новых Пользователей
//        User user = new User(name, surname, salary);
//// Добавим нового Пользователя в модель
//        model.add(user, count.getAndIncrement());
//// Возвращаем Пользователю в ответ html-страницу
//        pw.print("<html>" + "<h3>Пользователь " + name + " " + surname + " с зарплатой = " + salary + " успешно создан. </h3>" +
//                "<a href = addUser.html>Создать нового Пользователя</a><br/>" + "<a href = index.jsp>Домой</a>" + "</html>");
//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        request.setCharacterEncoding("UTF-8");
        // Парсим JSON на получение данных - ФИО, зарплата.
        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();
        // Создадим нового Пользователя и добавим его в модель.
        User user = new User(name, surname, salary);
        model.add(user, count.getAndIncrement());
        // Настроим сервлет на возврат данных в виде JSON.
        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();
        // Возвращаем список Пользователей в ответ на запрос.
        pw.print(gson.toJson(model.getFromList()));
    }
}
