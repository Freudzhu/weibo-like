package com.weibolike.model;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
public class UserService {
	private LinkedList<Blah> newest = new LinkedList<Blah>();
	private AccountDAO accountDAO;
	private BlahDAO blahDAO;
	
	public UserService(String users,AccountDAO accountDAO,BlahDAO blahDAO){
		this.accountDAO = accountDAO;
		this.blahDAO = blahDAO;
	}
	public boolean isUserExisted(Account account){
		return accountDAO.isUserExisted(account);
	}
	public void addAccount(Account account){
		accountDAO.addAccount(account);
	}
	public boolean checkLogin(Account account){
		if(account.getName()!=null&&account.getPassword()!=null){
			Account tmp = accountDAO.getAccount(account);
			return tmp!=null && tmp.getPassword().equals(account.getPassword());
		}
		return false;
	}
	public List<Blah> getBlahs(Blah blah){
		List<Blah> blahs = blahDAO.getBlahs(blah);
		Collections.sort(blahs,comparator);
		return blahs;
		
	}
	DateComparetor comparator = new DateComparetor();
	private class DateComparetor implements Comparator<Blah>{

		@Override
		public int compare(Blah b1, Blah b2) {
			// TODO Auto-generated method stub
			return -b1.getDate().compareTo(b2.getDate());
		}

	}
	//发布用户的消息
	public void addBlah(Blah blah){
		blahDAO.addBlah(blah);
		newest.addFirst(blah);
		if(newest.size() > 20){
			newest.removeLast();
		}
	}
	
	public void deleteBlah(Blah blah){
		blahDAO.deleteBlah(blah);
		newest.remove(blah);
	}
	public List<Blah> getNewest(){
		return newest;
	}
}
