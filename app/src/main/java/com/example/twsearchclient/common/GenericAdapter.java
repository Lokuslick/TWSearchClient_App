package com.example.twsearchclient.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenericAdapter<T extends GenericAdapterEntity> extends RecyclerView.Adapter<GenericAdapter<T>.ViewHolder> {

    private List<T> genericAdapterList;
    private int row;
    private int bindingObject;
    private Context context;


    public GenericAdapter(List<T> genericAdapterList, int row, int bindingObject, Context ctx) {
        this.genericAdapterList = genericAdapterList;
        this.row = row;
        this.bindingObject = bindingObject;
        this.context = ctx;
    }

    @NonNull
    @Override
    public GenericAdapter<T>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), this.row, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        T genericModel = genericAdapterList.get(position);
        genericModel.setViewPosition(position);
        holder.bind(genericModel);

    }


    @Override
    public int getItemCount() {
        return genericAdapterList.size();
    }

    public void removeItem(int position) {
        genericAdapterList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(T t, int position) {
        genericAdapterList.add(position, t);
        notifyItemInserted(position);
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding viewDataBinding;

        private ViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }


        private void bind(Object obj) {
            viewDataBinding.setVariable(bindingObject, obj);
            viewDataBinding.executePendingBindings();
        }

    }

    public void setAdapterList(List<T> genericAdapterList) {
        this.genericAdapterList = genericAdapterList;
        this.notifyDataSetChanged();
    }

    public List<T> getAdapterList() {
        return this.genericAdapterList;
    }

}

