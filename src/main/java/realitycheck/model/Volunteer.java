package realitycheck.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Volunteer extends User {

	//@Id
	//private Integer id ;

	public Volunteer() {}

	public Volunteer(Integer id, String name, String email) {
		super(id, name, email);
		// TODO Auto-generated constructor stub
	}

}
