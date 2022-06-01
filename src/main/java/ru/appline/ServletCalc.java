package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.appline.logic.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/clc")

public class ServletCalc extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        int a = jobj.get("a").getAsInt();
        int b = jobj.get("b").getAsInt();
        char math = jobj.get("math").getAsCharacter();

        if (math == '+') {
            int resolve = a + b;
            pw.print(gson.toJson(resolve));
        } else if (math == '-') {
            int resolve = a - b;
            pw.print(gson.toJson(resolve));
        } else if (math == '*') {
            int resolve = a * b;
            pw.print(gson.toJson(resolve));
        } else if (math == '/') {
            int resolve = a / b;
            pw.print(gson.toJson(resolve));
        }
    }
}
