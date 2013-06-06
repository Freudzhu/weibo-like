package com.weibolike.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class AccountDAOJdbcImpl implements AccountDAO{
	private DataSource dataSource;
	public AccountDAOJdbcImpl(DataSource dataSource){
		this.dataSource = dataSource;
	}
	@Override
	public boolean isUserExisted(Account account) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		boolean existed = false;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("select count(1) from t_account where name=?");
			stmt.setString(1, account.getName());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				existed = (rs.getInt(1) == 1);
			}
		}
		catch(SQLException e){
			ex = e;
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return existed;
	}

	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("insert into t_account(name,password,email) values(?,?,?)");
			stmt.setString(1, account.getName());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getEmail());
			stmt.executeUpdate();
		}
		catch(SQLException e){
			ex = e;
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Account getAccount(Account account) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		Account accountNew = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("select password,email from t_account  where name = ?");
			stmt.setString(1, account.getName());
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				accountNew = new Account();
				accountNew.setEmail(rs.getString(2));
				accountNew.setPassword(rs.getString(1));
			}
		}
		catch(SQLException e){
			ex = e;
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return accountNew;
	}

}
