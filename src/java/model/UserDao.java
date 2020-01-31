package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import method.IUser;

public class UserDao implements IUser{
	private DB db;
	private int ok;
	private ResultSet rs;
	private boolean bol;
	public UserDao() {
		db=new DB();
	}
	
        @Override
	public User getLogin(String login, String password) {
		User u=null;
		String sql="SELECT * FROM user WHERE login=? AND password=?";
		try {
		db.initPrepare(sql);
		db.getPstm().setString(1, login);
		db.getPstm().setString(2, password);
		ResultSet rs=db.executeSELECT();
		while(rs.next()) {
			u=new User();
			u.setId(rs.getInt(1));
			u.setName(rs.getString(2));
			u.setLogin(rs.getString(2));
			u.setPassword(rs.getString(2));
		}
		rs.close();
		db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public List<User> liste() {
		List<User> l_user=new ArrayList<User>();
		String sql="SELECT * FROM user";
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			
			while(rs.next()) {
				User u=new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setLogin(rs.getString(3));
				u.setPassword(rs.getString(4));
				l_user.add(u);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l_user;
	}
	@Override
	public int add(User u) {
		String sql="INSERT INTO user VALUES(null,?,?,?)";
		
		try {
			db.initPrepare(sql);
			db.getPstm().setString(1, u.getName());
			db.getPstm().setString(2, u.getLogin());
			db.getPstm().setString(3, u.getPassword());
			ok=db.executeMAJ();
			rs=db.getPstm().getGeneratedKeys();
			while(rs.next()){
				ok=rs.getInt(1);
			}
			rs.close();
			db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ok;
	}
	@Override
	public boolean delete(int id) {
		String sql="DELETE FROM user WHERE id=?";
		try {
			db.initPrepare(sql);
			db.getPstm().setInt(1, id);
			ok=db.executeMAJ();
			if(ok!=0) {
				bol=true;
			}
			
		} catch (Exception e) {
			bol=false;
			e.printStackTrace();
		}
		
		return bol;
	}
	@Override
	public boolean update(User u) {
		String sql="UPDATE user SET name=?,login=?,password=? WHERE id=?";
		bol=false;
		try {
			db.initPrepare(sql);
			db.getPstm().setString(1, u.getName());
			db.getPstm().setString(2, u.getLogin());
			db.getPstm().setString(3, u.getPassword());
			db.getPstm().setInt(4, u.getId());
			ok=db.executeMAJ();
			if(ok!=0) {
				bol=true;
			}
			
		} catch (Exception e) {
			bol=false;
			e.printStackTrace();
		}
		
		return bol;
	}
/*
        @Override
	public User getUserById(int id) {
		User u=null;
		String sql="SELECT * FROM user WHERE id=?";
		try {
			db.initPrepare(sql);
			db.executeSELECT();
			while(rs.next()) {
				u=new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setLogin(rs.getString(3));
				u.setPassword(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
*/	

}
