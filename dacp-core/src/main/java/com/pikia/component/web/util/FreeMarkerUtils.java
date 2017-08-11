package com.pikia.component.web.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FreeMarkerUtils {
	private final Log logger = LogFactory.getLog(getClass());

	private Configuration freemarker_cfg = null;
	private String ftlFolder;
	private String htmlFolder;

	public FreeMarkerUtils(String ftlFolder, String htmlFolder) {
		this.ftlFolder = ftlFolder;
		this.htmlFolder = htmlFolder;
	}

	public FreeMarkerUtils() {
	}

	protected Configuration getFreeMarkerCFG() {
		if (null == this.freemarker_cfg) {
			this.freemarker_cfg = new Configuration();
			this.freemarker_cfg.setDefaultEncoding("UTF-8");
			try {
				this.freemarker_cfg.setDirectoryForTemplateLoading(new File(
						this.ftlFolder));
			} catch (IOException e) {
				this.logger.error(e.getMessage());
			}
		}

		return this.freemarker_cfg;
	}

	public boolean geneHtmlFile(String templateFileName,
			Map<String, Object> propMap, String htmlFileName) {
		try {
			Template t = getFreeMarkerCFG().getTemplate(templateFileName);
			t.setEncoding("UTF-8");
			File afile = new File(this.htmlFolder
					+ System.getProperty("file.separator") + htmlFileName);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(afile), "UTF-8"));

			t.process(propMap, out);
			out.flush();
		} catch (TemplateException e) {
			this.logger.error("Error while processing FreeMarker template "
					+ templateFileName, e);
			return false;
		} catch (IOException e) {
			this.logger.error("Error while generate Static Html File "
					+ htmlFileName, e);
			return false;
		}

		return true;
	}

	public boolean geneHtmlFile(String templateFile,
			Map<String, Object> propMap, String htmlFile, boolean usefullpath) {
		int index = templateFile.lastIndexOf("/");
		String templateFileName = templateFile.substring(index + 1);
		String ftlFolder = templateFile.substring(0, index + 1);
		String htmlFolder = htmlFile
				.substring(0, htmlFile.lastIndexOf("/") + 1);
		File htmlFileFold = new File(htmlFolder);
		if (!htmlFileFold.exists())
			htmlFileFold.mkdir();
		try {
			this.freemarker_cfg = new Configuration();
			this.freemarker_cfg.setDefaultEncoding("UTF-8");
			this.freemarker_cfg.setDirectoryForTemplateLoading(new File(
					ftlFolder));
			Template t = this.freemarker_cfg.getTemplate(templateFileName);
			t.setEncoding("UTF-8");
			File afile = new File(htmlFile);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(afile), "UTF-8"));

			t.process(propMap, out);
			out.flush();
		} catch (TemplateException e) {
			this.logger.error("Error while processing FreeMarker template "
					+ templateFileName, e);
			return false;
		} catch (IOException e) {
			this.logger.error("Error while generate Static Html File: "
					+ htmlFile, e);
			return false;
		}
		return true;
	}
}
