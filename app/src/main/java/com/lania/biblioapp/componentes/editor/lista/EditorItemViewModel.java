package com.lania.biblioapp.componentes.editor.lista;

import androidx.lifecycle.ViewModel;

import com.lania.biblioapp.dominio.entidades.Editor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EditorItemViewModel extends ViewModel {

    private final Editor data;

}
