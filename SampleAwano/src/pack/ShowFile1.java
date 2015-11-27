package pack;

import java.io.*;

public class ShowFile1{
public static void main(String[] args){
		if(args.length != 1){
			System.out.println("�g�p�@");
			System.out.println("��");
			System.exit(0);
		}
		String filename = args[0];
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
			reader.close();
		}catch(FileNotFoundException e){
			System.out.println(filename + "��������܂���B");
		}catch(IOException e){
			System.out.println(e);
		}
	}
}