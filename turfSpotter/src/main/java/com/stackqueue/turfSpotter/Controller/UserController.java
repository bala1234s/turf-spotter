package com.stackqueue.turfSpotter.Controller;


import com.stackqueue.turfSpotter.Dto.UserRequestDto;
import com.stackqueue.turfSpotter.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userID}")
    public ResponseEntity<?> getUser(@PathVariable int userID){
        return userService.getUser(userID);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userDto){
        return userService.createUser(userDto);
    }
}
