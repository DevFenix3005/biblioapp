package com.lania.biblioapp_cli.entidades;

import android.content.ContentValues;

public class Libro {

    public final static String LIBRO_ID = "id";
    public final static String LIBRO_NOMBRE = "nombre";
    public final static String LIBRO_DISPONIBILIDAD = "disponibilidad";
    public final static String LIBRO_PRECIO = "precio";
    public final static String LIBRO_AUTOR = "autor";

    private Long id;
    private String nombre;
    private Integer disponibilidad;
    private Float precio;
    private String autor;

    public Libro() {
    }

    public static Libro createLibro(Long id, String nombre, Integer disponibilidad, Float precio, String autor) {
        Libro libro = new Libro();
        if (id != 0L) libro.setId(id);
        libro.setNombre(nombre);
        libro.setDisponibilidad(disponibilidad);
        libro.setPrecio(precio);
        libro.setAutor(autor);
        return libro;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof Libro;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getDisponibilidad() {
        return this.disponibilidad;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDisponibilidad(Integer disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Libro)) return false;
        final Libro other = (Libro) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre))
            return false;
        final Object this$disponibilidad = this.getDisponibilidad();
        final Object other$disponibilidad = other.getDisponibilidad();
        if (this$disponibilidad == null ? other$disponibilidad != null : !this$disponibilidad.equals(other$disponibilidad))
            return false;
        final Object this$precio = this.getPrecio();
        final Object other$precio = other.getPrecio();
        if (this$precio == null ? other$precio != null : !this$precio.equals(other$precio))
            return false;
        final Object this$autor = this.getAutor();
        final Object other$autor = other.getAutor();
        if (this$autor == null ? other$autor != null : !this$autor.equals(other$autor))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $disponibilidad = this.getDisponibilidad();
        result = result * PRIME + ($disponibilidad == null ? 43 : $disponibilidad.hashCode());
        final Object $precio = this.getPrecio();
        result = result * PRIME + ($precio == null ? 43 : $precio.hashCode());
        final Object $autor = this.getAutor();
        result = result * PRIME + ($autor == null ? 43 : $autor.hashCode());
        return result;
    }

    public String toString() {
        return "Libro(id=" + this.getId() + ", nombre=" + this.getNombre() + ", disponibilidad=" + this.getDisponibilidad() + ", precio=" + this.getPrecio() + ", autor=" + this.getAutor() + ")";
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Libro.LIBRO_NOMBRE, nombre);
        contentValues.put(Libro.LIBRO_DISPONIBILIDAD, disponibilidad);
        contentValues.put(Libro.LIBRO_PRECIO, precio);
        contentValues.put(Libro.LIBRO_AUTOR, autor);
        return contentValues;
    }
}
