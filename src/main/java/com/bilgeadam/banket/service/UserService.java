package com.bilgeadam.banket.service;

import com.bilgeadam.banket.dto.request.*;
import com.bilgeadam.banket.dto.response.UserResponseDto;
import com.bilgeadam.banket.entity.User;
import com.bilgeadam.banket.entity.enums.ERole;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;
import com.bilgeadam.banket.mapper.IUserMapper;
import com.bilgeadam.banket.repository.UserRepository;
import com.bilgeadam.banket.utility.JwtTokenManager;
import com.bilgeadam.banket.utility.PasswordGenerator;
import com.bilgeadam.banket.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends ServiceManager<User, String> {

    private final UserRepository userRepository;

    private final JwtTokenManager jwtTokenManager;

    private final MailService mailService;

    // Volkan: password encoder eklendi fakat yorum satırına alındılar.
//    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtTokenManager jwtTokenManager, MailService mailService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.mailService = mailService;
    }

    public String saveUser(SaveUserRequestDto dto) {
        if(!dto.getPassword().equals(dto.getRePassword())) throw new BanketApplicationException(ErrorType.PASSWORD_MISMATCH);
        if(userRepository.existsByEmail(dto.getEmail())) throw new BanketApplicationException(ErrorType.EMAIL_ALREADY_EXISTS);
        dto.setRoles(dto.getRoles().stream().distinct().toList());
        User user = IUserMapper.INSTANCE.saveUserDtoToUser(dto);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        return "Save user successful";
    }

    public String updateUser(UpdateUserRequestDto dto) {
        User user = getUserByToken(dto.getToken());
        Optional<User> optionalUserByEmail = userRepository.findByEmail(dto.getEmail());
        if(optionalUserByEmail.isPresent()) {
            if(!user.getUuid().equals(optionalUserByEmail.get().getUuid())) throw new BanketApplicationException(ErrorType.EMAIL_ALREADY_EXISTS);
        };
        user.setEmail(dto.getEmail());
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        update(user);
        return "Update user successful";
    }

    public User getUserByToken(String token) {
        UUID uuid = UUID.fromString(jwtTokenManager.getUUIDFromTokenAsString(token));
        return userRepository.findByUuid(uuid).orElseThrow(() -> new BanketApplicationException(ErrorType.USER_NOT_FOUND));
    }
    /*
    * Yasemin: Kullanıcı bilgilerini Response DTO ile döndüren metot
     */
    public UserResponseDto getUserResponseDto(String token) {
        UUID uuid=UUID.fromString(jwtTokenManager.getUUIDFromTokenAsString(token));
        User user = userRepository.findByUuid(uuid).orElseThrow(() -> new BanketApplicationException(ErrorType.USER_NOT_FOUND));
        return IUserMapper.INSTANCE.userToUserResponseDto(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new BanketApplicationException(ErrorType.USER_NOT_FOUND));
    }

    public User findUserByUUID(String uuid) {
        return userRepository.findByUuid(UUID.fromString(uuid)).orElseThrow(() -> new BanketApplicationException(ErrorType.USER_NOT_FOUND));
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public String deleteUser(String token) {
        User user = getUserByToken(token);
        if(user.isDeleted()) throw new BanketApplicationException(ErrorType.USER_ALREADY_DELETED);
        Boolean isDeleted = softDelete(user);
        if(isDeleted) return "Delete user successful";
        return "Delete user failed";
    }

    public String removeUser(RemoveUserRequestDto dto) {
        User user = userRepository.findByUuid(dto.getUuid()).orElseThrow(() -> new BanketApplicationException(ErrorType.USER_NOT_FOUND));
        hardDelete(user);
        return "Remove user successful";
    }

    public String addManager(AddManagerRequestDto dto) {

        if(userRepository.existsByEmail(dto.getEmail())) throw new BanketApplicationException(ErrorType.EMAIL_ALREADY_EXISTS);

        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(PasswordGenerator.generatePassword())
                .roles(List.of(ERole.MANAGER))
                .build();

        save(user);

        mailService.sendLoginCredentialsMailToManager(user);

        return "Manager added successful";
    }

    public String updatePassword(UpdatePasswordRequestDto dto) {

        User user = getUserByToken(dto.getToken());
        user.setPassword(dto.getPassword());
        update(user);
        return "Update password successful";
    }
}
