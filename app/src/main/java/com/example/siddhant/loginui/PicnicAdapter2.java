import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.siddhant.loginui.R;
import com.example.siddhant.loginui.newpicnic2;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicnicAdapter2 extends RecyclerView.Adapter<PicnicAdapter2.MyViewHolder> {
  Context context;
  ArrayList<newpicnic2> newpicnic2;
  public PicnicAdapter2(Context c,ArrayList<com.example.siddhant.loginui.newpicnic2>n){
      context=c;
      newpicnic2=n;
  }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.picnic_list_item2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.spotn.setText(newpicnic2.get(position).getSpotname());
    }

    @Override
    public int getItemCount() {
        return newpicnic2.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView spotn;
        public MyViewHolder( View itemView) {
            super(itemView);
            spotn=itemView.findViewById(R.id.placename2);
        }
    }
}
