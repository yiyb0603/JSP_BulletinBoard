package kr.hs.dgsw.bbs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.hs.dgsw.bbs.Bulletin;
import kr.hs.dgsw.bbs.SimpleDbBulletin;
import kr.hs.dgsw.bbs.Writing;

/**
 * Servlet implementation class BbsList
 */
@WebServlet("/bbs/list.dgsw")
public class BbsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BbsListServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터베이스에서 글 목록을 읽어와서 클라이언트로 전송한다.
		Bulletin bulletin = 
				new SimpleDbBulletin();
		
		List<Writing> list = bulletin.list();
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(list);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonString);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
