package albumBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.AlertFunc;
import util.FileUtil;

@WebServlet("/albumBoard/pass.do")
public class PassController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/blueViewPak/Pass.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int alnum;
		if(req.getParameter("alnum") == null)
		{
			 alnum = 0;
		}else
		{
			 alnum = Integer.parseInt(req.getParameter("alnum"));
		}
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		//비밀번호 확인
				BoardDAO dao = new BoardDAO();
//				boolean confirmed = dao.confirmId(pass,alnum);
				dao.close();
				HttpSession session = req.getSession(); //세션 가져오기
				String id = (String) session.getAttribute("id");
				//무엇을 진행할지 삭제 수정
				if(pass.equals(pass)) { //비번 일치
					if(mode.equals("edit")) { //수정
						
						resp.sendRedirect("../albumBoard/edit.do?alnum="+alnum);
					}else if(mode.equals("delete")){ //삭제
						dao = new BoardDAO();
						BoardDTO dto = dao.getView(alnum);
						int result = dao.deletePost(alnum); //게시물 삭제
						dao.close();
						if(result == 1) { //게시물 삭제 성공 시 첨부 파일도 삭제
							String saveFileName = dto.getNfile();
							FileUtil.deleteFile(req,"/Storage",saveFileName);
						}
						AlertFunc.alertLocation(resp, "삭제되었습니다.", "../albumBoard/list.do");
					}
				}else { //비번 불일치
					AlertFunc.alertBack(resp,"비밀번호 검증이 실패 하였습니다.");
				}
			}
}
