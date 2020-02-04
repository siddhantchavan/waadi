//package com.example.siddhant.loginui;
//
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//public class Picnicadapter extends RecyclerView.Adapter<Picnicadapter.ViewHolder> implements Filterable {
//    private newpicnic[]dDataset;
//    private Picnicadapter.OnItemClickListner mylistner;
//    private List<newpicnic> examplelistfull;
//
//    public interface OnItemClickListner{
//        void onButtonClick(int position);
//    }
//    public void setOnClickListnerItem(Picnicadapter.OnItemClickListner listner){
//        this.mylistner=listner;
//    }
//
//
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView placename;
//
//        public ViewHolder(View itemView , final Picnicadapter.OnItemClickListner listner) {
//
//            super(itemView);
//            placename=(TextView)itemView.findViewById(R.id.placename);
//        }
//    }
//    public Picnicadapter(newpicnic[] mypicnicDataset){
//        this.dDataset=mypicnicDataset;
//        examplelistfull= Arrays.asList(mypicnicDataset);
//    }
//
//    @Override
//    public Picnicadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view=inflater.inflate(R.layout.picnic_list_item,parent,false);
//        return new Picnicadapter.ViewHolder(view , mylistner);
//    }
//
//    @Override
//    public void onBindViewHolder(Picnicadapter.ViewHolder holder, int position) {
//        holder.placename.setText(dDataset[position].getPlaceName());
//        holder.placename.setTextColor(Color.BLACK);
//
//
//
//
//
//    }
//    @Override
//    public int getItemCount() {
//        return dDataset.length;
//    }
//
//    @Override
//    public Filter getFilter() {
//        return exampleFilter;
//    }
//    private Filter exampleFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            List<newpicnic> filteredList = new ArrayList<>();
//            if(charSequence ==null|| charSequence.length()==0){
//                filteredList.addAll(examplelistfull);
//            }
//            else {
//                String filterPattern = charSequence.toString().toLowerCase().trim();
//                for (newpicnic item:examplelistfull){
//                    if(item.getPlaceName().toLowerCase().contains(filterPattern)){
//                        filteredList.add(item);
//
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//            return  results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            dDataset=null;
//            List<newpicnic> lst=new ArrayList<newpicnic>();
////         lst.addAll((List)filterResults.values);
//            dDataset=new newpicnic[lst.size()];
//            dDataset=lst.toArray(dDataset);
//            notifyDataSetChanged();
//        }
//    };
//}
