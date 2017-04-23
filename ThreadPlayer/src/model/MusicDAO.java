package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import control.DAOException;

public class MusicDAO {

	private String fileName = "Musics.txt";
	
	public void addMusic(Music music){
		
		FileOutputStream fos = null;
		PrintStream filePrintStream = null;
		
		try{
			File file = new File(fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			fos = new FileOutputStream(file,true);
			filePrintStream = new PrintStream(fos);
		
			String csv = music.toCsv();
			filePrintStream.println(csv);
			
		}catch(FileNotFoundException e){
			new DAOException("Error looking file",e);
		}catch(IOException e1){
			new DAOException("Error opening file",e1);
		}finally{
			if(filePrintStream!=null){
				filePrintStream.close();
			}
			if(fos!=null){
				try{
					fos.close();
				}catch(IOException e){}
			}
		}
		
	}
	
	public ArrayList<Music> listarMusic(){
		
		ArrayList<Music> musics = new ArrayList<Music>();
		
		Scanner fileScanner = null;
		
		try{
			File file = new File(fileName);
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			fileScanner = new Scanner(file);
			
			while(fileScanner.hasNextLine()){
			
				String line = fileScanner.nextLine();
				
				String parts[] = line.split(",");
				
				Music music = new Music(parts[0],parts[1]);

				musics.add(music);
				
				
			}
			
		}catch(FileNotFoundException e){
			new DAOException("Error looking file",e);
		}catch(IOException e1){
			new DAOException("Error opening file",e1);
		}finally{
			if(fileScanner != null)
				fileScanner.close();
		}
		
		return musics;
		
	}
	
}
