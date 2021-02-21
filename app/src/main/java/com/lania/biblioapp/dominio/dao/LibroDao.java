package com.lania.biblioapp.dominio.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lania.biblioapp.dominio.entidades.Libro;

import java.util.List;

@Dao
public interface LibroDao {

    @Query("SELECT * FROM libros")
    List<Libro> getAllLibros();

    @Query("SELECT * FROM libros")
    Cursor getAllLibrosByCursor();

    @Insert
    List<Long> insertAll(Libro... libros);

    @Insert
    Long insertOne(Libro libro);

    @Delete
    void deleteBook(Libro libro);

    @Query("DELETE FROM libros WHERE id = :id")
    void deleteBookById(Long id);

    @Query("SELECT count(*) FROM libros")
    Long count();

}
