package kr.hs.dgsw.bbs.servlet;

import com.google.gson.Gson;
import kr.hs.dgsw.bbs.Bulletin;
import kr.hs.dgsw.bbs.SimpleDbBulletin;
import kr.hs.dgsw.bbs.Writing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/bbs/hit.dgsw")
public class HitServlet extends HttpServlet {
    public HitServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sequence = Integer.parseInt(request.getParameter("bulletin_id"));
        java.util.Date date = new java.util.Date();
        Timestamp stamp = new Timestamp(date.getTime());
        String writer = request.getParameter("writer_id");

        Bulletin bulletin = new SimpleDbBulletin();
        Writing writing = new Writing();

        writing.setSequence(sequence);
        writing.setWriter(writer);
        writing.setHitTime(stamp);

        bulletin.increaseHit(writing);
        bulletin.increaseListHitCount(sequence);
        response.getWriter().print("OK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sequence = Integer.parseInt(request.getParameter("sequence"));

        Bulletin bulletin = new SimpleDbBulletin();
        List<Writing> list = bulletin.readHit(sequence);

        Gson gson = new Gson();
        String res = gson.toJson(list);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(res);
    }
}
