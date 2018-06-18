package edu.handong.csee.java.hw3;
/**
 * Message is public class 
 * and has consturtor and instance variable date,user,strMessage
 * @author °ÇÁØ¹ü
 *
 */
public class Message {
	String date, user, strMessage;
	public Message(String date, String user, String strMessage) {
		this.date = date;

		this.user = user;
		this.strMessage = strMessage;   
	}
	/**
	 * getID method return the message's user (String type)
	 * @return
	 */
	public String getID(){
		return user;
	}
}