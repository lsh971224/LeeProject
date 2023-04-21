package albumBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		int alnum;
		if(req.getParameter("alnum") == null)
		{
			 alnum = 0;
		}else
		{
			 alnum = Integer.parseInt(req.getParameter("alnum"));
		}
		System.out.println(alnum);
		dao.updateCount(alnum); //조회수 증가
		BoardDTO dto = dao.getView(alnum); //내용 읽어오기
		dao.close();
		//
		//게시물 뷰로 전달
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/blueViewPak/View.jsp").forward(req, resp);
	}
}
