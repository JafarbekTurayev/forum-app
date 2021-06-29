package uz.pdp.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.pdp.forum.payload.LoginDto;
import uz.pdp.forum.entity.User;
import uz.pdp.forum.security.JwtProvider;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    // User parol va login orqali kirishi uchun (Userlar Data DataLoader classida avtomatik qo'shiladi ,qachonki project run bo'lganda)
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword()
            ));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(409).body("Username or password error!");
        }
    }

}
