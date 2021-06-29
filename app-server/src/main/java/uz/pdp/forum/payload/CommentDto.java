package uz.pdp.forum.payload;

import lombok.Data;
import uz.pdp.forum.entity.Question;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    private String textComment;
    private Long questionId;
}
