package com.zxh.core.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zxh.core.util.UploadUtils;


/**
 * @包名 com.jlht.cst.company.action
 * @文件名 ImageUploadAct.java
 * @作者 liye
 * @创建日期 2014年8月15日 下午4:47:31
 * @版本 V 1.0
 */

public class ImageUpload extends HttpServlet {

	/**
	 * 
	 */
	private Log log =LogFactory.getLog(ImageUpload.class);
	private static final long serialVersionUID = 1L;
	private static final String videoSuffix=".flv.FLV.mp4.MP4";
	
	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = this.getServletConfig().getServletContext().getRealPath("");
		
		savePath = savePath + "/upload";
		String url = "/upload";
		
		File f1 = new File(savePath);
		System.out.println(savePath);
		log.info(savePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			ex.printStackTrace();
			return;
		}

		Iterator<FileItem> it = fileList.iterator();
		String name = "";
		String extName = "";
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				name = item.getName();
				if (name == null || name.trim().equals("")) {
					continue;
				}

				// 扩展名格式：
				if (name.lastIndexOf(".") >= 0) {
					extName = FilenameUtils.getExtension(name);
				}
				//如果是视频文件
				if(videoSuffix.indexOf(extName)>-1){
					savePath = savePath +"/video";
					File vFile = new File(savePath);
					if(!vFile.exists()){
						vFile.mkdirs();
					}
				}
				File file = null;
				do {
					// 生成文件名：name = UUID.randomUUID().toString();
					String foldYM = UploadUtils.generateMonthname();
					foldYM = savePath + File.separator + foldYM;
					// upload\201501"
					File fileYMDir = new File(foldYM);
					// 日期文件夹，没有则生成
					if (!fileYMDir.exists()) {
						fileYMDir.mkdir();
					}
					// 生成新的文件名
					savePath = UploadUtils.generateMD5Filename(savePath, extName);
					file = new File(savePath);
					// 获取文件地址
					int n = savePath.indexOf("/upload");
					url = savePath.substring(n);
				} while (file.exists());
				File saveFile = new File(savePath);
				PrintWriter outP = null;
				try {
					BufferedInputStream in = new BufferedInputStream(item.getInputStream());     
	                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile)); 
	                Streams.copy(in, out, true);
	                request.setCharacterEncoding("utf-8");  
	                response.setContentType("text/html; charset=utf-8");
	                outP = response.getWriter();
	                outP.write(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally{
					outP.flush();
		            outP.close();
				}
			}
		}
		//response.getWriter().print(url);
	}

}
