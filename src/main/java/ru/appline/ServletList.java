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
import java.util.Map;

@WebServlet(urlPatterns = "/get")

public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html; charset=utf-8");
//        PrintWriter pw = response.getWriter();
//// Реализуем вывод всех Пользователей при вводе нуля
//        int id = Integer.parseInt(request.getParameter("id"));
//        if(id == 0) {
//            pw.print("<html>" + // Выводим в ответ html-страницу.
//                    "<h3>Доступные Пользователи:</h3></br>" + // Заголовок выводимой html-страницы.
//                    "ID Пользователя: " +
//                    "<ul>"); // Создаем формат текста в виде списка, который будем заполнять далее - для красоты отображения.
//            // Начнем перебирать все значения из нашего Map и выводить их на экран.
//            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
//                pw.print("<li>" + entry.getKey() + "</li>" + // Выводим лист с получением ключа из Map, лист закрываем.
//                        "<ul>" + // Создаем новый список, в который добавляем наших Пользователей
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
//                        "</ul>"); // Закрываем список, содержащий данные о конкретном Пользователе.
//            }
//            // Когда Map пройден - необходимо закрыть общий список, открытый на строке 27. Дополнительно, укажем ссылку на домашнюю страницу.
//            pw.print("</ul>" +
//                    "<a href = index.jsp>Домой</a>" +
//                    "</html>");
//        }
//        // Теперь, при вводе нуля - выводится полный список Пользователей.
//        else if (id > 0) {
//            // Выполним отлов сценария, чтобы при вводе числа, которое превышает размер Map, выводится сообщение об ошибке.
//            if(id > model.getFromList().size()) {
//                pw.print("<html>" +
//                        "<h3>Такого Пользователя не существует</h3>" +
//                        "<a href = index.jsp>Домой</a>" + // Добавим ссылку для возврата на домашнюю страницу.
//                        "</html>");
//            } else {
//                pw.print("<html>" +
//                        "<h3>Вы запросили Пользователя:</h3>" +
//                        "<br/>" +
//                        "Имя: " + model.getFromList().get(id).getName() + "<br/>" +
//                        "Фамилия: " + model.getFromList().get(id).getSurname() + "<br/>" +
//                        "Зарплата: " + model.getFromList().get(id).getSalary() + "<br/>" +
//                        "<a href = index.jsp>Домой</a>" +
//                        "</html>");
//            }
//        } else {
//            pw.print("<html>" +
//                    "<h3>ID должен быть больше нуля.</h3>" +
//                    "<a href = index.jsp>Домой</a>" +
//                    "</html>");
//        }
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuffer strb = new StringBuffer();
        String newline;

        try {
            BufferedReader reader = request.getReader();
            while ((newline = reader.readLine()) != null) {
                strb.append(newline);
            }
        } catch (Exception e) {
            System.out.println("error");
        }

        JsonObject jsnob = gson.fromJson(String.valueOf(strb), JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        int id = jsnob.get("id").getAsInt();

        if(id==0) {
            pw.print(gson.toJson(model.getFromList()));
        } else if (id > 0) {
            if (id > model.getFromList().size()) {
                pw.print("User is not exist.");
            } else {
                pw.print(gson.toJson(model.getFromList().get(id)));
            }
        } else {
            pw.print("Error: ID can not be < 0");
        }
    }
}
