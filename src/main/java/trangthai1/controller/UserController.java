package trangthai1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trangthai1.model.Entity.Status;
import trangthai1.model.Entity.User;
import trangthai1.model.Response.ResponseData;
import trangthai1.model.Response.ResponsePage;
import trangthai1.service.user.UserService;

@Controller
@CrossOrigin("*")
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity < ResponsePage > addUser(@RequestBody User user, @RequestParam Long statusId, @RequestParam int limit) {
        return ResponseEntity.ok(userService.save(user, statusId, limit));
    }

    @GetMapping("/getUser")
    public ResponseEntity < ResponseData > getUser() {
        return ResponseEntity.ok(userService.getUser());
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity < ResponseData > deleteUser(@RequestParam Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("updateUser")
    public ResponseEntity < ResponseData > updateUser(@RequestBody User user, @RequestParam Long id, @RequestParam Long statusId) {
        return ResponseEntity.ok(userService.updateUser(user, id, statusId));
    }

    @GetMapping("getOneUser")
    public ResponseEntity < ResponseData > getOneUser() {
        return ResponseEntity.ok(userService.getOneUser());
    }

    @GetMapping("getPagination")
    public ResponseEntity < ResponsePage > getPagination(
            @RequestParam(defaultValue = "1") Integer activePage,
            @RequestParam(defaultValue = "3") Integer limit
    ) {
        return ResponseEntity.ok(userService.getPagination(activePage, limit));
    }
}
