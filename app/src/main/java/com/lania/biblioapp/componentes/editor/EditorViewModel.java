package com.lania.biblioapp.componentes.editor;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lania.biblioapp.componentes.editor.lista.EditorAdapter;
import com.lania.biblioapp.dominio.entidades.Editor;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class EditorViewModel extends ViewModel {

    private final MutableLiveData<String> nombre = new MutableLiveData<>();
    private final MutableLiveData<String> direccion = new MutableLiveData<>();
    private final MutableLiveData<Boolean> nombreValid = new MutableLiveData<>();
    private final MutableLiveData<Boolean> direccionValid = new MutableLiveData<>();

    private EditorAdapter editorAdapter = new EditorAdapter();
    private MutableLiveData<String> toolbarsubtitle = new MutableLiveData<>("UNK");


    public Editor createEditorByViewModel() {

        String nombre = this.nombre.getValue();
        String direccion = this.direccion.getValue();

        Editor editor = new Editor();
        editor.setNombre(nombre);
        editor.setDireccion(direccion);
        return editor;
    }


    public void clean() {
        this.nombre.setValue(null);
        this.direccion.setValue(null);
        this.nombreValid.setValue(false);
        this.direccionValid.setValue(false);
    }

}
