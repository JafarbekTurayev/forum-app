package uz.pdp.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.forum.payload.AllQuestionDto;
import uz.pdp.forum.payload.ApiResponse;
import uz.pdp.forum.payload.QuestionDto;
import uz.pdp.forum.payload.QuestionResponseDTO;
import uz.pdp.forum.service.QuestionsService;

import javax.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionsService questionsService;

    //Savol qo'shish uchun
    @PreAuthorize(value = "hasAuthority('ADD_QUESTION')")
    @PostMapping()
    public HttpEntity<?> addQuestion(@Valid @RequestBody QuestionDto questionDto) {
        ApiResponse apiResponse = questionsService.addQuestion(questionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    //Bitta userga tegishli savollar ro'yxati chiqadi
    @PreAuthorize(value = "hasAuthority('VIEW_QUESTIONS')")
    @GetMapping("/getMyAll")
    public HttpEntity<?> getMyAllQuestion() {
        AllQuestionDto myAllQuestions = questionsService.getMyAllQuestions();
        return ResponseEntity.ok(myAllQuestions);
    }

    //Hamma userga tegishli hamma savollar ro'yxati
    @PreAuthorize(value = "hasAuthority('VIEW_QUESTIONS')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAllQuestion() {
        AllQuestionDto allQuestion = questionsService.getAllQuestion();
        return ResponseEntity.ok(allQuestion);
    }

    // Hohlagan savolni id bilan topadi va shu savolga tegishli commentlar ro'yxati ham chiqadi
    @PreAuthorize(value = "hasAuthority('VIEW_QUESTION')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByIdQuestion(@PathVariable Long id) {
        QuestionResponseDTO byIdQuestions = questionsService.getByIdQuestion(id);
        return ResponseEntity.ok(byIdQuestions);
    }


//    Hamma userga tegishli hamma savollar ro'yxati faqat page bo'lib chiqadi
//    @PreAuthorize(value = "hasAuthority('VIEW_QUESTIONS')")
//    @GetMapping("/getAll")
//    public HttpEntity<?> getAllQuestion(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
//        Page allQuestion = questionsService.getAllQuestion(page, size);
//        return ResponseEntity.ok(allQuestion);
//    }


}
