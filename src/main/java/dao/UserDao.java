package dao;

import entity.User;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Long, User> {
    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL =
            "INSERT INTO users (name, birthday, email, password, role, gender) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    @SneakyThrows
    public User save(User user) {
        System.out.println("Saving user to database: " + user.getEmail());

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(user.getBirthday()));
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getGender().name());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
                System.out.println("Successfully saved user with ID: " + id);
            } else {
                System.out.println("Warning: No ID generated for user");
            }

            return user;
        } catch (Exception e) {
            System.err.println("Failed to save user: " + e.getMessage());
            throw e;
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}