package com.nony.studentgradingsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.nony.studentgradingsystem.utils.AmazonS3Util;
import org.junit.jupiter.api.Test;

public class AmazonS3UtilTests {

	@Test
	public void testListFolder() {
		String folderName = "test-upload";
		List<String> listKeys = AmazonS3Util.listFolder(folderName);
		listKeys.forEach(System.out::println);
 	}

	 @Test
	public void testUploadFile() throws FileNotFoundException {
		String folderName = "test-upload";
		String fileName = "IMG_2255.JPG";
		String filePath = "/Users/nony/Downloads/ShopDownBS/img/" + fileName;

		 InputStream inputStream = new FileInputStream(filePath);
		AmazonS3Util.uploadFile(folderName, fileName, inputStream);

	}

	@Test
	public void testDeleteFile() {
		String fileName = "test-upload/IMG_2255.JPG";
		AmazonS3Util.deleteFile(fileName);
	}

	@Test
	public void testRemoveFolder() {
		String folderName = "test-upload";
		AmazonS3Util.removeFolder(folderName);
	}
}
