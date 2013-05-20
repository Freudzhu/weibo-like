package com.weibolike.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
public class UserService {
	private String USERS;
	public UserService(String users){
		USERS = users;
	}
	//增加用户目录和资料
	public void creatUser(String username, String email, String password) {
		// TODO Auto-generated method stub
		File userhome = new File(USERS + "/" + username);
        userhome.mkdir();
        try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
			writer.write(email + "\t" + password);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	//验证登陆
	public boolean checkLogin(String username, String password) throws IOException {
		// TODO Auto-generated method stub
		if(username!=null && password!=null){
			for(String file : new File(USERS).list()){
				if(file!=null && file.equals(username)){
					try {
						BufferedReader reader = new BufferedReader(new FileReader(new File(USERS +"/" +username + "/profile")));
						String[] spit = reader.readLine().split("\t");
						return spit[1].equals(password);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		}
		return false;
	}
	//读取用户的信息
	public Map<Date,String> readMessage(String username) throws IOException{
		File users = new File(USERS + "/" + username);
		Map<Date,String> messages = new TreeMap<Date,String>(new DateComparetor());
		for(String fileName : users.list(new TextFilter())){
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(USERS + "/" + username + "/" + fileName),"UTF-8"));
			StringBuffer message = new StringBuffer();
			String tmp = null;
			while((tmp=reader.readLine())!=null){
				message.append(tmp);
			}
			Date date = new Date(Long.parseLong(fileName.substring(0,fileName.indexOf(".txt"))));
			messages.put(date, message.toString());
			reader.close();
		}
		return messages;
	}
	private class TextFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			return name.endsWith(".txt");
		}
		
	}
	private class DateComparetor implements Comparator<Date>{

		@Override
		public int compare(Date o1, Date o2) {
			// TODO Auto-generated method stub
			return -o1.compareTo(o2);
		}

	}
	//发布用户的消息
	public void addMessage(String username, String blabla) throws IOException {
		String file = USERS + "/" + username + "/" + new Date().getTime() + ".txt";
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		writer.write(blabla);
		writer.close();
	}
	//验证用户是否为有效用户
	public boolean isInValidUser(String username){
		if(username == null){
			return false;
		}
		File[] users = new File(USERS).listFiles();
		for(File user : users){
			if(user.getName().equals(username)){
				return true;
			}
		}
		return false;
		
	}
}
