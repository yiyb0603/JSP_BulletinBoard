package kr.hs.dgsw.java;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "name", urlPatterns = "/person")
public class PersonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        Person person = new Person("홍길동", "도둑", 21);
        String str = gson.toJson(person);
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(str);
        writer.close();
    }
}
