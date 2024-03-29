package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {
//	파일 업로드 처리
	public static MultipartRequest uploadFile(HttpServletRequest req,String saveDirectory, int maxPostSize) {
		
		try {
			return new MultipartRequest(req, saveDirectory,maxPostSize,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	private static String getFileName(Part part) {
	    String contentDispositionHeader = part.getHeader("content-disposition");
	    String[] elements = contentDispositionHeader.split(";");
	    for (String element : elements) {
	        if (element.trim().startsWith("filename")) {
	            return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	//파일 다운로드

	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String directory, String saveFilename,
			String originFilename) {
		String saveDirectory = request.getServletContext().getRealPath(directory);
		try {
			   File file = new File(saveDirectory,saveFilename);
			   InputStream inStream = new FileInputStream(file);
			   
			   //한글 파일명 깨짐 방지
			   String client=request.getHeader("User-Agent");
			   if(client.indexOf("WOW64")==-1){
			      originFilename=new String(originFilename.getBytes("UTF-8"),"ISO-8859-1");
			   }else{
			      originFilename=new String(originFilename.getBytes("KSC5601"),"ISO-8859-1");
			   }
			   
			   //파일 다운로드 응답 헤더 설정
			   response.reset();
			   response.setContentType("application/octet-stream");
			   response.setHeader("Content-Disposition","attachment; filename=\""+originFilename+"\"");
			   
			   //response 내장 객체로 새로운 출력 스트림 생성
			   OutputStream outStream = response.getOutputStream();
			   
			   byte b[]=new byte[(int)file.length()];
			   int readBuffer=0;
			   while((readBuffer=inStream.read(b))>0){
			      outStream.write(b,0,readBuffer);
			   }
			   //입출력 스트림 닫기
			   inStream.close();
			   outStream.close();
		}catch(FileNotFoundException e){
				System.out.println("파일을 찾을 수 없습니다.");
			}catch(Exception e){
				System.out.println("예외가 발생했습니다.");
			}
		
	}
	//파일 삭제

	public static void deleteFile(HttpServletRequest req, String directory, String saveFileName) {
		String saveDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(saveDirectory+File.separator+saveFileName);
		if(file.exists()) {
			file.delete();
		}
		
	}
	
	
}
