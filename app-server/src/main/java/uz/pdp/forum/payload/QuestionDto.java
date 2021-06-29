package uz.pdp.forum.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class QuestionDto {
    @NotNull(message = "Post title must not be empty")
    private String title;
    @NotNull(message = "Text title must not be empty")
    private String text;
}
