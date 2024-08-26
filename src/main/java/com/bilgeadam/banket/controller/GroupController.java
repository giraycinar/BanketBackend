package com.bilgeadam.banket.controller;

import com.bilgeadam.banket.dto.request.SaveGroupRequestDto;
import com.bilgeadam.banket.dto.request.UpdateGroupRequestDto;
import com.bilgeadam.banket.entity.Group;
import com.bilgeadam.banket.entity.Student;
import com.bilgeadam.banket.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

import static com.bilgeadam.banket.constant.Endpoint.*;

@RestController
@RequestMapping(ROOT + GROUP)
@RequiredArgsConstructor
@CrossOrigin
public class GroupController {

    private final GroupService groupService;

    @PostMapping(SAVE)
    public ResponseEntity<String> saveGroup(@RequestBody SaveGroupRequestDto dto){
        return ResponseEntity.ok(groupService.saveGroup(dto));
    }

    @GetMapping(GET_BY_UUID)
    public ResponseEntity<Group> getGroup(@PathVariable UUID uuid){
        return ResponseEntity.ok((groupService.getGroup(uuid)));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<Group>> getAllGroups(){
        return ResponseEntity.ok((groupService.findAll()));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateGroup(UpdateGroupRequestDto dto){
        return ResponseEntity.ok(groupService.updateGroup(dto));
    }

    @DeleteMapping(DELETE_BY_UUID)
    public ResponseEntity<String> deleteGroup(@PathVariable UUID uuid){
        return ResponseEntity.ok(groupService.deleteGroup(uuid));
    }

    @DeleteMapping(REMOVE_BY_UUID)
    public ResponseEntity<String> removeGroup(@PathVariable UUID uuid){
        return ResponseEntity.ok(groupService.removeGroup(uuid));
    }

    @GetMapping(FIND_BY_NAME)
    @CrossOrigin("*")
    public ResponseEntity<List<Group>> findGroupByName(@PathVariable String groupName){
        return ResponseEntity.ok(groupService.findByName(groupName));
    }

}
