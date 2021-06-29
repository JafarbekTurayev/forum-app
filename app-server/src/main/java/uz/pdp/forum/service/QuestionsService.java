package uz.pdp.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.forum.entity.Comment;
import uz.pdp.forum.entity.User;
import uz.pdp.forum.payload.*;
import uz.pdp.forum.entity.Question;
import uz.pdp.forum.repository.CommentRepository;
import uz.pdp.forum.repository.QuestionRepository;
import uz.pdp.forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    public ApiResponse addQuestion(QuestionDto questionDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Question question = new Question();
        Optional<User> optionalUser = userRepository.findById(user.getId());
//        if (!optionalUser.isPresent()) {
//            return new ApiResponse("User topilmadi!", false);
//        }

        question.setUser(optionalUser.get());
        question.setTitle(questionDto.getTitle());
        question.setText(questionDto.getText());
        questionRepository.save(question);
        return new ApiResponse("Question successfully added!", true);
    }

    public AllQuestionDto getMyAllQuestions() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        List<Question> questionList = questionRepository.findAllByUserId(user.getId());

        List<QuestionRes> questionResList = new ArrayList<>();
        AllQuestionDto allQuestionDtos = new AllQuestionDto();

        if (questionList.isEmpty()) return null;


        for (Question question : questionList) {
            QuestionRes questionRes = new QuestionRes();
            questionRes.setQuestionText(question.getText());
            questionRes.setQuestionTitle(question.getTitle());
            questionRes.setQuestionID(question.getId());
            questionRes.setOwner(question.getUser().getFullName());
            questionRes.setOwnerId(question.getUser().getId());

            questionResList.add(questionRes);
        }
        allQuestionDtos.setQuestionList(questionResList);
        return allQuestionDtos;

    }

    public QuestionResponseDTO getByIdQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent()) {
            return null;
        }


        List<Comment> commentList = commentRepository.findAllByQuestionId(id);
        List<CommentRes> commentResList = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentRes commentRes = new CommentRes();
            commentRes.setText(comment.getText());
            commentRes.setCreatedAt(comment.getCreatedAt());
            commentRes.setUpdatedAt(comment.getUpdatedAt());
            commentRes.setId(comment.getId());
            commentRes.setUpdatedAt(comment.getUpdatedAt());

            commentResList.add(commentRes);
        }
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        questionResponseDTO.setQuestionID(optionalQuestion.get().getId());
        questionResponseDTO.setQuestionText(optionalQuestion.get().getText());
        questionResponseDTO.setQuestionTitle(optionalQuestion.get().getTitle());
        questionResponseDTO.setOwner(optionalQuestion.get().getUser().getFullName());
        questionResponseDTO.setOwnerId(optionalQuestion.get().getUser().getId());
//        questionResponseDTO.setQuestion(optionalQuestion.get());
        questionResponseDTO.setCommentList(commentResList);
        return questionResponseDTO;
    }


    public AllQuestionDto getAllQuestion() {

        List<Question> all = questionRepository.findAll();
        if (all.isEmpty()) return null;

        AllQuestionDto allQuestionDto = new AllQuestionDto();
        List<QuestionRes> questionList = new ArrayList<>();

        for (Question question1 : all) {
            QuestionRes questionREs = new QuestionRes();
            questionREs.setQuestionID(question1.getId());
            questionREs.setQuestionText(question1.getText());
            questionREs.setQuestionTitle(question1.getTitle());
            questionREs.setOwnerId(question1.getUser().getId());
            questionREs.setOwner(question1.getUser().getFullName());
            questionList.add(questionREs);
        }
        allQuestionDto.setQuestionList(questionList);
        return allQuestionDto;
    }

//    public ApiResponse getMyAllQuestion() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<Question> questionList = questionRepository.findAllByUserId(user.getId());
//
//
//        if (!questionList.isEmpty()) {
//            new ApiResponse("Sizning savollar to'plamingiz hali mavjud emas!", false);
//        }
//        return new ApiResponse("Sizning savollar to'plamingiz!", true, questionList);
//
//
//    }


//    public ApiResponse getByIdQuestion(Long id) {
//        Optional<Question> optionalQuestion = questionRepository.findById(id);
//        if (!optionalQuestion.isPresent()) {
//            return new ApiResponse("Question topilmadi", false);
//        }
//        return new ApiResponse("Question topildi", true, optionalQuestion);
//        return optionalQuestion.map(question -> new ApiResponse("Question topildi!", true, question)).orElseGet(() -> new ApiResponse("Bunday id li question topilmadi!", false));
//    }

//    public Page getAllQuestion(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Question> all = questionRepository.findAll(pageable);
//        return all;
//    }
}
