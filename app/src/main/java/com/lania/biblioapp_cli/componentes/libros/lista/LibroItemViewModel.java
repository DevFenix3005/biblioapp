package com.lania.biblioapp_cli.componentes.libros.lista;

import androidx.lifecycle.ViewModel;

import com.lania.biblioapp_cli.entidades.Libro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class LibroItemViewModel extends ViewModel {

    private Libro data;

}
