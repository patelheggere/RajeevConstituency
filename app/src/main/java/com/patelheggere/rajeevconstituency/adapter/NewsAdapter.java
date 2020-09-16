package com.patelheggere.rajeevconstituency.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.patelheggere.rajeevconstituency.R;
import com.patelheggere.rajeevconstituency.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private static final String TAG = "NewsAdapter";
    private List<NewsModel> messageList;
    private Context mContext;

    public NewsAdapter(Context mContext, List<NewsModel> messageList) {
        this.messageList = messageList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        try {
            NewsModel details = messageList.get(i);
            if (details.getName() != null)
                newsViewHolder.mTextViewTitle.setText(details.getName());
            if (details.getStatus() != null)
                newsViewHolder.mTextViewDesc.setText(details.getStatus());
            if (details.getProfilePic() != null) {
                Picasso.with(mContext).load(details.getProfilePic()).into(newsViewHolder.mIcon);
            }
            if (details.getImage() != null) {
                Picasso.with(mContext).load(details.getImage()).into(newsViewHolder.mImageViewPhoto);
            }
            if(details.getTimeStamp()!=0)
            {
                CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                        Long.parseLong(String.valueOf(details.getTimeStamp())),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
                newsViewHolder.mTextViewDate.setText(timeAgo);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewDate, mTextViewTitle, mTextViewDesc;
        private ImageView mImageViewPhoto;
        private CircleImageView mIcon;
        public NewsViewHolder(@NonNull View mView) {
            super(mView);
            mTextViewDate = mView.findViewById(R.id.textViewDate);
            mTextViewTitle = mView.findViewById(R.id.textViewTitle);
            mTextViewDesc = mView.findViewById(R.id.textViewDescription);
            mImageViewPhoto = mView.findViewById(R.id.imageViewPhoto);
            mIcon = mView.findViewById(R.id.icon);
        }
    }
}
