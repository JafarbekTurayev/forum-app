package uz.pdp.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.forum.payload.ApiResponse;
import uz.pdp.forum.payload.CommentDto;
import uz.pdp.


        forum.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    //Comment qo'shish uchun
    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addComment(@RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    // bitta comentni ko'rish uchun id bilan
    @PreAuthorize(value = "hasAuthority('VIEW_COMMENT')")
    @GetMapping("{id}")
    public HttpEntity<?> getByIdComment(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.getByIdComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    // Hamma comentlarni ko'rish uchun
    @PreAuthorize(value = "hasAuthority('VIEW_COMMENTS')")
    @GetMapping
    public HttpEntity<?> getAllComment() {
        ApiResponse allComment = commentService.getAllComment();
        return ResponseEntity.ok(allComment);
    }

//    @PreAuthorize(value = "hasAuthority('VIEW_COMMENT')")
//    @GetMapping("{id}")
//    public HttpEntity<?> getByIdComment(@PathVariable Long id) {
//        ApiResponse apiResponse = commentService.getByIdComment(id);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
//    }


}
