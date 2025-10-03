package service;

import dao.UserDao;
import dto.CreateUserDto;
import dto.UserDto;
import entity.User;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CreateUserMapper;
import mapper.UserMapper;
import validator.CreateUserValidator;
import validator.Error;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password).map(userMapper::mapFrom);
    }

    public Integer create(CreateUserDto createUserDto) {
        validateUser(createUserDto);
        User user = mapToEntity(createUserDto);
        return saveUser(user);
    }

    private void validateUser(CreateUserDto createUserDto) {
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
    }

    private User mapToEntity(CreateUserDto createUserDto) {
        try {
            return createUserMapper.mapFrom(createUserDto);
        } catch (IllegalArgumentException e) {
            throw new ValidationException(List.of(
                    Error.of("mapping.error", "Invalid data format: " + e.getMessage())
            ));
        }
    }

    private Integer saveUser(User user) {
        try {
            userDao.save(user);
            return user.getId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user: " + e.getMessage(), e);
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}