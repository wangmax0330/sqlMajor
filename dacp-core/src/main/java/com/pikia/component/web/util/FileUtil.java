package com.pikia.component.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * 文件操作工具类
 * 
 * @author methew
 * 
 */
public class FileUtil {
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	/*
	 * 创建目录
	 * 
	 * @param dir
	 */
	public static void mkdir(String dir) {
		try {
			String dirTemp = dir;
			File dirPath = new File(dirTemp);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		} catch (Exception e) {
			logger.error("failed to create directory!!!   " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 创建新文件
	 * 
	 * @param fileName
	 *            String 包含路径的文件名 如:/home/methew/123.txt
	 * @param content
	 *            String 文件内容
	 */

	public static void createNewFile(String fileName, String content) {
		try {
			String fileNameTemp = fileName;
			File filePath = new File(fileNameTemp);
			if (!filePath.exists()) {
				filePath.createNewFile();
			}
			FileWriter fw = new FileWriter(filePath);
			PrintWriter pw = new PrintWriter(fw);
			String strContent = content;
			pw.println(strContent);
			pw.flush();
			pw.close();
			fw.close();
		} catch (Exception e) {
			logger.error("failed to create new file!!!   " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            String 包含路径的文件名
	 */
	public static void delFile(String fileName) {

		try {
			String filePath = fileName;
			java.io.File delFile = new java.io.File(filePath);
			delFile.delete();
		} catch (Exception e) {
			logger.error("failed to delete file!!!  " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            文件夹路径
	 */
	public static void delFolder(String folderPath) {
		try {
			// 删除文件夹里面所有内容
			delAllFile(folderPath);
			String filePath = folderPath;
			java.io.File myFilePath = new java.io.File(filePath);
			// 删除空文件夹
			myFilePath.delete();
		} catch (Exception e) {
			logger.error("failed to delete directory!!!   " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            文件夹路径
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] childFiles = file.list();
		File temp = null;
		for (int i = 0; i < childFiles.length; i++) {
			// File.separator与系统有关的默认名称分隔符
			// 在UNIX系统上，此字段的值为'/'；在Microsoft Windows系统上，它为 '\'。
			if (path.endsWith(File.separator)) {
				temp = new File(path + childFiles[i]);
			} else {
				temp = new File(path + File.separator + childFiles[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + childFiles[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + childFiles[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param srcFile
	 *            包含路径的源文件 如：/home/methew/123.txt
	 * @param dirDest
	 *            目标文件目录；若文件目录不存在则自动创建 如：/home/methew/123.txt
	 * @throws IOException
	 */

	public static void copyFile(String srcFile, String dirDest) {
		try {
			FileInputStream in = new FileInputStream(srcFile);
			mkdir(dirDest);
			FileOutputStream out = new FileOutputStream(dirDest + "/" + new File(srcFile).getName());
			int len;
			byte buffer[] = new byte[1024];
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
			logger.error("failed to copy file!!!   " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @param oldPath
	 *            String 源文件夹路径 如：/home/methew/123.txt
	 * @param newPath
	 *            String 目标文件夹路径 如：/home/methew/123.txt
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			// 如果文件夹不存在 则新建文件夹
			mkdir(newPath);
			File file = new File(oldPath);
			String[] files = file.list();
			File temp = null;
			for (int i = 0; i < files.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + files[i]);
				} else {
					temp = new File(oldPath + File.separator + files[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/"
							+ (temp.getName()).toString());
					byte[] buffer = new byte[1024 * 2];
					int len;
					while ((len = input.read(buffer)) != -1) {
						output.write(buffer, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
				}
			}
		} catch (Exception e) {
			logger.error("failed to copy directory!!!   " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 解压zip文件
	 * 
	 * @param srcDir
	 *            解压前存放的目录
	 * @param destDir
	 *            解压后存放的目录
	 * @throws Exception
	 */
	public static void extractZip(String srcDir, String destDir) throws Exception {
		int leng = 0;
		byte[] b = new byte[1024 * 2];
		/** 获取zip格式的文件 **/
		File[] zipFiles = new File(srcDir).listFiles(new FileFilterByExtension("zip"));
		if (zipFiles != null && !"".equals(zipFiles)) {
			for (int i = 0; i < zipFiles.length; i++) {
				File file = zipFiles[i];
				/** 解压的输入流 * */
				ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
				ZipEntry entry = null;
				while ((entry = zis.getNextEntry()) != null) {
					File destFile = null;
					if (destDir.endsWith(File.separator)) {
						destFile = new File(destDir + entry.getName());
					} else {
						destFile = new File(destDir + "/" + entry.getName());
					}
					/** 把解压包中的文件拷贝到目标目录 * */
					FileOutputStream fos = new FileOutputStream(destFile);
					while ((leng = zis.read(b)) != -1) {
						fos.write(b, 0, leng);
					}
					fos.close();
				}
				zis.close();
			}
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @param srcDir
	 *            压缩前存放的目录
	 * @param destDir
	 *            压缩后存放的目录
	 * @throws Exception
	 */
	public static void compressZip(String srcDir, String destDir) throws Exception {
		String tempFileName = null;
		byte[] buf = new byte[1024 * 2];
		int len;
		// 获取要压缩的文件
		File[] files = new File(srcDir).listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					if (destDir.endsWith(File.separator)) {
						tempFileName = destDir + file.getName() + ".zip";
					} else {
						tempFileName = destDir + "/" + file.getName() + ".zip";
					}
					FileOutputStream fos = new FileOutputStream(tempFileName);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					ZipOutputStream zos = new ZipOutputStream(bos);// 压缩包
					ZipEntry ze = new ZipEntry(file.getName());// 压缩包文件名
					zos.putNextEntry(ze);// 写入新的ZIP文件条目并将流定位到条目数据的开始处

					while ((len = bis.read(buf)) != -1) {
						zos.write(buf, 0, len);
						zos.flush();
					}
					bis.close();
					zos.close();
				}
			}
		}

	}

	private static class FileFilterByExtension implements FileFilter {
		private String extension;

		public FileFilterByExtension(String extension) {
			this.extension = extension;
		}

		public boolean accept(File pathName) {
			String temp = pathName.getName().toLowerCase();
			if (temp.endsWith(extension))
				return true;
			else
				return false;
		}
	}
}
