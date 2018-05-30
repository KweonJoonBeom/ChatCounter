package edu.handong.csee.java.hw3;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import edu.handong.csee.java.hw3.DataReaderCSV;
import edu.handong.csee.java.hw3.DataReaderTXT;
/**
 * public DataReader is read the messages from files
 * using the File instance, first use method of getDirectory 
 * second use method of getListoffiles then including to the files variable
 * @author °ÇÁØ¹ü
 *
 */
public class DataReader {
	static HashMap<String, ArrayList<Message>> messages = new HashMap<String, ArrayList<Message>>();
	public HashMap<String, ArrayList<Message>> getData(String strDir){
		File myDir = getDirectory(strDir);
		File[] files = getListOfFiles(myDir);
		readFiles(files);   
		return messages;
	}
	/**
	 * public getDirectory method return File instance 
	 * if be inputted String variable fDir 
	 * @param fDir
	 * @return
	 */
	public File getDirectory(String fDir) {//get Directory and return File
		File myDirectory = new File(fDir);
		return myDirectory;
	}
	/**
	 * public getListoffiles return File's list 
	 * 
	 * @param dataDir
	 * @return
	 */
	public File[] getListOfFiles(File dataDir) {//get list of files
		return dataDir.listFiles();
	}
	/**
	 * readFiles method return the nothing 
	 * but read all of file message and user name and date 
	 * if file is CSV file then use DataReaderCSV else use getMessagesFromTXTfiles
	 * @param files
	 */
	public void readFiles(File[] files){//get String arraylist of the file   
		File file = null;
		System.out.println(files.length);
		for (int i = 0; i < files.length; i++){
			file = files[i];
			System.out.println("Read a file : " + file.getName());
			if(file.getName().endsWith(".csv"))
				DataReaderCSV.getMessagesFromCSVFiles(file);
			else
				DataReaderTXT.getMessagesFromTXTFiles(file);
			//System.out.println(message);
		}   
	}
}   

