//package com.pikia.component.web.util;
//
//import java.awt.AlphaComposite;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//public class ImageUtils {
//	/**
//	 *
//	 * @param iconPath
//	 * @param srcImgPath
//	 * @param outImgPath
//	 * @param location
//	 *            图片水印的位置: 1 水平居中 2 右下角 3 左下角 4 右上角 5 左上角
//	 *
//	 */
//	public static void markImageByIcon(String iconPath, String srcImgPath, String outImgPath,
//			String location) {
//		OutputStream os = null;
//		try {
//			Image srcImg = ImageIO.read(new File(srcImgPath));
//			int wideth = srcImg.getWidth(null);
//			int height = srcImg.getHeight(null);
//			BufferedImage buffImg = new BufferedImage(wideth, height, 1);
//			Graphics2D g = buffImg.createGraphics();
//			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//			g.drawImage(srcImg.getScaledInstance(wideth, height, 4), 0, 0, null);
//			ImageIcon imgIcon = new ImageIcon(iconPath);
//			Image img = imgIcon.getImage();
//			int wideth_biao = img.getWidth(null);
//			int height_biao = img.getHeight(null);
//			float alpha = 0.5F;
//			g.setComposite(AlphaComposite.getInstance(10, alpha));
//
//			if ("1".equals(location))
//				// 水平居中
//				g.drawImage(img, (wideth - wideth_biao) / 2, (height - height_biao) / 2,
//						wideth_biao, height_biao, null);
//			else if ("2".equals(location))
//				// 右下角
//				g.drawImage(img, wideth - wideth_biao, height - height_biao, null);
//			else if ("3".equals(location))
//				// 左下角
//				g.drawImage(img, 0, height - height_biao, null);
//			else if ("4".equals(location))
//				// 右上角
//				g.drawImage(img, wideth - wideth_biao, 0, null);
//			else if ("5".equals(location))
//				// 左上角
//				g.drawImage(img, 0, 0, null);
//			else {
//				// 右下角
//				g.drawImage(img, wideth - wideth_biao, height - height_biao, null);
//			}
//			g.setComposite(AlphaComposite.getInstance(3));
//			g.dispose();
//
//			os = new FileOutputStream(outImgPath);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
//			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(buffImg);
//			param.setQuality(1.0F, true);
//			encoder.encode(buffImg, param);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (null != os) os.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//	}
//
//	/**
//	 *
//	 * @param logoText
//	 * @param srcImgPath
//	 * @param outImgPath
//	 * @param fontSize
//	 * @param location
//	 *            文字水印位置: 1 水平居中 2 右下角 3 左下角 4 右上角 5 左上角
//	 */
//	public static void markByText(String logoText, String srcImgPath, String outImgPath,
//			Integer fontSize, String location) {
//		InputStream is = null;
//		OutputStream os = null;
//		try {
//			Image srcImg = ImageIO.read(new File(srcImgPath));
//			int wideth = srcImg.getWidth(null);
//			int height = srcImg.getHeight(null);
//			BufferedImage buffImg = new BufferedImage(wideth, height, 1);
//			Graphics2D g = buffImg.createGraphics();
//			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//			g.drawImage(srcImg.getScaledInstance(wideth, height, 4), 0, 0, null);
//
//			g.setColor(Color.red);
//			g.setFont(new Font("宋体", 1, fontSize.intValue()));
//			float alpha = 0.5F;
//			g.setComposite(AlphaComposite.getInstance(10, alpha));
//			int text_wideth = logoText.length() * fontSize.intValue();
//			int text_height = fontSize.intValue();
//
//			if ("1".equals(location))
//				// 水平居中
//				g.drawString(logoText, (wideth - text_wideth) / 2, (height - text_height) / 2);
//			else if ("2".equals(location))
//				// 右下角
//				g.drawString(logoText, wideth - text_wideth, height - text_height);
//			else if ("3".equals(location))
//				// 左下角
//				g.drawString(logoText, 0, height - text_height);
//			else if ("4".equals(location))
//				// 右上角
//				g.drawString(logoText, wideth - text_wideth, 0);
//			else if ("5".equals(location))
//				// 左上角
//				g.drawString(logoText, 0, 0);
//			else {
//				// 右下角
//				g.drawString(logoText, wideth - text_wideth, height - text_height);
//			}
//			g.drawString(logoText, 120, 0);
//			g.dispose();
//			os = new FileOutputStream(outImgPath);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
//			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(buffImg);
//			param.setQuality(1.0F, true);
//			encoder.encode(buffImg, param);
//			os.flush();
//			os.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (null != is) is.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			try {
//				if (null != os) os.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//	}
//
//	/**
//	 * 将图片重绘成正方形
//	 *
//	 * @param srcImgPath
//	 */
//	public static void drawImageToSquare(String srcImgPath) {
//		OutputStream os = null;
//		try {
//			int square = 0;
//			Image srcImg = ImageIO.read(new File(srcImgPath));
//			int wideth = srcImg.getWidth(null);
//			int height = srcImg.getHeight(null);
//			square = wideth > height ? wideth : height;
//			if (wideth == height) return;
//			BufferedImage buffImg = new BufferedImage(square, square, 1);
//
//			Graphics2D g = buffImg.createGraphics();
//			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//			g.setColor(Color.WHITE);
//			g.fillRect(0, 0, square, square);
//			if (wideth >= height)
//				g.drawImage(srcImg, 0, (square - height) / 2, wideth, height, null);
//			else {
//				g.drawImage(srcImg, (square - wideth) / 2, 0, wideth, height, null);
//			}
//			g.setComposite(AlphaComposite.getInstance(3));
//			g.dispose();
//			os = new FileOutputStream(srcImgPath);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
//			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(buffImg);
//			param.setQuality(1.0F, true);
//			encoder.encode(buffImg, param);
//			os.flush();
//			os.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (null != os) os.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//	}
//
//	public static void main(String[] args) {
//		String srcImgPath = "d:/test/test.jpg";
//		String iconPath = "d:/test/image/logo.png";
//		drawImageToSquare(srcImgPath);
//		markImageByIcon("d:/logo.png", "d:/adele-2.jpg", "d:/adele-2=m.jpg", null);
//		markImageByIcon("d:/logo.png", "d:/adele-2.jpg", "d:/adele-2=m1.jpg", "1");
//		markImageByIcon("d:/logo.png", "d:/adele-2.jpg", "d:/adele-2=m2.jpg", "2");
//		markImageByIcon("d:/logo.png", "d:/adele-2.jpg", "d:/adele-2=m3.jpg", "3");
//		markImageByIcon("d:/logo.png", "d:/adele-2.jpg", "d:/adele-2=m3.jpg", "3");
//		markByText("简单是最好的", "d:/adele-2.jpg", "d:/adele-2=t.jpg", 1500, null);
//	}
//}
