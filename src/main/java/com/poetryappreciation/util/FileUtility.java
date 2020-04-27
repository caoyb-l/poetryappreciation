package com.poetryappreciation.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Properties;


/**
 * 方法说明：文件操作
 * @author  AnHZ
 * @date  2019/7/9
 */
public final class FileUtility {

	private static final int BUFFER_SIZE = 4096;

	/**
	 * 创建文件
	 *
	 * @param inStream
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String createFile(final InputStream inStream,
                                    final String filePath, final String fileName) throws Exception {
		File f = new File(filePath);
		f.mkdirs();

		String makeFile = filePath.concat(System.getProperty("file.separator"))
				.concat(fileName);

		OutputStream outStream = new FileOutputStream(makeFile);

		int bytesRead = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		bytesRead = inStream.read(buffer, 0, BUFFER_SIZE);
		while (bytesRead != -1) {
			outStream.write(buffer, 0, bytesRead);
			bytesRead = inStream.read(buffer, 0, BUFFER_SIZE);
		}

		outStream.close();
		return fileName;
	}

	/**
	 * 取得属性文件内容
	 *
	 * @param propertyfilepath
	 * @return
	 * @throws Exception
	 */
	public static Properties getProperties(String propertyfilepath)
			throws Exception {
		Properties props = null;

		props = new Properties();
		FileInputStream in = null;
		in = new FileInputStream(propertyfilepath);
		props.load(in);
		in.close();

		return props;
	}

	/**
	 * 保存Properties文件
	 *
	 * @param props
	 * @param propertyfilepath
	 * @throws Exception
	 */
	public static void saveProperties(Properties props, String propertyfilepath)
			throws Exception {
		FileOutputStream out = null;
		out = new FileOutputStream(propertyfilepath);
		props.store(out, "");
		out.close();
	}

	/**
	 * 写文件
	 *
	 * @param filepath
	 * @param filename
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String writeFile(String filepath, String filename,
                                   String value) throws Exception {
		PrintWriter out = null;

		File f = new File(filepath);
		f.mkdirs();

		String file = filepath + System.getProperty("file.separator")
				+ filename;

		out = new PrintWriter(new FileWriter(file));
		out.write(value);
		out.close();

		return file;
	}

	/**
	 * 追加写文件
	 *
	 * @param filepath
	 * @param filename
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String appendFile(String filepath, String filename,
                                    String value) throws Exception {
		PrintWriter out = null;

		File f = new File(filepath);
		f.mkdirs();

		String file = filepath + System.getProperty("file.separator")
				+ filename;

		out = new PrintWriter(new FileWriter(file, true));
		out.write(value);
		out.close();

		return file;
	}

	/**
	 * 取得文件类型
	 *
	 * @param filepath
	 * @return
	 */
	public static String getFileType(String filepath) {
		int index = filepath.lastIndexOf(".");

		String filetype = filepath.substring(index + 1);
		return filetype;
	}

	/**
	 * 根据文件路径取得内容
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String getFileContentByPath(String path) throws Exception {
		String content = "";
		StringBuffer buffer = new StringBuffer();

		File sqlfile = new File(path);

		if (sqlfile.exists() && sqlfile.isFile()) {
			BufferedReader raf = new BufferedReader(new InputStreamReader(
					new FileInputStream(sqlfile)));

			String lineStr;
			while ((lineStr = raf.readLine()) != null) {
				buffer.append(lineStr);
			}
			content = buffer.toString();
		}
		return content;
	}

	/**
	 * 复制文件
	 *
	 * @param srcFilePath
	 * @param desFilePath
	 * @return
	 * @throws Exception
	 */
	public static int copyFile(String srcFilePath, String desFilePath)
			throws Exception {
		File srcf = new File(srcFilePath);
		File desf = new File(desFilePath);

		if (!srcf.exists()) {
			return -1;
		}
		if (desf.exists()) {
			return -2;
		}

		FileInputStream fis = new FileInputStream(srcf);
		FileOutputStream fos = new FileOutputStream(desf);

		byte[] buffer = new byte[1024];

		int flag = 0;
		while ((flag = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, flag);
		}
		fis.close();
		fos.close();
		return 1;
	}

	/**
	 * 复制文件夹
	 *
	 * @param srcPath
	 * @param desPath
	 * @return
	 * @throws Exception
	 */
	public static int copyFolder(String srcPath, String desPath)
			throws Exception {
		File src = new File(srcPath);
		File des = new File(desPath);

		String desFilePath = "";

		if (!src.exists()) {
			return -1;
		}

		if (!src.isDirectory()) {
			return -2;
		}

		if (!des.exists()) {
			des.mkdirs();
		}

		File file[] = src.listFiles();

		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				desFilePath = desPath + System.getProperty("file.separator")
						+ file[i].getName();

				copyFile(file[i].getPath(), desFilePath);

			} else {
				if (file[i].isDirectory()) {
					String tempDesPath = desPath
							+ System.getProperty("file.separator")
							+ file[i].getName();
					copyFolder(file[i].getPath(), tempDesPath);
				} else {

					return 0;
				}
			}

		}

		return 1;
	}

	/**
	 * 创建文件夹
	 *
	 * @param folderPath
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}
	}

	/**
	 * 创建文件
	 *
	 * @param filePathAndName
	 * @param fileContent
	 */
	public static void newFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件
	 *
	 * @param filePathAndName
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			File myDelFile = new File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*
	* 文件上传
	* */
	public static void fileUpload(String path,String fileName, MultipartFile reqFile){
		try{
			File uploadDir = new File(path);
			if(!uploadDir.isDirectory()){
				uploadDir.mkdirs();
			}
			Path uploadPath = Paths.get(path,fileName);
			System.out.println(uploadPath);
			reqFile.transferTo(uploadPath);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	* 文件下载
	* */
	public static void fileDownLoad(String path,String fileName,HttpServletResponse response){
		File file = new File(path+fileName);
		if (file.exists()) {
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}



	/**
	 * 删除文件夹（递归）
	 *
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 递归删除文件
	 *
	 * @param path
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
			}
		}
	}

	/**
	 * 移动文件
	 *
	 * @param oldPath
	 * @param newPath
	 * @throws Exception
	 */
	public static void moveFile(String oldPath, String newPath)
			throws Exception {
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/**
	 * 移动文件夹
	 *
	 * @param oldPath
	 * @param newPath
	 * @throws Exception
	 */
	public static void moveFolder(String oldPath, String newPath)
			throws Exception {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 下载文件
	 *
	 * @param out
	 * @param path
	 * @throws Exception
	 */
	public static void downloadFile(OutputStream out, String path)
			throws Exception {
		File file = new File(path);
		byte[] buf = new byte[1024];
		int len = 0;
		BufferedInputStream br = null;
		try {
			br = new BufferedInputStream(new FileInputStream(file));
			while ((len = br.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
				br = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}

	/**
	 * 获得一个文件的大小，字符串格式
	 *
	 * @param fileSize
	 * @return
	 */
	public static String getFileSize(long fileSize) {

		String[] unit = new String[] { "B", "K", "M", "G" };
		long exp = 1024;
		int level = 1;
		long max = exp;
		DecimalFormat format = new DecimalFormat("#.#");

		while (fileSize >= max) {

			level++;
			max = max * exp;
		}

		level--;
		max = max / exp;

		return format.format(((double) fileSize) / ((double) max))
				+ unit[level];
	}

}
