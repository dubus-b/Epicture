package eu.epitech.epicture;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Louis Giraud on 10/02/2018.
 */

public class CardImageAdapter extends RecyclerView.Adapter<CardImageAdapter.ImageViewHolder> {

    private Context activityContext;
    private ArrayList<String> imageAddrsList;
    private OnItemClickListener imageClickListener;

    public interface OnItemClickListener {
        void onDownloadClick(int position);
        void onFavoriteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.imageClickListener = listener;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public ImageView favIcon;

        public TextView favText;
        public ImageView dlIcon;
        public TextView dlText;

        public ImageViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            image = itemView.findViewById(R.id.imageLoaded);
            favIcon = itemView.findViewById(R.id.favButton);
            favText = itemView.findViewById(R.id.favText);
            dlIcon = itemView.findViewById(R.id.dlButton);
            dlText = itemView.findViewById(R.id.dlText);

            favIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onFavoriteClick(position);
                        }
                    }
                }
            });

            favText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onFavoriteClick(position);
                        }
                    }
                }
            });
        }
    }

    public CardImageAdapter(Context context, ArrayList<String> imageAddrsList) {
        this.activityContext = context;
        this.imageAddrsList = imageAddrsList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_card_image, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view, imageClickListener);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        CardImage currentItem = new CardImage(imageAddrsList.get(position));
        Picasso.with(activityContext).load(currentItem.getUrl()).into(holder.image);
        if (currentItem.getFavorite() == 1) {
            holder.favIcon.setImageResource(R.drawable.ic_favorite_red);
            holder.favText.setTextColor(Color.parseColor("#ffff4444"));
        }
    }

    @Override
    public int getItemCount() {
        return imageAddrsList.size();
    }
}
