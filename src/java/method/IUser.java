package method;

import java.util.List;

import entities.User;

public interface IUser {
	public User getLogin(String login,String password);
	public List<User> liste();
	public int add(User u);
	public boolean update(User u);
	public boolean delete(int id);
	/*public User getUserById(int id);*/
}
