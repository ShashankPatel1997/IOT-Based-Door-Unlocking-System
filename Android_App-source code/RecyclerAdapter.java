package iot.com.iotbaseddoorunlockingsystem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import com.squareup.picasso.Picasso;

import java.util.List;


public  class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    private Context mContext;
    private List<visitors> visitors;
    private OnItemClickListener mListener;

    public RecyclerAdapter(Context context, List<visitors> uploads) {
        mContext = context;
        visitors = uploads;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_model, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        visitors currentVisitors = visitors.get(position);
        holder.nameTextView.setText(currentVisitors.getName());
        holder.descriptionTextView.setText(currentVisitors.getDescription());

        Picasso.with(mContext)
                .load(currentVisitors.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(holder.teacherImageView);
    }

    @Override
    public int getItemCount() {
        return visitors.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            MenuItem.OnMenuItemClickListener{

        public TextView nameTextView,descriptionTextView,dateTextView;
        public ImageView teacherImageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.nameTextView );
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);

            teacherImageView = itemView.findViewById(R.id.teacherImageView);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }



        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();


            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
