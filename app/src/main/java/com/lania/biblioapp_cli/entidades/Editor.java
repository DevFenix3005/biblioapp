package com.lania.biblioapp_cli.entidades;

import android.content.ContentValues;


public class Editor {

    public final static String _NOMBRE = "nombre";
    public final static String _DIRECCION = "direccion";
    public static final String _ID = "id";

    private Long id;
    private String nombre;
    private String direccion;

    public Editor() {
    }

    public static Editor createEditor(Long id, String nombre, String direccion) {
        Editor editor = new Editor();
        if (id != 0L) editor.setId(id);
        editor.setNombre(nombre);
        editor.setDireccion(direccion);
        return editor;
    }


    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Editor)) return false;
        final Editor other = (Editor) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre))
            return false;
        final Object this$direccion = this.getDireccion();
        final Object other$direccion = other.getDireccion();
        if (this$direccion == null ? other$direccion != null : !this$direccion.equals(other$direccion))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Editor;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $direccion = this.getDireccion();
        result = result * PRIME + ($direccion == null ? 43 : $direccion.hashCode());
        return result;
    }

    public String toString() {
        return "Editor(id=" + this.getId() + ", nombre=" + this.getNombre() + ", direccion=" + this.getDireccion() + ")";
    }

    public ContentValues toContetValues() {
        ContentValues values = new ContentValues();
        values.put(_NOMBRE, this.nombre);
        values.put(_DIRECCION, this.direccion);
        return values;
    }
}
