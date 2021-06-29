package uz.pdp.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.forum.entity.template.AbcEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
public class Comment extends AbcEntity {
    @Column(nullable = false, columnDefinition = "text")
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

}
