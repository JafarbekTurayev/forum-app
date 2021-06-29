package uz.pdp.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.forum.entity.Comment;
import uz.pdp.forum.entity.Question;
import uz.pdp.forum.payload.CommentDto;
import uz.pdp.forum.payload.ApiResponse;
import uz.pdp.forum.repository.CommentRepository;
import uz.pdp.forum.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ApiResponse addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        Optional<Question> optionalQuestion = questionRepository.findById(commentDto.getQuestionId());
        if (!optionalQuestion.isPresent()) {
            return new ApiResponse("Bunday id li Question topilmadi!", false);
        }
        comment.setQuestion(optionalQuestion.get());
        comment.setText(commentDto.getTextComment());
        commentRepository.save(comment);
        return new ApiResponse("Successfully comment added!", true);
    }

    public ApiResponse getByIdComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            return new ApiResponse("Comment not found!", false);
        }
        return new ApiResponse("Comment topildi!", true, optionalComment);
    }

    public ApiResponse getAllComment() {
        List<Comment> all = commentRepository.findAll();
        return new ApiResponse("Commentlar marhamat", true, all);
    }

//    public List<Comment> getById(Long id) {
//        List<Comment> byQuestionId = commentRepository.findByQuestionId(id);
//        return byQuestionId;
//
//    }


}
