package realitycheck.repo;

import realitycheck.model.Video;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepo extends CrudRepository<Video, Integer> {

	//List<Video> findByUserId(Integer user);
}
