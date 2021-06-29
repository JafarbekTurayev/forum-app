package uz.pdp.forum.payload;

import lombok.Data;
import uz.pdp.forum.entity.User;

import java.sql.Timestamp;

@Data
public class CommentRes {
    Long id;
    String text;
    Timestamp createdAt;
    Timestamp updatedAt;
    Long createdBy;

}
