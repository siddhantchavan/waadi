import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Busadapter extends RecyclerView.Adapter<Busadapter.ViewHolder> {
    @Override
    public Busadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Busadapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {

            super(itemView);
             TextView deponame;
             TextView deponumber;
        }
    }
}
