//package com.pikia.component.web.util;
//
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.imageio.ImageIO;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
///**
// * Java 压缩图片工具类
// *
// * @author Methew
// *
// */
//@SuppressWarnings("restriction")
//public class CompressPic {
//	private File file = null;
//	private String inputDir;
//	private String outputDir;
//	private String inputFileName;
//	private String outputFileName;
//	private int outputWidth = 300;
//	private int outputHeight = 300;
//	private boolean proportion = true;
//	protected final Log logger = LogFactory.getLog(CompressPic.class);
//
//	public void setInputDir(String inputDir) {
//		this.inputDir = inputDir;
//	}
//
//	public void setOutputDir(String outputDir) {
//		this.outputDir = outputDir;
//	}
//
//	public void setInputFileName(String inputFileName) {
//		this.inputFileName = inputFileName;
//	}
//
//	public void setOutputFileName(String outputFileName) {
//		this.outputFileName = outputFileName;
//	}
//
//	public void setOutputWidth(int outputWidth) {
//		this.outputWidth = outputWidth;
//	}
//
//	public void setOutputHeight(int outputHeight) {
//		this.outputHeight = outputHeight;
//	}
//
//	public void setWidthAndHeight(int width, int height) {
//		this.outputWidth = width;
//		this.outputHeight = height;
//	}
//
//	public long getPicSize(String path) {
//		this.file = new File(path);
//		return this.file.length();
//	}
//
//	public Map<String, String> compressPic(String inputDir, String outputDir, String inputFileName,
//			String outputFileName, int outputWidth, int outputHeight, boolean proportion) {
//		Map map = new HashMap();
//		try {
//			File file = new File(inputDir + inputFileName);
//			if (!file.exists()) {
//				this.logger.error("file [" + inputFileName + "] do not exist to compress!");
//				return null;
//			}
//
//			if (outputFileName.toLowerCase().endsWith(".gif")) {
//				map.put("outputWidth", null + "");
//				map.put("outputHeight", null + "");
//				map.put("name", inputFileName);
//				return map;
//			}
//			Image img = ImageIO.read(file);
//			if (img.getWidth(null) == -1) {
//				this.logger.error("file [" + inputFileName + "] is not allowed file to compress!");
//				return null;
//			}
//			int newHeight;
//			int newWidth;
//			if (proportion == true) {
//				double rate1 = img.getWidth(null) / outputWidth;
//				double rate2 = img.getHeight(null) / outputHeight;
//				double rate = rate1 > rate2 ? rate1 : rate2;
//				newWidth = (int) (img.getWidth(null) / rate);
//				newHeight = (int) (img.getHeight(null) / rate);
//			} else {
//				newWidth = outputWidth;
//				newHeight = outputHeight;
//			}
//			map.put("outputWidth", newWidth + "");
//			map.put("outputHeight", newHeight + "");
//			map.put("name", outputFileName);
//			BufferedImage tag = new BufferedImage(newWidth, newHeight, 1);
//			tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, 4), 0, 0, null);
//			FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
//
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			encoder.encode(tag);
//			out.close();
//		} catch (IOException ex) {
//			this.logger.error("file [" + inputFileName + "] compress failed!", ex);
//			return null;
//		}
//		return map;
//	}
//
//	public Map<String, String> compressNormalPic(String inputDir, String outputDir,
//			String inputFileName, String outputFileName, int outputWidth, int outputHeight) {
//		Map map = new HashMap();
//		try {
//			File file = new File(inputDir + inputFileName);
//			if (!file.exists()) {
//				this.logger.error("file [" + inputFileName + "] do not exist to compress!");
//				return null;
//			}
//			Image img = ImageIO.read(file);
//
//			if (img.getWidth(null) == -1) {
//				this.logger.error("file [" + inputFileName + "] is not allowed file to compress!");
//				return null;
//			}
//
//			if (outputFileName.toLowerCase().endsWith(".gif")) {
//				map.put("outputWidth", img.getWidth(null) + "");
//				map.put("outputHeight", img.getHeight(null) + "");
//				map.put("name", outputFileName);
//				file.renameTo(new File(outputDir + outputFileName));
//				return map;
//			}
//
//			double rate1 = img.getWidth(null) / outputWidth;
//			double rate2 = img.getHeight(null) / outputHeight;
//
//			double rate = rate1 > rate2 ? rate1 : rate2;
//			if ((rate1 < 1.0D) && (rate2 < 1.0D)) {
//				rate = 1.0D;
//			}
//			int newWidth = (int) (img.getWidth(null) / rate);
//			int newHeight = (int) (img.getHeight(null) / rate);
//
//			map.put("outputWidth", newWidth + "");
//			map.put("outputHeight", newHeight + "");
//			map.put("name", outputFileName);
//			BufferedImage tag = new BufferedImage(newWidth, newHeight, 1);
//			tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, 4), 0, 0, null);
//			FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
//
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			encoder.encode(tag);
//			out.close();
//		} catch (IOException ex) {
//			this.logger.error("file [" + inputFileName + "] compress failed!", ex);
//			return null;
//		}
//		return map;
//	}
//
//	public Map<String, String> compressPic(String inputDir, String outputDir, String inputFileName,
//			String outputFileName, int outputWidth, int outputHeight) {
//		return compressPic(inputDir, outputDir, inputFileName, outputFileName, outputWidth,
//				outputHeight, this.proportion);
//	}
//
//	public Map<String, String> compressPic(String inputDir, String outputDir, String inputFileName,
//			String outputFileName) {
//		return compressPic(inputDir, outputDir, inputFileName, outputFileName, this.outputWidth,
//				this.outputWidth, this.proportion);
//	}
//
//	public Map<String, String> compressPic() {
//		return compressPic(this.inputDir, this.outputDir, this.inputFileName, this.outputFileName,
//				this.outputWidth, this.outputHeight, this.proportion);
//	}
//
//	public static void main(String[] arg) {
//		CompressPic mypic = new CompressPic();
//		int count = 0;
//		for (int i = 0; i < 1; i++) {
//			int start = (int) System.currentTimeMillis();
//			System.out.println(mypic.compressPic("d:\\", "d:\\", "18507.jpg", "r1" + i + ".jpg",
//					547, 537, true));
//			int end = (int) System.currentTimeMillis();
//			int re = end - start;
//			count += re;
//			System.out.println("第" + (i + 1) + "张图片压缩处理使用了: " + re + "毫秒");
//			System.out.println("输出的图片大小："
//					+ mypic.getPicSize(new StringBuilder().append("d:\\r1").append(i)
//							.append(".jpg").toString()) / 1024L + "KB");
//		}
//		System.out.println("总共用了：" + count + "毫秒");
//	}
//}
