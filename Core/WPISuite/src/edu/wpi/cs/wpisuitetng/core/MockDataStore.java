package edu.wpi.cs.wpisuitetng.core;

import java.util.ArrayList;

import com.google.gson.*;

import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class MockDataStore {

	private ArrayList<Project> projects;
	private ArrayList<User> users;
	
	private static MockDataStore myself = null;
	
	public static MockDataStore getMockDataStore()
	{
		if(myself == null)
			myself = new MockDataStore();
		return myself;
	}
	
	private MockDataStore()
	{
		users = new ArrayList<User>();
		users.add(new User("steve", "steve",0));
		users.add(new User("fred","fred",1));
		users.add(new User("jeff","jeff",2));
		users.add(new User("tyler","tyler",3));
		
		projects = new ArrayList<Project>();
		projects.add(new Project("WPISUITE",0));
		projects.add(new Project("ANDROID:BEARCLAW",1));
		projects.add(new Project("WINDOWS9",2));
		projects.add(new Project("OSX:HOUSECAT",3));
		projects.add(new Project("UBUNTU_RABID_RHINO",4));
	}
	
	public User addUser(String json)
	{
		Gson gson = new Gson();
		User u = gson.fromJson(json, User.class);
		users.add(u);
		return u;
	}
	
	public User[] getUser(String username)
	{
		User[] list = new User[1];
		if(username != null)
		{
			int index = 0;
			for(User u : users)
			{
				if(u.getUsername().equalsIgnoreCase(username))
					break;
				index++;
			}
			list[0] =  users.get(index);
			return list;
		}
		return users.toArray(list);
	}
	
	public Project addProject(String json)
	{
		Gson gson = new Gson();
		Project p = gson.fromJson(json, Project.class);
		projects.add(p);
		return p;
	}
	
	public Project[] getProject(int idNum)
	{
		Project[] list = new Project[1];
		int index = 0;
		if((Integer)idNum != null)
		{
		for(Project p : projects)
		{
			if(p.getIdNum() == idNum)
				break;
			index++;
		}
		list[0] =  projects.get(index);
		return list;
		}
		return projects.toArray(list);
	}
	
	public Model[] getModel(String[] path)
	{
		Model[] ret;

		if(path[0].equalsIgnoreCase("project"))
		{
			ret = getProject(Integer.parseInt(path[1]));
		}
		else if(path[0].equalsIgnoreCase("user"))
		{
			ret = getUser((String) path[1]);
		}
		else
		{
			ret = null;
		}
		return ret;
	}
}