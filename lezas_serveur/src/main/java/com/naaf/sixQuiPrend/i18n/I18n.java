package com.naaf.sixQuiPrend.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

	private static ResourceBundle resourceBundle = null;
	private static final String DEFAUL_NAME = "messages";
	private static final String DIRECTORY_BUNDLE = "i18n/";

	private I18n() {
	}

	public static ResourceBundle getI18n(String fileName, Locale... locale) {
		if (null == resourceBundle) {
			if (0 < locale.length) {
				resourceBundle = ResourceBundle.getBundle(DIRECTORY_BUNDLE + fileName, locale[0]);
			} else {
				resourceBundle = ResourceBundle.getBundle(DIRECTORY_BUNDLE + fileName);
			}
		}
		return resourceBundle;
	}

	public static ResourceBundle getI18n() {
		return getI18n(DEFAUL_NAME);
	}
}
