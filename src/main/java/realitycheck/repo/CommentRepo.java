package realitycheck.repo;

import realitycheck.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Integer>  {

	Optional<Comment> findById(Integer id);
	List<Comment> findByUserId(Integer id);
	List<Comment> findByVideoId(Integer id);
}
