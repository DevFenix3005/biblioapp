package com.lania.biblioapp.componentes.libros.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lania.biblioapp.R;
import com.lania.biblioapp.componentes.libros.LibroActivity;
import com.lania.biblioapp.componentes.libros.LibroController;
import com.lania.biblioapp.componentes.libros.LibroViewModel;
import com.lania.biblioapp.databinding.FragmentLibroListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibroListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibroListFragment extends Fragment {

    private FragmentLibroListBinding binding;

    public LibroListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LibroListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibroListFragment newInstance() {
        return new LibroListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        LibroActivity libroActivity = (LibroActivity) getActivity();
        LibroViewModel libroViewModel = new ViewModelProvider(libroActivity).get(LibroViewModel.class);
        libroViewModel.getToolbarsubtitle().setValue("Lista");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LibroActivity libroActivity = (LibroActivity) getActivity();
        LibroController libroController = libroActivity.getLibroController();
        LibroViewModel libroViewModel = new ViewModelProvider(libroActivity).get(LibroViewModel.class);

        LibrosListAdapter librosListAdapter = libroViewModel.getLibroAdapter();
        librosListAdapter.setLibroController(libroController);


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_libro_list, container, false);
        binding.setLibroAdapter(librosListAdapter);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        RecyclerView recyclerView = view.findViewById(R.id.libro_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        FloatingActionButton floatingActionButton = view.findViewById(R.id.libro_floatingbutton_go2form);
        floatingActionButton.setOnClickListener(libroController);

        return view;
    }
}