package validator;

import dto.CreateUserDto;
import entity.Gender;
import entity.Role;
import lombok.NoArgsConstructor;
import utils.LocalDateFormatter;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto userDto) {
        var validationResult = new ValidationResult();
        if (userDto.getName() == null || userDto.getName().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.name", "Name is required"));
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.email", "Email is required"));
        }

        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.password", "Password is required"));
        }

        if (userDto.getBirthday() == null || userDto.getBirthday().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.birthday.empty", "Birthday is required"));
        } else if (!LocalDateFormatter.isValid(userDto.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday.format", "Birthday is invalid"));
        }

        if (userDto.getRole() == null || userDto.getRole().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.role.empty", "Role is required"));
        } else if (Role.find(userDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role.value", "Role is invalid"));
        }

        if (userDto.getGender() == null || userDto.getGender().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.gender.empty", "Gender is required"));
        } else if (Gender.find(userDto.getGender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender.value", "Gender is invalid"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}