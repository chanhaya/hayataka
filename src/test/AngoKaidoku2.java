package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AngoKaidoku2 {

	public static void main(String[] args) {
		
		File file = new File("/Applications/Eclipse_2023-12.app/Contents/workspace/test/src/test/20240131_ango.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file), "UTF-8")) {
			String str;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}

	}

}
