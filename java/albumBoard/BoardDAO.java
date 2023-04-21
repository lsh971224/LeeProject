package albumBoard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.JDBConnPool;
public class BoardDAO extends JDBConnPool {
	  
	    // DB연결
	    public BoardDAO() {
	        try {
	            Context  ctx = new InitialContext();
	            Context envContext = (Context) ctx.lookup("java:/comp/env");
	            ds = (DataSource) envContext.lookup("jdbc/orcl");
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	    //삽입 기능 
	    public int insertFile(BoardDTO dto) {
			int applyResult = 0;
			try {
				String query = "INSERT INTO album ("
						+ " alnum, title, imgname, teacherid, count, nfile) "
						+ " VALUES ( "
						+ " album_seq.nextval, ?, ?, ?, ?, ?)";
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getImgName());
				psmt.setString(3, dto.getTeacherId());
				psmt.setInt(4, dto.getCount());
				psmt.setString(5, dto.getNfile());
				applyResult = psmt.executeUpdate();
			}catch(Exception e) {
				System.out.println("INSERT 중 예외 발생");
				e.printStackTrace();
			}
			return applyResult;
		}
	  //파일 목록을 반환합니다.
		public List<BoardDTO> myFileList(){
			List<BoardDTO> fileList = new Vector<BoardDTO>();
			String query = "SELECT * FROM album ORDER BY alnum DESC";
			
			try {
				psmt = con.prepareStatement(query); //쿼리 준비
				rs=psmt.executeQuery(); //쿼리 준비
				while(rs.next()) { //목록 안의 파일 수만큼 반복
					//DTO에 저장
					BoardDTO dto = new BoardDTO();
					dto.setAlnum(rs.getInt(1));
					dto.setTitle(rs.getString(2));
					dto.setImgName(rs.getString(3));
					dto.setTeacherId(rs.getString(4));
					dto.setWriteDate(rs.getDate(5));
					dto.setCount(rs.getInt(6));
					dto.setNfile(rs.getString(7));
					fileList.add(dto); //목록에 추가
				}
			}catch(Exception e) {
				System.out.println("SELECT 시 예외 발생");
				e.printStackTrace();
			}
			return fileList; // 목록 반환
			
		}
		public List<BoardDTO> getListPage(Map<String, Object> map) {
			List<BoardDTO> b1 = new Vector();
			String query = "select * from ("
					+ " select rownum pnum, s.* from( "
					+ " select b.* from album b ";
			if(map.get("searchStr")!=null) {
				query += " where "+ map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
			}
			query+= " order by alnum desc"
				+ "  ) s"
				+")"
				+" where pnum between ? and ? ";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1,map.get("start").toString());
				psmt.setString(2,map.get("end").toString());
				rs = psmt.executeQuery();
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setAlnum(rs.getInt("alnum"));
					dto.setTitle(rs.getString("title"));
					dto.setImgName(rs.getString("imgname"));
					dto.setTeacherId(rs.getString("teacherid"));
					dto.setWriteDate(rs.getDate("write_date"));
					dto.setCount(rs.getInt("count"));
					dto.setNfile(rs.getString("nfile"));
					b1.add(dto);
				}
			}catch(Exception e) {
				System.out.println("게시물 목록 읽는 중 에러");
				e.printStackTrace();
			}
			return b1;
		}
		public int countAll(Map<String, Object> map) {
			int totalCount = 0;
			String query = "select count(*) from album";
			if(map.get("searchStr")!=null){
				query+=" where "+map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
			}
				try {
					stmt = con.createStatement(); 
					rs = stmt.executeQuery(query);
					rs.next();
					totalCount = rs.getInt(1);
				}
				catch(Exception e) {
					System.out.println("게시물 카운트 중 에러");
					e.printStackTrace();
				}

				return totalCount;
			}
		
		//조회수 증가
		public void updateCount(int ALNUM) {
			String query ="update ALBUM set COUNT = COUNT+1 "
						+" where ALNUM=?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setInt(1, ALNUM);
				psmt.executeUpdate();
			}catch(Exception e) {
				System.out.println("조회수 증가 중 예외");
				e.printStackTrace();
			}
		}
		public BoardDTO getView(int alnum) {
			BoardDTO dto = new BoardDTO();
			String query = "select * from album where alnum = ?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setInt(1, alnum);
				rs = psmt.executeQuery();
				if(rs.next()) {
					dto.setAlnum(rs.getInt("alnum"));
					dto.setTitle(rs.getString("title"));
					dto.setImgName(rs.getString("imgname"));
					dto.setTeacherId(rs.getString("teacherid"));
					dto.setWriteDate(rs.getDate("write_date"));
					dto.setCount(rs.getInt("count"));
					dto.setNfile(rs.getString("nfile"));
				}
			}catch(Exception e) {
				System.out.println("상세 보기중 예외");
				e.printStackTrace();
			}
			
			return dto;
		}
		
		public int updatePost(BoardDTO dto) {
			int result=0;
	        try {
	           String query="update album "
	                    +" set title=?, imgname=?, write_date=?,nfile=?"
	                    +" where alnum = ? ";
	           psmt=con.prepareStatement(query);
	           psmt.setString(1, dto.getTitle());
	           psmt.setString(2, dto.getImgName());
	           psmt.setDate(3, dto.getWriteDate());
	           psmt.setString(4, dto.getNfile());
	           result=psmt.executeUpdate();
	        }catch(Exception e) {
	           System.out.println("게시뭃 수정 중 에러");
	           e.printStackTrace();
	        }
	        return result;

		}
		public boolean confirmId(String pass, int alnum) {
			boolean isRight = false;
			try {
				String query = "select count(*) from album where pass=? and idx = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, pass);
				psmt.setInt(2, alnum);
				rs = psmt.executeQuery(); //위에 쿼리가 ok면 1 아니면 0
				rs.next();
				if(rs.getInt(1)==1){
					isRight=true;
				}
			}catch(Exception e) {
				isRight = false;
				System.out.println("암호 검증 중 에러");
				e.printStackTrace();
			}
			return isRight;
		}
	
		public int deletePost(int alnum) {
			int result = 0;
			try {
				String query ="delete from album where alnum = ?";
				psmt = con.prepareStatement(query);
				psmt.setInt(1, alnum);
				result = psmt.executeUpdate();
			}catch(Exception e) {
				System.out.println("게시물 삭제 중 에러");
				e.printStackTrace();
			}
			return result;
		}
}
