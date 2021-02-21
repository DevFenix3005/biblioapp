package com.lania.biblioapp_cli.componentes.editor.lista;

import androidx.lifecycle.ViewModel;

import com.lania.biblioapp_cli.entidades.Editor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EditorItemViewModel extends ViewModel {

    private final Editor data;

}
