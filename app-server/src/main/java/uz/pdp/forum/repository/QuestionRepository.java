package uz.pdp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.forum.entity.Question;
import uz.pdp.forum.entity.User;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

//    @Query(value = "select q.* from questions q join comments c on q.id =:int_id", nativeQuery = true)
//    List<Question> findByQuestionId(Integer int_id);

    List<Question> findAllByUserId(Long user_id);
}
