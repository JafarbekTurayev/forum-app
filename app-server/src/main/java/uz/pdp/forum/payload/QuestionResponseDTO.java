package uz.pdp.forum.payload;

import lombok.Data;
import uz.pdp.forum.entity.Comment;
import uz.pdp.forum.entity.Question;

import java.util.List;

@Data
public class QuestionResponseDTO {
    private Long questionID;
    private String questionText;
    private String questionTitle;
    private String owner;
    private Long ownerId;
    private List<CommentRes> commentList;
}
