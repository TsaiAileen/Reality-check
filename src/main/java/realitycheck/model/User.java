package realitycheck.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private Integer id ;
	
	private String name;
	private String email;

	public User() {}
	
	public User (Integer id, String name, String email)
	{
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getName()	{
		return name;
	}

	public int getId()	{
		return id;
	}

	public String getEmail()	{
		return email;
	}

	
}
