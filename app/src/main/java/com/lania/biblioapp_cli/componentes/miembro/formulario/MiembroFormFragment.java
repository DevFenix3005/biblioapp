package com.lania.biblioapp_cli.componentes.miembro.formulario;

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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lania.biblioapp_cli.R;
import com.lania.biblioapp_cli.componentes.miembro.MiembroActivity;
import com.lania.biblioapp_cli.componentes.miembro.MiembroController;
import com.lania.biblioapp_cli.componentes.miembro.MiembroViewModel;
import com.lania.biblioapp_cli.databinding.FragmentMiembroFormBinding;
import com.lania.biblioapp_cli.entidades.Miembro;

import java.util.Objects;

public class MiembroFormFragment extends Fragment {

    private FragmentMiembroFormBinding binding;

    public static Fragment newInstance() {
        return new MiembroFormFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MiembroActivity miembroActivity = (MiembroActivity) this.getActivity();
        MiembroController miembroController = miembroActivity.getMiembroController();

        MiembroViewModel miembroViewModel = new ViewModelProvider(miembroActivity).get(MiembroViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_miembro_form, container, false);
        binding.setLifecycleOwner(this);
        binding.setMiembro(miembroViewModel);
        View view = binding.getRoot();

        TextInputEditText birthdayField = view.findViewById(R.id.birthday_miembro_field_true);
        Button registerButton = view.findViewById(R.id.miembro_registrar);

        Miembro miembro2Update = miembroViewModel.getMiembro2Update();

        if (miembro2Update != null) {
            registerButton.setText("Actualizar");
        } else {
            registerButton.setText("Registrar");
        }

        birthdayField.setOnClickListener(miembroController);
        registerButton.setOnClickListener(miembroController);

        createValidations(miembroViewModel);

        return view;
    }


    private void createValidations(MiembroViewModel miembroViewModel) {
        miembroViewModel.getNombre().observe(getViewLifecycleOwner(), value -> this.emptyFieldValidator(value, R.id.nombre_miembro_field, miembroViewModel.getNombreValid()));
        miembroViewModel.getFechaDeNacimiento().observe(getViewLifecycleOwner(), value -> this.emptyFieldValidator(value, R.id.birthday_miembro_field, miembroViewModel.getFechaDeNacimientoValid()));
        miembroViewModel.getDireccion().observe(getViewLifecycleOwner(), value -> this.emptyFieldValidator(value, R.id.direccion_miembro_field, miembroViewModel.getDireccionValid()));
    }

    private void emptyFieldValidator(String value, int fieldId, MutableLiveData<Boolean> validFlag) {
        View view = getView();
        TextInputLayout textInputLayout = view.findViewById(fieldId);
        if (Objects.isNull(value)) {
            textInputLayout.setError("");
            textInputLayout.setErrorEnabled(false);
            validFlag.setValue(Boolean.TRUE);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}