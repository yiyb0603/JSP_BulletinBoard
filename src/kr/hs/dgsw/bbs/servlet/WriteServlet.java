package kr.hs.dgsw.bbs.servlet;

import kr.hs.dgsw.bbs.Bulletin;
import kr.hs.dgsw.bbs.SimpleDbBulletin;
import kr.hs.dgsw.bbs.Writing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/bbs/write.dgsw")
public class WriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String writer = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if ("dgswId".equals(cookie.getName())) {
                writer = cookie.getValue();
            }
        }

        Bulletin bulletin = new SimpleDbBulletin();
        Writing writing = new Writing();

        writing.setTitle(title);
        writing.setContent(content);
        writing.setWriter(writer);

        bulletin.write(writing);
        response.getWriter().print("OK");
    }
}
