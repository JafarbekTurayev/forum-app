package uz.pdp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.forum.entity.Comment;
import uz.pdp.forum.entity.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByQuestionId(Long question_id);
//
//    @Query(value ="select c.* from questions q join comments c on q.id =:int_id" )
//    List<Comment> findByQuestionId(Long int_id);
//
//    @Query(value = "select c.* from questions q join comments c on q.id =:int_id", nativeQuery = true )
//    List<Comment> findByQuestionId(Long int_id);

}
