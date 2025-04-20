package com.matschie.general.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileHandlers {
	
	public static void createNewTxtFile(String fileName, String content) {
		try {
			Files.writeString(Paths.get(
			          "./test-evidences/"+fileName+".txt")
			         ,content
			         ,StandardOpenOption.CREATE
			         ,StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	public static void appendTxtFile(String fileName, String content) {
		try {
			Files.writeString(Paths.get(
					"./test-evidences/"+fileName+".txt")
			         ,content
			         ,StandardOpenOption.APPEND);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}

}