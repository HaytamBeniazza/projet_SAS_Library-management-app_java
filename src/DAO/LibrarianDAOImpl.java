package DAO;

import Model.Librarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LibrarianDAOImpl implements LibrarianDAO{

    //pair and impair number method


    private final Connection connection;

    public LibrarianDAOImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean addLibrarian(Librarian librarian) {
        String sql = "INSERT INTO librarian (name, code, email) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, librarian.getName());
            preparedStatement.setString(2, librarian.getCode());
            preparedStatement.setString(3, librarian.getEmail());
            int rows = preparedStatement.executeUpdate();
            return  rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Librarian> getLibrarianByEmail(String email) {
        String sql = "SELECT * FROM librarian WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int librarianID = resultSet.getInt("librarianID");
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                return Optional.of(new Librarian(name, code, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Librarian authenticateLibrarian(String email, String code) {
        String sql = "SELECT * FROM librarian WHERE email = ? AND code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int librarianID = resultSet.getInt("librarianID");
                String name = resultSet.getString("name");

                Librarian authenticate = new Librarian(null, null, null);
                authenticate.setName(name);
                authenticate.setCode(code);
                authenticate.setEmail(email);

                return authenticate;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

        @Override
    public boolean doesEmailExist(String email) {
        String sql = "SELECT COUNT(*) AS count FROM librarian WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean doesCodeExist(String code) {
        String sql = "SELECT COUNT(*) AS count FROM librarian WHERE code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isValidEmail(String email) {
        // Use a simple regex pattern to check if the email format is valid
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailPattern);
    }
}
