package com.bilgeadam.banket.service;

import com.bilgeadam.banket.dto.request.SaveGroupRequestDto;
import com.bilgeadam.banket.dto.request.UpdateGroupRequestDto;
import com.bilgeadam.banket.entity.BaseAPIGroup;
import com.bilgeadam.banket.entity.Group;
import com.bilgeadam.banket.entity.Student;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;
import com.bilgeadam.banket.mapper.IGroupMapper;
import com.bilgeadam.banket.repository.GroupRepository;
import com.bilgeadam.banket.utility.ServiceManager;
import com.google.gson.Gson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class GroupService extends ServiceManager<Group, String> {

    private final GroupRepository groupRepository;

    public GroupService(MongoRepository<Group, String> mongoRepository, GroupRepository groupRepository) {
        super(mongoRepository);
        this.groupRepository = groupRepository;
    }

    public String saveGroup(SaveGroupRequestDto dto) {
        if (groupRepository.existsByName(dto.getName())){
            throw new BanketApplicationException(ErrorType.GROUP_ALREADY_EXISTS);
        }
        Group group = IGroupMapper.INSTANCE.saveGroupDtoToGroup(dto);
        save(group);
        return "Save group successfully.";
    }

    public Group getGroup(UUID uuid) {
        Optional<Group> optionalGroup = groupRepository.findByUuid(uuid);
        if (optionalGroup.isEmpty()){
            throw new BanketApplicationException(ErrorType.GROUP_NOT_FOUND);
        }
        return optionalGroup.get();
    }

    public String updateGroup(UpdateGroupRequestDto dto) {
        Optional<Group> optionalGroup = groupRepository.findByUuid(dto.getUuid());
        if (optionalGroup.isEmpty()){
            throw new BanketApplicationException(ErrorType.GROUP_NOT_FOUND);
        }
        if (dto.getName() != null){
            if (groupRepository.existsByName(dto.getName())){
                throw new BanketApplicationException(ErrorType.GROUP_ALREADY_EXISTS);
            }else {
                optionalGroup.get().setName(dto.getName());
            }
        }
        if(dto.getStudents() != null){
            optionalGroup.get().getStudents().addAll(dto.getStudents());
        }
        update(optionalGroup.get());
        return "Update group successfully.";
    }


    public String deleteGroup(UUID uuid) {
        Optional<Group> optionalGroup = groupRepository.findByUuid(uuid);
        if (optionalGroup.isEmpty()){
            throw new BanketApplicationException(ErrorType.GROUP_NOT_FOUND);
        }
        if (optionalGroup.get().isDeleted()){
            throw new BanketApplicationException(ErrorType.GROUP_ALREADY_DELETED);
        }
        softDelete(optionalGroup.get());
        return "Delete group successfully";
    }

    public String removeGroup(UUID uuid) {
        Optional<Group> optionalGroup = groupRepository.findByUuid(uuid);
        if (optionalGroup.isEmpty()){
            throw new BanketApplicationException(ErrorType.GROUP_NOT_FOUND);
        }
        hardDelete(optionalGroup.get());
        return "Remove group successfully";
    }

    public List<Group> findByName(String groupName) {
        List<Group> groupList = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8082/coursegroup/find-course-groups-by-name-like/"+groupName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String value = bufferedReader.readLine();
            BaseAPIGroup[] array = new Gson().fromJson(value, BaseAPIGroup[].class);
            Arrays.asList(array).forEach(o->{
                Group group = Group.builder()
                        .id(o.getId().toString())
                        .name(o.getName())
                        .uuid(o.getUuid()).build();
                groupList.add(group);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return groupList;
    }



}
