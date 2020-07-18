package kr.hs.dgsw.bbs.servlet;

import kr.hs.dgsw.bbs.Bulletin;
import kr.hs.dgsw.bbs.SimpleDbBulletin;
import kr.hs.dgsw.bbs.Writing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bbs/modify.dgsw")
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String sequence = request.getParameter("sequence");

        Bulletin bulletin = new SimpleDbBulletin();
        Writing writing = new Writing();

        writing.setTitle(title);
        writing.setContent(content);
        writing.setSequence(Integer.parseInt(sequence));

        bulletin.update(writing);
        response.getWriter().print("OK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
