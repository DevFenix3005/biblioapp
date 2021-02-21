package com.lania.biblioapp.componentes.miembro.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lania.biblioapp.R;
import com.lania.biblioapp.componentes.miembro.MiembroActivity;
import com.lania.biblioapp.componentes.miembro.MiembroController;
import com.lania.biblioapp.componentes.miembro.MiembroViewModel;
import com.lania.biblioapp.databinding.FragmentMiembroListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiembroListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiembroListFragment extends Fragment {

    private FragmentMiembroListBinding fragmentMiembroListBinding;

    public MiembroListFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MiembroListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MiembroListFragment newInstance() {
        return new MiembroListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MiembroActivity miembroActivity = (MiembroActivity) getActivity();
        MiembroController miembroController = miembroActivity.getMiembroController();

        MiembroViewModel miembroViewModel = new ViewModelProvider(miembroActivity).get(MiembroViewModel.class);
        MiembroListAdapter miembroListAdapter = miembroViewModel.getMiembroListAdapter();
        miembroListAdapter.setMiembroController(miembroController);
        miembroViewModel.setToolbarSubtitulo("Lista de Miembros");

        fragmentMiembroListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_miembro_list, container, false);
        fragmentMiembroListBinding.setMiembroAdapter(miembroListAdapter);
        fragmentMiembroListBinding.setLifecycleOwner(this);

        View view = fragmentMiembroListBinding.getRoot();
        EditText editText = view.findViewById(R.id.miembro_busquedainput);
        editText.addTextChangedListener(miembroController);

        RecyclerView recyclerView = view.findViewById(R.id.miembro_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        FloatingActionButton floatingActionButton = view.findViewById(R.id.miembro_floatingbutton_go2form);
        floatingActionButton.setOnClickListener(miembroController);

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.fragmentMiembroListBinding = null;
    }
}