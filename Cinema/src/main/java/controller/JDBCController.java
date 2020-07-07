package controller;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCController {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:library.db";

    private Connection connection;
    private Statement statement;

    public JDBCController() {
        try {
            Class.forName(JDBCController.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z ustanowieniem połączenia");
            e.printStackTrace();
        }
        createTables();
    }

    public boolean createTables() {
        String createCinemaUsers = "CREATE TABLE IF NOT EXISTS cinema_users" +
                "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, user_name varchar(255), user_surname varchar(255))";
        String createMovies = "create  TABLE IF NOT EXISTS movies " +
                "(movie_id INTEGER PRIMARY KEY AUTOINCREMENT, title varchar (255), length int)";
        String createCinemaHalls = "create  TABLE IF NOT EXISTS cinemaHalls" +
                "(cinemaHall_id INTEGER PRIMARY KEY AUTOINCREMENT, number int, seat_number int)";
        String createSeances = "create  TABLE IF NOT EXISTS seances " +
                "(seance_id INTEGER PRIMARY KEY AUTOINCREMENT, time timestamp, movie bigint, cinemaHall bigint,  price int)";
        String alterCreateSeances = "alter TABLE seances add foreign key (cinemaHall) references cinemaHalls(cinemaHall_id)";
        String alterCreateSeances2 = "alter TABLE seances add foreign key (movie) references movies(movie_id)";
        String createTicket = "create  TABLE IF NOT EXISTS tickets " +
                "(ticket_id INTEGER PRIMARY KEY AUTOINCREMENT, cinema_user int, seance int)";
        String alterCreateTicket = "alter TABLE tickets add foreign key (cinema_user) references cinema_users(user_id)";
        String alterCreateTicket2 = "alter TABLE tickets add foreign key (seance) references seances(seance_id)";

        try {
            statement.execute(createCinemaUsers);
            statement.execute(createMovies);
            statement.execute(createCinemaHalls);
            statement.execute(createSeances);
            statement.execute(alterCreateSeances);
            statement.execute(alterCreateSeances2);
            statement.execute(createTicket);
            statement.execute(alterCreateTicket);
            statement.execute(alterCreateTicket2);
        } catch (SQLException e) {
            System.err.println("Błąd przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertCinemaUser(String name, String surname) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into library_users values (NULL, ?, ?, ?);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, surname);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy wstawianiu użytkownika");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertMovie(String title, int length) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into movies values (NULL, ?, ?);");
            prepStmt.setString(1, title);
            prepStmt.setInt(2, length);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy wstawianiu książki");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertBookRental(String libraryUserId, String bookId) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into book_rental values (NULL, ?, ?);");
            prepStmt.setString(1, libraryUserId);
            prepStmt.setString(2, bookId);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy wstawianiu wypożyczonych książek");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<LibraryUser> selectLibraryUsers() {
        List<LibraryUser> libraryUsers = new LinkedList<LibraryUser>();
        try {
            ResultSet result = statement.executeQuery("SELECT*FROM library_users");
            int id;
            String name, surname, PID;
            while (result.next()) {
                id = result.getInt("reader_id");
                name = result.getString("user_name");
                surname = result.getString("surname");
                PID = result.getString("p_id");
                libraryUsers.add(new LibraryUser(id, name, surname, PID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return libraryUsers;
    }
    public List<Book> selectBooks() {
        List<Book> books = new LinkedList<Book>();
        try {
            ResultSet result = statement.executeQuery("SELECT*FROM books");
            int id;
            String title, author;
            while (result.next()) {
                id = result.getInt("book_id");
                title = result.getString("title");
                author = result.getString("author");
                books.add(new Book(id, title, author));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return books;
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknięciem połączenia");
            e.printStackTrace();
        }
    }

}
