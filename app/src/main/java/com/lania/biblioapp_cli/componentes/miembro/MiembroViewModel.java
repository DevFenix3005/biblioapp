package com.lania.biblioapp_cli.componentes.miembro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lania.biblioapp_cli.componentes.miembro.lista.MiembroListAdapter;
import com.lania.biblioapp_cli.entidades.Miembro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MiembroViewModel extends ViewModel {

    private final MutableLiveData<String> nombre = new MutableLiveData<>();
    private final MutableLiveData<String> fechaDeNacimiento = new MutableLiveData<>();
    private final MutableLiveData<String> direccion = new MutableLiveData<>();

    private final MutableLiveData<Boolean> nombreValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> fechaDeNacimientoValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> direccionValid = new MutableLiveData<>(Boolean.FALSE);

    private final MiembroListAdapter miembroListAdapter = new MiembroListAdapter();
    private MutableLiveData<String> toolbarsubtitle = new MutableLiveData<>("UNK");

    private Miembro miembro2Update;

    public Miembro createMiembroFromViewModel() {
        String nombreMiembro = this.nombre.getValue();
        String fechaDeNacimientoMiembro = this.fechaDeNacimiento.getValue() + "T00:00:00";
        String direccionMiembro = this.direccion.getValue();

        Miembro miembro = new Miembro();
        miembro.setDireccion(direccionMiembro);
        miembro.setFechaDeRegistro(LocalDateTime.now());
        miembro.setFechaDeNacimiento(LocalDateTime.parse(fechaDeNacimientoMiembro, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        miembro.setNombre(nombreMiembro);

        return miembro;
    }

    public void clean() {
        miembro2Update = null;
        nombre.setValue(null);
        fechaDeNacimiento.setValue(null);
        direccion.setValue(null);
    }

    public void setValues2Update(Miembro miembro) {
        this.miembro2Update = miembro;
        LocalDateTime localDate = miembro.getFechaDeNacimiento();
        nombre.setValue(miembro.getNombre());
        fechaDeNacimiento.setValue(localDate.format(DateTimeFormatter.ISO_DATE));
        direccion.setValue(miembro.getDireccion());
    }


}
