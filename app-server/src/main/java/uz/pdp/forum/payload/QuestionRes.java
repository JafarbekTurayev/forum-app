package uz.pdp.forum.payload;

import lombok.Data;

@Data
public class QuestionRes {

    private Long questionID;
    private String questionText;
    private String questionTitle;
    private String owner;
    private Long ownerId;
}
