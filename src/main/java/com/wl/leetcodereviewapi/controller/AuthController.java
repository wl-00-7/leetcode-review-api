package com.wl.leetcodereviewapi.controller;


import com.wl.leetcodereviewapi.common.Result;
import com.wl.leetcodereviewapi.dto.LoginDTO;
import com.wl.leetcodereviewapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")     //@Valid触发loginDTO的@NotBlank校验
    public Result<String> login(@Valid @RequestBody LoginDTO dto){
        return Result.ok(userService.login(dto.getUsername(),dto.getPassword()));
    }
}
