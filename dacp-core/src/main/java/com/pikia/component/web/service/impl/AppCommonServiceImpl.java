//package com.pikia.component.web.service.impl;
//
//import java.awt.Image;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Date;
//
//import javax.imageio.ImageIO;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import com.pikia.component.web.service.AppCommonService;
//import com.pikia.component.web.util.CompressPic;
//import com.pikia.component.web.util.DateUtils;
//import com.pikia.component.web.util.ImageUtils;
//import com.pikia.system.property.SystemProperties;
//
///**
// * 通用的Service
// *
// * @author Methew
// *
// */
//@Service
//public class AppCommonServiceImpl implements AppCommonService {
//	protected final Logger logger = Logger.getLogger(AppCommonServiceImpl.class);
//
//	/**
//	 * 文件上传通用方法 thum 是否需要缩略图 XXL500*500 XL// 300*300 L 200*200// M150*150
//	 * S80*80// SS60*60
//	 */
//	@Override
//	public String uploadFile(CommonsMultipartFile file, String pathFolder, boolean waterMark,
//			String thum, boolean whitemark) {
//		return uploadFile(file.getBytes(), file.getOriginalFilename(), pathFolder, waterMark, thum,
//				whitemark);
//
//	}
//
//	@Override
//	public String uploadFile(File file, String pathFolder, boolean waterMark, String thum,
//			boolean whitemark) {
//		return uploadFile(getBytes(file), file.getName(), pathFolder, waterMark, thum, whitemark);
//
//	}
//
//	private byte[] getBytes(File file) {
//		byte[] buffer = null;
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//			byte[] b = new byte[1000];
//			int n;
//			while ((n = fis.read(b)) != -1) {
//				bos.write(b, 0, n);
//			}
//			fis.close();
//			bos.close();
//			buffer = bos.toByteArray();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return buffer;
//	}
//
//	@Override
//	public String uploadFile(byte[] bytes, String fileTrueName, String pathFolder,
//			boolean waterMark, String thum, boolean whitemark) {
//		try {
//			Date date = new Date();
//			String folder = (StringUtils.isBlank(pathFolder) ? "undefine" : pathFolder) + "/"
//					+ DateUtils.date2Str(date, "yyyy") + "/" + DateUtils.date2Str(date, "MM") + "/"
//					+ DateUtils.date2Str(date, "dd") + "/" + DateUtils.date2Str(date, "HH") + "/";
//
//			String uploadPath = SystemProperties.getProperties("com.pikia.component.web.util.ImageUtils.uploadPicPath");
//			String savePath = uploadPath + folder;
//			String markImagePlace = SystemProperties
//					.getProperties("com.pikia.component.web.util.ImageUtils.markImagePlace");
//			File f1 = new File(savePath);
//			if (!f1.exists()) {
//				f1.mkdirs();
//			}
//			String name = "";
//			String extName = "";
//			String images = ".jpg,.bmp,.png,.gif";
//			if (bytes.length != 0) {
//				extName = fileTrueName.substring(fileTrueName.lastIndexOf(".")).toLowerCase();
//				name = System.currentTimeMillis() + "";
//				File saveFile = new File(savePath + name + extName);
//				FileCopyUtils.copy(bytes, saveFile);
//				// String outORGPath = uploadPath + "ORG" + folder;
//				// File fORG = new File(outORGPath);
//				// if (!fORG.exists()) {
//				// fORG.mkdirs();
//				// }
//				// File saveORGFile = new File(outORGPath + "/" + name +
//				// extName);
//				// FileCopyUtils.copy(bytes, saveORGFile);
//
//				CompressPic compress = new CompressPic();
//				int width = 0;
//				int height = 0;
//				if (images.indexOf(extName.toLowerCase()) > -1) {
////					Image img = ImageIO.read(saveFile);
////					width = img.getWidth(null);
////					height = img.getHeight(null);
//					// if (width > 800 && height > 800) {// 800
//					// compress.compressPic(savePath, savePath, name + extName,
//					// name + extName, 800, 800, true);
//					// }
//
//					if (!extName.toLowerCase().endsWith(".gif") && waterMark) {// 非gif图片方可加水印和生成缩略图
//						if (width >= 800)
//							ImageUtils.markImageByIcon(uploadPath + "big.png", savePath + name
//									+ extName, savePath + name + extName, markImagePlace);
//						if (width < 800 && width > 400)
//							ImageUtils.markImageByIcon(uploadPath + "middle.png", savePath + name
//									+ extName, savePath + name + extName, markImagePlace);
//						if (width <= 400)
//							ImageUtils.markImageByIcon(uploadPath + "small.png", savePath + name
//									+ extName, savePath + name + extName, markImagePlace);
//					}
//					// if (whitemark)
//					// com.pikia.component.web.util.ImageUtils.drawImageToSquare(savePath
//					// + name + extName);// 白底修正图片为正方形
//					if (StringUtils.isNotBlank(thum)) {
//						String[] thums = thum.split(",");
//						for (String th : thums) {
//							String outPath = uploadPath + th + folder;
//							File f12 = new File(outPath);
//							if (!f12.exists()) {
//								f12.mkdirs();
//							}
//							int outputWidth = outputWidth(th);
//							int outputHeight = outputHeight(th);
//							if ((width > (outputWidth - 2) && width < (outputWidth + 2))
//									&& (height > (outputHeight - 2) && height < (outputHeight + 2))) {
//								File outFile = new File(outPath + name + extName);
//								FileCopyUtils.copy(bytes, outFile);
//							} else {
//								//里面自带有对gif 的处理,不需要担心
//								compress.compressPic(savePath, outPath, name + extName, name
//										+ extName, outputWidth, outputHeight, false);
//							}
//						}
//					}
//				}
//
//			}
//			// if(fullName!=null && "1".equals(fullName)){
//			// Map<String, String> map = new HashMap<String, String>();
//			// map.put("name", folder+name + extName);
//			// map.put("oname",
//			// fileTrueName.substring(0,fileTrueName.lastIndexOf(".")));
//			// map.put("extName", extName);
//			// response.getWriter().print(JsonUtils.JSON_Bean2String(map));
//			// }else{
//			// response.getWriter().print(folder+name + extName);
//			// }
//			//
//			return folder + name + extName;
//		} catch (IOException e) {
//			logger.error(e, e);
//		}
//		return null;
//	}
//
//	public int outputWidth(String key) {
//		if (key.equals("XL")) return 640;
//		if (key.equals("AXL")) return 640;
//		if (key.equals("L")) return 130;
//		if (key.equals("M")) return 120;
//		if (key.equals("S")) return 73;
//		if (key.equals("GXL")) return 600;
//		if (key.equals("GL")) return 262;
//		return 0;
//	}
//
//	public int outputHeight(String key) {
//		if (key.equals("XL")) return 214;
//		if (key.equals("AXL")) return 214;
//		if (key.equals("L")) return 130;
//		if (key.equals("M")) return 120;
//		if (key.equals("S")) return 73;
//		if (key.equals("GXL")) return 320;
//		if (key.equals("GL")) return 140;
//		return 0;
//	}
//
//}
