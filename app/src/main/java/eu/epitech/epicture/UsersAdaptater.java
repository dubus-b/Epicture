package eu.epitech.epicture;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eu.epitech.epicture.api.User;

/**
 * Created by oscar on 10/02/2018.
 */

public class UsersAdaptater extends RecyclerView.Adapter<UsersAdaptater.UsersHolder> {

    private ArrayList<User> _users;
    public static int UPLOAD_ACTIVITY = 1;
    public static int ACCOUNTS_MANAGEMENT_ACTIVITY = 2;
    private int _activity_type;

    public OnItemClickListener get_click_listener() {
        return _click_listener;
    }

    public void set_click_listener(OnItemClickListener click_listener) {
        this._click_listener = click_listener;
    }

    private OnItemClickListener _click_listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
        void onInfoClick(int position);
        void onCheckboxClick(int position);
    }

    public UsersAdaptater(ArrayList<User> Users, int activity_type) {
        _users = Users;
        _activity_type = activity_type;
    }


    @Override
    public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_picture_services, parent, false);
        UsersHolder holder = new UsersHolder(view, _click_listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(UsersHolder holder, int position) {
User user = _users.get(position);
        holder._unique_id.setText(String.valueOf(user.get_id()));
        holder._unique_str.setText(user.get_unique_str());
        if (_activity_type == UPLOAD_ACTIVITY) {
            holder._PictureService.setVisibility(View.VISIBLE);
            switch (user.get_platfom()) {
                case "imgur":
                    holder._PictureService.setImageResource(R.drawable.imgur);
                    break;
                default:
                    holder._PictureService.setImageResource(R.drawable.img_not_foud);
                    break;
            }
        }
        else {
            holder._delete_user.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return _users.size();
    }

    public static class UsersHolder extends RecyclerView.ViewHolder {
        public ImageView getPictureService() {
            return _PictureService;
        }

        public void setPictureService(ImageButton pictureService) {
            _PictureService = pictureService;
        }

        public TextView getUnique_str() {
            return _unique_str;
        }

        public void setUnique_str(TextView unique_str) {
            this._unique_str = unique_str;
        }

        public TextView getUnique_id() {
            return _unique_id;
        }

        public void setUnique_id(TextView unique_id) {
            this._unique_id = unique_id;
        }

        public CheckBox getBox() {
            return _box;
        }

        public void setBox(CheckBox box) {
            this._box = box;
        }

        public ImageView _PictureService;
        public TextView _unique_str;
        public TextView _unique_id;
        public CheckBox _box;
        public ImageButton _delete_user;
        public ImageButton _print_user_info;

        public UsersHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            _unique_str = itemView.findViewById(R.id.UserUniqueName);
            _unique_id = itemView.findViewById(R.id.InvisibleUserId);
            _box = itemView.findViewById(R.id.ServiceToUploadBox);
            _PictureService = itemView.findViewById(R.id.ServiceToUpload);
            _delete_user = itemView.findViewById(R.id.delete_user);
            _print_user_info = itemView.findViewById(R.id.user_info);

            _box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onCheckboxClick(getAdapterPosition());
                    }
                }
            });

            _print_user_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onInfoClick(getAdapterPosition());
                    }
                }
            });

            _delete_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
