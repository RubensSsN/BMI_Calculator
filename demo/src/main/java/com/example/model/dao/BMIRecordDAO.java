package com.example.model.dao;

import com.example.model.BMIRecord;
import com.example.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BMIRecordDAO {
    private Connection connection;

    public BMIRecordDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(BMIRecord record) throws DataAccessException {
        String sql = "INSERT INTO BMI_RECORDS (HEIGHT, WEIGHT, BMI) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, record.getHeight());
            stmt.setDouble(2, record.getWeight());
            stmt.setDouble(3, record.getBmi());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao salvar o registro no banco de dados.", e);
        }
    }

    public List<BMIRecord> getAllRecords() throws DataAccessException {
        List<BMIRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM BMI_RECORDS";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                BMIRecord record = new BMIRecord(rs.getDouble("HEIGHT"), rs.getDouble("WEIGHT"), rs.getDouble("BMI"));
                records.add(record);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar registros no banco de dados.", e);
        }
        return records;
    }
}
