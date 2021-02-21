package com.lania.biblioapp_cli.entidades;

import android.content.ContentValues;

import com.lania.biblioapp_cli.utils.DateConvertes;

import java.time.LocalDateTime;

public class Miembro {

    public final static String _ID = "id";
    public final static String _NOMBRE = "nombre";
    public final static String _FECHA_DE_NACIMIENTO = "fechaDeNacimiento";
    public final static String _DIRECCION = "direccion";
    public final static String _FECHA_DE_REGISTRO = "fechaDeRegistro";

    private Long id;
    private String nombre;
    private LocalDateTime fechaDeNacimiento;
    private String direccion;
    private LocalDateTime fechaDeRegistro;

    public Miembro() {
    }


    public static Miembro createMiembro(Long id, String nombre, LocalDateTime fechaDeNacimiento, String direccion, LocalDateTime fechaDeRegistro) {
        Miembro miembro = new Miembro();
        if (id != 0L) miembro.setId(id);
        if (fechaDeRegistro != null) miembro.setFechaDeRegistro(fechaDeRegistro);
        else miembro.setFechaDeRegistro(LocalDateTime.now());
        miembro.setNombre(nombre);
        miembro.setFechaDeNacimiento(fechaDeNacimiento);
        miembro.setDireccion(direccion);
        return miembro;
    }


    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public LocalDateTime getFechaDeNacimiento() {
        return this.fechaDeNacimiento;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public LocalDateTime getFechaDeRegistro() {
        return this.fechaDeRegistro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeNacimiento(LocalDateTime fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaDeRegistro(LocalDateTime fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Miembro)) return false;
        final Miembro other = (Miembro) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre))
            return false;
        final Object this$fechaDeNacimiento = this.getFechaDeNacimiento();
        final Object other$fechaDeNacimiento = other.getFechaDeNacimiento();
        if (this$fechaDeNacimiento == null ? other$fechaDeNacimiento != null : !this$fechaDeNacimiento.equals(other$fechaDeNacimiento))
            return false;
        final Object this$direccion = this.getDireccion();
        final Object other$direccion = other.getDireccion();
        if (this$direccion == null ? other$direccion != null : !this$direccion.equals(other$direccion))
            return false;
        final Object this$fechaDeRegistro = this.getFechaDeRegistro();
        final Object other$fechaDeRegistro = other.getFechaDeRegistro();
        if (this$fechaDeRegistro == null ? other$fechaDeRegistro != null : !this$fechaDeRegistro.equals(other$fechaDeRegistro))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Miembro;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $fechaDeNacimiento = this.getFechaDeNacimiento();
        result = result * PRIME + ($fechaDeNacimiento == null ? 43 : $fechaDeNacimiento.hashCode());
        final Object $direccion = this.getDireccion();
        result = result * PRIME + ($direccion == null ? 43 : $direccion.hashCode());
        final Object $fechaDeRegistro = this.getFechaDeRegistro();
        result = result * PRIME + ($fechaDeRegistro == null ? 43 : $fechaDeRegistro.hashCode());
        return result;
    }

    public String toString() {
        return "Miembro(id=" + this.getId() + ", nombre=" + this.getNombre() + ", fechaDeNacimiento=" + this.getFechaDeNacimiento() + ", direccion=" + this.getDireccion() + ", fechaDeRegistro=" + this.getFechaDeRegistro() + ")";
    }

    public ContentValues toContentValue() {

        ContentValues values = new ContentValues();
        values.put(_NOMBRE, nombre);
        values.put(_FECHA_DE_NACIMIENTO, fechaDeNacimiento.format(DateConvertes.FORMATTER));
        values.put(_DIRECCION, direccion);
        if (this.fechaDeRegistro != null) {
            values.put(_FECHA_DE_REGISTRO, fechaDeRegistro.format(DateConvertes.FORMATTER));
        }

        return values;
    }
}
