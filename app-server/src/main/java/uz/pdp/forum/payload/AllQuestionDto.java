package uz.pdp.forum.payload;

import lombok.Data;
import uz.pdp.forum.entity.Question;

import java.util.List;

@Data
public class AllQuestionDto {
    List<QuestionRes> questionList;

}
