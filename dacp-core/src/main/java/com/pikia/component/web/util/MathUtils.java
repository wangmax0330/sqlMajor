package com.pikia.component.web.util;

import java.math.BigDecimal;

public class MathUtils {
	public MathUtils() {
	}

	public static double round(double d, int scale) {
		BigDecimal b = new BigDecimal(d);
		double f1 = b.setScale(scale, 4).doubleValue();
		return f1;
	}
}
