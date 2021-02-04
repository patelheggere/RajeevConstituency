package com.patelheggere.rajeevconstituency.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.format.DateUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.patelheggere.rajeevconstituency.R;
import com.patelheggere.rajeevconstituency.model.BeneficiaryModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Veerendra Patel on 3/6/19.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private final String TAG = "UsersAdapter";
    private Context context;
    private boolean isClicked = false;
    private long nbId=0;
    private DecimalFormat formatter;
    private List<BeneficiaryModel> dataModelList;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private SelectItem mListener;
    private String type;

    public UsersAdapter(Context context, List<BeneficiaryModel> dataList) {
        this.context = context;
        this.dataModelList = dataList;
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time, phone, url, name, desi;
        ImageView imageView, imageViewPhoto;
        private LinearLayout mShareCommentLayout;
        private LinearLayout mLinearLayoutLike, mLinearLayoutComment, mLinearLayoutShare, mLinearLayoutPlace;
        private TextView mTextViewLikeCount, mTextViewShareCount, mTextViewCommentCount, textViewPlace;

        CircleImageView circleImageView;
        MyViewHolder(View view) {
            super(view);
            phone = (TextView)view.findViewById(R.id.textViewPhone);
            name = (TextView)view.findViewById(R.id.textViewName);
            imageView = view.findViewById(R.id.textCall);
            desi = view.findViewById(R.id.desi);
            imageViewPhoto = view.findViewById(R.id.photo);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.beneficiary_row_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            final BeneficiaryModel dataModel = dataModelList.get(position);
            holder.name.setText(dataModel.getName());
            holder.phone.setText(dataModel.getMobile());
            if(dataModel.getDesignation()!=null)
            {
                holder.desi.setText(dataModel.getDesignation());
                holder.desi.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.desi.setVisibility(View.GONE);
            }
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                    phoneIntent.setData(Uri.parse("tel:"+dataModel.getMobile()));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    context.startActivity(phoneIntent);
                }
            });
            if(dataModel.getPhotoURL()!=null)
            {
                Picasso.with(context).load(dataModel.getPhotoURL()).into(holder.imageViewPhoto);
            }
            else if(dataModel.getImageData()!=null)
            {
                byte[] decodedString = Base64.decode(dataModel.getImageData(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                holder.imageViewPhoto.setImageBitmap(decodedByte);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        super.onViewRecycled(holder);
    }


    public void onClickItem(int position) {
        if (mListener != null) {
            mListener.selectedPosition(position);
        }
    }
    public interface SelectItem{
        void selectedPosition(int position);
    }

}

