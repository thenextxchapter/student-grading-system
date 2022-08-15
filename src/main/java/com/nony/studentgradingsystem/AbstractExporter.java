package com.nony.studentgradingsystem;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AbstractExporter {

	public void setResponseHeader(
			HttpServletResponse response,
			String contentType,
			String extension,
			String entityName
	) throws IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd_HH-mm-ss");
		String timeStamp = dateFormatter.format(new Date());
		String fileName = entityName + timeStamp + extension;

		response.setContentType(contentType);

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
	}
}
