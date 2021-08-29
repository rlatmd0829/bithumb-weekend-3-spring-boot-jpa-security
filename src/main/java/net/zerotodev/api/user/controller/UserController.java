package net.zerotodev.api.user.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import net.zerotodev.api.user.domain.User;
import net.zerotodev.api.user.domain.UserDto;
import net.zerotodev.api.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*") // cors 이슈 처리, 전부허용해줌
@Api(tags = "users") // swagger 문서작업용
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    @ApiOperation(value="${UserController.signup}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인거절"),
            @ApiResponse(code = 422, message = "중복된 ID")
    })
    public ResponseEntity<String> signup(@ApiParam("Signup User")
                                         @RequestBody UserDto userDto){
        System.out.println("회원가입 정보: " + userDto.toString());
        return ResponseEntity.ok(userService.signup(modelMapper.map(userDto, User.class)));
    }

    @PostMapping("/signin")
    @ApiOperation(value="${UserController.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 422, message = "유효하지 않은 아이디, 비밀번호")
    })
    public ResponseEntity<UserDto> signin(@ApiParam("Signin User")
                                             @RequestBody UserDto userDto){
        System.out.println("로그인 정보: " + userDto.toString());
        return ResponseEntity.ok(userService.signin(modelMapper.map(userDto, User.class)));
    }
}
