package albumBoard;

import java.sql.Date;

public class BoardDTO {
	private int alnum;
	private String title;
	private String imgName;
	private String teacherId;
	private Date writeDate;
	private int count; 
	private String nfile;
	public BoardDTO() {}
	public BoardDTO(int alnum, String title, String imgName, String teacherId, Date writeDate, int count,String nfile) {
		super();
		this.alnum = alnum;
		this.title = title;
		this.imgName = imgName;
		this.teacherId = teacherId;
		this.writeDate = writeDate;
		this.count = count;
		this.nfile = nfile;
	}
	
	public String getNfile() {
		return nfile;
	}
	public void setNfile(String nfile) {
		this.nfile = nfile;
	}
	public int getAlnum() {
		return alnum;
	}
	public void setAlnum(int alnum) {
		this.alnum = alnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setWriteDate(java.util.Date writeDate2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
