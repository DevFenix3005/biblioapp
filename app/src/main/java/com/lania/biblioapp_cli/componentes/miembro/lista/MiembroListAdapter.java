package com.lania.biblioapp_cli.componentes.miembro.lista;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lania.biblioapp_cli.R;
import com.lania.biblioapp_cli.componentes.miembro.MiembroController;
import com.lania.biblioapp_cli.databinding.ItemviewMiembroBinding;
import com.lania.biblioapp_cli.entidades.Miembro;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.rambler.libs.swipe_layout.SwipeLayout;

@Data
@EqualsAndHashCode(callSuper = true)
public class MiembroListAdapter extends RecyclerView.Adapter<MiembroListAdapter.MiembroItemHolder> {

    private List<Miembro> miembroList;
    private MiembroController miembroController;

    public MiembroListAdapter() {
    }

    @NonNull
    @Override
    public MiembroItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemviewMiembroBinding miemrboItemBinding = DataBindingUtil.inflate(inflater, R.layout.itemview_miembro, parent, false);
        return new MiembroItemHolder(miemrboItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MiembroListAdapter.MiembroItemHolder holder, int position) {
        Miembro miembro = this.miembroList.get(position);
        holder.bind(miembro);
    }

    @Override
    public int getItemCount() {
        return miembroList.size();
    }

    public void add(Miembro miembro) {
        this.miembroList.add(miembro);
        this.notifyItemInserted(this.miembroList.size());
    }

    public void remove(Miembro miembro) {
        int index = this.miembroList.indexOf(miembro);
        this.miembroList.remove(miembro);
        this.notifyItemRemoved(index);
    }

    public void update(List<Miembro> nuevaLista) {
        if (this.miembroList == null) {
            this.miembroList = new ArrayList<>(nuevaLista);
        } else {
            this.miembroList.clear();
            this.miembroList.addAll(nuevaLista);
        }
        this.notifyDataSetChanged();
    }


    public class MiembroItemHolder extends RecyclerView.ViewHolder {

        private final ItemviewMiembroBinding itemviewMiembroBinding;
        private final SwipeLayout swipeLayout;

        public MiembroItemHolder(ItemviewMiembroBinding itemviewMiembroBinding) {
            super(itemviewMiembroBinding.getRoot());
            this.itemviewMiembroBinding = itemviewMiembroBinding;
            this.swipeLayout = this.itemView.findViewById(R.id.item_miembro_swipe);
            this.swipeLayout.setOnSwipeListener(miembroController);

        }

        public void bind(Miembro miembro) {
            this.itemviewMiembroBinding.setMiembro(new MiembroItemViewModel(miembro));
            this.itemviewMiembroBinding.executePendingBindings();
            this.swipeLayout.setTag(miembro);
        }


    }


}
