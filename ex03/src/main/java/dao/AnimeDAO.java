package dao;

import model.Anime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimeDAO {

	private Connection connection;

	public AnimeDAO() {
		try {
			// Load the PostgreSQL JDBC driver
			Class.forName("org.postgresql.Driver");
			// Connect to the database
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste", "postgres", "ti@cc");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(Anime anime) {
		String sql = "INSERT INTO animes (descricao, eps, inicio, fim) VALUES (?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, anime.getDescricao());
			statement.setInt(2, anime.getEps());
			statement.setDate(3, Date.valueOf(anime.getInicio()));
			statement.setDate(4, Date.valueOf(anime.getFim()));

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Anime get(int id) {
		Anime anime = null;
		String sql = "SELECT * FROM animes WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				anime = new Anime();
				anime.setId(resultSet.getInt("id"));
				anime.setDescricao(resultSet.getString("descricao"));
				anime.setEps(resultSet.getInt("eps"));
				anime.setInicio(resultSet.getDate("inicio").toLocalDate());
				anime.setFim(resultSet.getDate("fim").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return anime;
	}

	public void update(Anime anime) {
		String sql = "UPDATE animes SET descricao = ?, eps = ?, inicio = ?, fim = ? WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, anime.getDescricao());
			statement.setInt(2, anime.getEps());
			statement.setDate(3, Date.valueOf(anime.getInicio()));
			statement.setDate(4, Date.valueOf(anime.getFim()));
			statement.setInt(5, anime.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(int id) {
		String sql = "DELETE FROM animes WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Anime> getAll() {
		List<Anime> animes = new ArrayList<>();
		String sql = "SELECT * FROM animes";

		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Anime anime = new Anime();
				anime.setId(resultSet.getInt("id"));
				anime.setDescricao(resultSet.getString("descricao"));
				anime.setEps(resultSet.getInt("eps"));
				anime.setInicio(resultSet.getDate("inicio").toLocalDate());
				anime.setFim(resultSet.getDate("fim").toLocalDate());
				animes.add(anime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return animes;
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}