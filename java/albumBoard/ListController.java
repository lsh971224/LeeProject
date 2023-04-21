package albumBoard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.PagingUtill;

public class ListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		Map<String,Object> map = new HashMap<>();
		
		//검색
		String searchType = req.getParameter("searchType");
		String searchStr = req.getParameter("searchStr");
		if(searchStr != null) {
			map.put("searchType", searchType);
			map.put("searchStr", searchStr);
		}
		HttpSession session = req.getSession();
        session.setAttribute("id", "1234");
		//전체 게시물 갯수 
				int totalCount = dao.countAll(map);
				
				ServletContext application = getServletContext();
				int pageSize = Integer.parseInt(application.getInitParameter("PAGE_SIZE")); //10
				int pageBlock = Integer.parseInt(application.getInitParameter("PAGING_BLOCK")); //5
				
				
				//페이지 확인
				int pageNum = 1;
				String pageTemp = req.getParameter("pageNum");
				if(pageTemp!=null && !pageTemp.equals("")) {
					pageNum = Integer.parseInt(pageTemp);
				}
				//목록에서 보여줄 게시물을 범위 계산
				int start = (pageNum-1)*pageSize+1;
				int end = pageNum*pageSize; //끝번호
				map.put("start", start);
				map.put("end", end);
				
				//DB에서 게시물 정보 읽기
				List<BoardDTO> boardList = dao.getListPage(map);
				List<BoardDTO>	mfileList = dao.myFileList();
				dao.close();
//				뷰에 값을 전달해 줄 것을 정리
				String pagingStr = PagingUtill.pagingCenter(totalCount, pageSize, pageBlock, pageNum, "../albumBoard/list.do");
				map.put("pagingStr", pagingStr);
				map.put("totalCount", totalCount);
				map.put("pageSize", pageSize);
				map.put("pageNum",pageNum);
				//뷰에 최종 전달
				req.setAttribute("boardList", boardList);
				req.setAttribute("map",map);
				req.setAttribute("fileList", mfileList);
				req.getRequestDispatcher("/blueViewPak/List.jsp").forward(req, resp);
			}

}
