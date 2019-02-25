package com.anvasy.dao;

import com.anvasy.database.DataBase;
import com.anvasy.model.Author;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorsDAO {

    private DataBase db;

    public AuthorsDAO(DataBase dataBase) {
        this.db = dataBase;
    }

    public void insert(Author author) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "insert into authors (fio,birthday)"
                        + "values(?,?)");

        ps.setString(1, author.getFio());
        ps.setDate(2, (Date) author.getDate());

        ps.execute();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "delete from authors where id = ?");
        ps.setInt(1, id);
        ps.execute();
    }

    public void update(Author author) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement(
                "update authors set fio=?, birthday=? where id = ?");
        ps.setString(1, author.getFio());
        ps.setDate(2, (Date) author.getDate());
        ps.setInt(3, author.getId());

        ps.execute();
    }

    public List<Author> getAll() throws SQLException{
        List<Author> authors = new ArrayList<>();

        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from authors");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Author article = new Author();
            article.setId(rs.getInt("id"));
            article.setFio(rs.getString("fio"));
            article.setDate(rs.getDate("birthday"));

            authors.add(article);
        }

        return authors;
    }

    public Author getArticle(int id) throws SQLException{
        Author author = new Author(id);

        PreparedStatement preparedStatement = db.getCn().prepareStatement("select * from authors where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            author.setFio(rs.getString("fio"));
            author.setDate(rs.getDate("bithday"));
        }

        return author;
    }
}
