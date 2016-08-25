package com.android.app.touristguide.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class RecyclerViewBindingAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /*
    Custom RecyclerView Binding Adapter . It can be used for any type of data

    The constructor takes three parameters
     @param holderLayout store the holderlayout details
      @param variableId store which variable should the data bind in the layout
      @param dataSet store the adapter data
     */
    private final int holderLayout;
    private final int variableId;
    private List<T> dataSet=new ArrayList<>();
    private OnItemClickListener<T> onItemClickListener;
    public void setItemClickListener(OnItemClickListener<T> onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    // setting up the adapter with the holder and the binding variable and dataset
    public RecyclerViewBindingAdapter(int holderLayout,int variableId,List<T> dataSet) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.dataSet = dataSet;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =LayoutInflater.from(parent.getContext()).inflate(holderLayout,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    public void addItem(T item){
        dataSet.add(item);
        notifyDataSetChanged();
    }
    public T getItemAt(int position){
        return dataSet.get(position);
    }
    public void addItemAt(int position,T item){
        dataSet.add(position,item);
        notifyItemInserted(position);
    }
    public void addItems(List<T> items){
        dataSet.addAll(items);
        notifyDataSetChanged();
    }
    public void removeItemAt(int position){
        dataSet.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder itemHolder= (ViewHolder) holder;
        final T item=dataSet.get(position);

        itemHolder.binding.getRoot().setOnClickListener(view -> {
// click event for the root layout
            if(null!=onItemClickListener) {
                onItemClickListener.onItemClick(position, item);
            }
        });

        itemHolder.binding.setVariable(variableId,item);

    }
    private class ViewHolder extends RecyclerView.ViewHolder{
        private final ViewDataBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
    public interface OnItemClickListener<T>{
        void onItemClick(int position, T item);
    }
    public void clearDataSet(){
        dataSet.clear();
    }

}
