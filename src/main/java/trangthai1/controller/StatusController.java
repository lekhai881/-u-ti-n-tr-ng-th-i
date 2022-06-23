package trangthai1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trangthai1.model.Entity.Status;
import trangthai1.model.Response.ResponseData;
import trangthai1.service.status.StatusService;

@Controller
@CrossOrigin("*")
@RequestMapping
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping("addStatus")
    public ResponseEntity< ResponseData > addStatus(@RequestBody Status status){
        return ResponseEntity.ok(statusService.save(status));
    }

    @GetMapping("getStatus")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(statusService.getAllStatusAndSort());
    }

    @PutMapping("updateStatus")
    public ResponseEntity<ResponseData> updateStatus(@RequestBody Status status , @RequestParam Long id){
       return ResponseEntity.ok(statusService.updateStatus(status, id));
    }

    @DeleteMapping("deleteStatus")
    public ResponseEntity<ResponseData> deleteStatus(@RequestParam Long id){
        return ResponseEntity.ok(statusService.deleteStatus(id));
    }


}
