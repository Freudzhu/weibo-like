package com.weibolike.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BlahDAOjdbcImpl implements BlahDAO{
	private DataSource dataSource;
	public BlahDAOjdbcImpl(DataSource dataSource){
		this.dataSource = dataSource;
	}
	@Override
	public List<Blah> getBlahs(Blah blah) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		List<Blah> blahs = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("select date,txt  from t_blah where name = ?");
			stmt.setString(1, blah.getUsername());
			ResultSet rs = stmt.executeQuery();
			blahs = new ArrayList<Blah>();
			while(rs.next()){
				blahs.add(new Blah(blah.getUsername(),rs.getTimestamp(1),rs.getString(2)));
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
		return blahs;
	}

	@Override
	public void addBlah(Blah blah) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		boolean existed = false;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("insert into t_blah(name,date,txt) values(?,?,?)");
			stmt.setString(1, blah.getUsername());
			stmt.setTimestamp(2,new Timestamp(blah.getDate().getTime()));
			stmt.setString(3, blah.getTxt());
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
	public void deleteBlah(Blah blah) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		SQLException ex = null;
		try{
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("delete from t_blah where date = ?");
			stmt.setTimestamp(1,new Timestamp(blah.getDate().getTime()));
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

}
