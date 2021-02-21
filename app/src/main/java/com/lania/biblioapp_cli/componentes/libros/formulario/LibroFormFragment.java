package com.lania.biblioapp_cli.componentes.libros.formulario;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.lania.biblioapp_cli.R;
import com.lania.biblioapp_cli.componentes.libros.LibroActivity;
import com.lania.biblioapp_cli.componentes.libros.LibroController;
import com.lania.biblioapp_cli.componentes.libros.LibroViewModel;
import com.lania.biblioapp_cli.databinding.FragmentLibroFormBinding;

import java.util.Objects;

public class LibroFormFragment extends Fragment {

    private static final String TAG = "LibroActivity";


    public LibroFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LibroListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibroFormFragment newInstance() {
        return new LibroFormFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LibroActivity libroActivity = (LibroActivity) getActivity();
        LibroController libroController = libroActivity.getLibroController();

        LibroViewModel libroViewModel = new ViewModelProvider(libroActivity).get(LibroViewModel.class);
        FragmentLibroFormBinding activityLibroBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_libro_form, container, false);
        activityLibroBinding.setLibro(libroViewModel);
        activityLibroBinding.setLifecycleOwner(this);
        View view = activityLibroBinding.getRoot();

        Button registerLibroButton = view.findViewById(R.id.register_libro);
        registerLibroButton.setOnClickListener(libroController);

        this.createValidations(libroViewModel);

        return view;
    }


    public void createValidations(LibroViewModel libroViewModel) {
        libroViewModel.getTitulo().observe(this.getViewLifecycleOwner(), value -> this.emptyFieldValidator(value, R.id.libro_nombre_field, libroViewModel.getTituloValid()));
        libroViewModel.getDisponibilidad().observe(this.getViewLifecycleOwner(), value -> this.emptyFieldValidator(value, R.id.libro_disponibilidad_field, libroViewModel.getDisponibilidadValid()));
        libroViewModel.getPrecio().observe(this.getViewLifecycleOwner(), value -> this.emptyFieldValidator(value, R.id.libro_precio_field, libroViewModel.getPrecioValid()));
        libroViewModel.getAutor().observe(this.getViewLifecycleOwner(), value -> this.emptyFieldValidator(value, R.id.libro_autor_field, libroViewModel.getAutorValid()));
    }


    private void emptyFieldValidator(String value, int fieldId, MutableLiveData<Boolean> validFlag) {
        View view = this.getView();
        TextInputLayout textInputLayout = view.findViewById(fieldId);
        if (Objects.isNull(value)) {
            textInputLayout.setError("");
            textInputLayout.setErrorEnabled(false);
            validFlag.setValue(Boolean.FALSE);
            return;
        }
        if (value.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("No puede ser un campo vacio");
            validFlag.setValue(Boolean.FALSE);
        } else {
            textInputLayout.setError("");
            textInputLayout.setErrorEnabled(false);
            validFlag.setValue(Boolean.TRUE);
        }
    }


}