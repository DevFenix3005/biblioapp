package com.lania.biblioapp.componentes.libros;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lania.biblioapp.componentes.libros.lista.LibrosListAdapter;
import com.lania.biblioapp.dominio.entidades.Libro;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LibroViewModel extends ViewModel {

    private final MutableLiveData<String> titulo = new MutableLiveData<>();
    private final MutableLiveData<String> disponibilidad = new MutableLiveData<>();
    private final MutableLiveData<String> precio = new MutableLiveData<>();
    private final MutableLiveData<String> autor = new MutableLiveData<>();
    private final LibrosListAdapter libroAdapter = new LibrosListAdapter();

    private final MutableLiveData<Boolean> tituloValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> disponibilidadValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> precioValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> autorValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> dirty = new MutableLiveData<>(Boolean.FALSE);

    private MutableLiveData<String> toolbarsubtitle = new MutableLiveData<>("UNK");

    public Libro createEntityFromView() {
        String titulo = this.titulo.getValue();
        String disponibilidad = this.disponibilidad.getValue();
        String precio = this.precio.getValue();
        String autor = this.autor.getValue();

        Libro libro = new Libro();
        libro.setNombre(titulo);
        libro.setDisponibilidad(Integer.parseInt(disponibilidad));
        libro.setPrecio(Float.parseFloat(precio));
        libro.setAutor(autor);
        return libro;
    }


    public void clean() {
        titulo.setValue(null);
        disponibilidad.setValue(null);
        precio.setValue(null);
        autor.setValue(null);
    }
}
