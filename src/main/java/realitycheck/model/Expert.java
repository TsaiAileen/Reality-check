package realitycheck.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Expert extends User {

	//@Id
	//private Integer id ;

	public Expert(Integer id, String name, String email) {
		super(id, name, email);
		// TODO Auto-generated constructor stub
	}

	public Expert() {}
}
