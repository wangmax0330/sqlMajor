//package com.pikia.component.web.util;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.ezmorph.bean.MorphDynaBean;
//
//import org.apache.commons.beanutils.PropertyUtils;
//
//import com.pikia.system.domain.AppCityVo;
//import com.pikia.system.domain.AppDistrictVo;
//import com.pikia.system.domain.AppProvinceVo;
//
//public class CityUtils {
//	public static List<AppProvinceVo> provinces;
//	public static Map<String, AppProvinceVo> provinceMap;
//	public static Map<String, AppCityVo> cityMap;
//
//	public static void initCities(String fileName) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "utf-8"));
//		StringBuffer str = new StringBuffer();
//		String data = br.readLine();// 一次读入一行，直到读入null为文件结束
//		while (data != null) {
//			str.append(data);
//			data = br.readLine(); // 接着读下一行
//		}
//		cityMap = new HashMap();
//		provinceMap = new HashMap();
//		CityUtils.provinces = JsonUtils.JSON_String2List(str.toString(), AppProvinceVo.class);
//		if (provinces != null) {
//			for (AppProvinceVo province : provinces) {
//				System.out.println("-------------------" + province.getName());
//				provinceMap.put(province.getName(), province);
//				if (province.getSub() != null) {
//					for (MorphDynaBean cityMor : province.getSub()) {
//						AppCityVo city = new AppCityVo();
//						city.setName(cityMor.get("name").toString());
//						List<AppDistrictVo> districtList = null;
//						System.out.println("+++++++++++++++++" + cityMor.get("name").toString());
//						System.out.println("+++++++++++++++++" + cityMor);
//						System.out.println(cityMor.get("sub"));
//						System.out.println("+++++++++++++++++" + PropertyUtils.getProperty(cityMor, "sub").toString());
//						if (cityMor.get("sub") != null) {
//							List<MorphDynaBean> districts = (List) cityMor.get("sub");
//							if (districts != null) {
//								districts = new ArrayList();
//								for (MorphDynaBean districtMor : districts) {
//									AppDistrictVo district = new AppDistrictVo();
//									district.setName(districtMor.get("name").toString());
//									// districtMap.put(district.getName(),
//									// business);
//									districtList.add(district);
//								}
//							}
//						}
//						city.setDistrict(districtList);
//						cityMap.put(city.getName(), city);
//					}
//				}
//			}
//		}
//	}
//
//	// public static String getProvinceName() {
//	// AppCityVo city = getCityById(id);
//	// return city == null ? null : city.getName();
//	// }
//
//	public static AppCityVo getCitysByProvinceName(String name) {
//		if (name == null)
//			return null;
//		AppProvinceVo provinceVo = CityUtils.provinceMap.get(name);
//		AppCityVo city = CityUtils.cityMap.get(name);
//		return city;
//	}
//
//	public static List<AppDistrictVo> getDistrictsByCityName(String name) {
//		if (name == null)
//			return null;
//		AppCityVo appCityVo = CityUtils.cityMap.get(name);
//		return appCityVo.getDistrict();
//	}
//
//	public static void main(String[] args) throws Exception {
//		String filePath = CityUtils.class.getResource("/").getPath();
//		CityUtils.initCities(filePath + "config/province_city_district.json");
//		System.out.println(CityUtils.cityMap);
//		System.out.println("---------------------------------");
//		System.out.println(CityUtils.provinceMap);
//		// AppCityVo appCityVo = CityUtils.getCitysByProvinceName("北京");
//		// System.out.println(appCityVo);
//
//		// 遍历map中的值
//		// for (AppCityVo city : CityUtils.cityMap.values()) {
//		// System.out.println("------00000000000000--------");
//		// System.out.println(city.getName());
//		// // System.out.println(city.getSub());
//		// MorphDynaBean bean = new MorphDynaBean();
//		// List<MorphDynaBean> districts = city.getSub();
//		//
//		// // System.out.println(sb);
//		// for (MorphDynaBean district : districts) {
//		// System.out.println("++" + PropertyUtils.getProperty(district,
//		// "name"));
//		// // System.out.println(district.get("name"));
//		// }
//		// System.out.println("------00000000000000--------");
//		// }
//		// AppCityVo city=CityUtils.getCityById(id)
//	}
//}
