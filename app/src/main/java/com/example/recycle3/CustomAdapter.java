package com.example.recycle3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycle2.model.Member;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM_HEADER=0;
    private static final int TYPE_ITEM_YES_VIEW=1;
    private static final int TYPE_ITEM_NOT_VIEW=2;
    private static final int TYPE_ITEM_FOOTER=3;

    private Context context;
    private List<Member> members;


    public CustomAdapter(Context context,List<Member> members){
        this.context=context;
        this.members=members;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) return TYPE_ITEM_HEADER;
        if (isFooter(position)) return TYPE_ITEM_FOOTER;
        Member member=members.get(position);
        if (member.isAvailable()) return TYPE_ITEM_YES_VIEW;
        return TYPE_ITEM_NOT_VIEW;
    }
    @Override
    public int getItemCount() { return members.size(); }
    public boolean isHeader(int position){return position==0;}
    public boolean isFooter(int position){return position==members.size()-1;}
    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType==TYPE_ITEM_HEADER){
            View header= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cutom_layout_header, viewGroup,false);
            return new CustomViewYesHolder(header);
        } else if (viewType==TYPE_ITEM_YES_VIEW){
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cutom_layout_yes, viewGroup,false);
            return new CustomViewYesHolder(view);
        } else if (viewType==TYPE_ITEM_NOT_VIEW){
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cutom_layout_not, viewGroup,false);
            return new CustomViewYesHolder(view);
        }
        View footer= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cutom_layout_footer, viewGroup,false);
        return new CustomViewYesHolder(footer);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position)||isFooter(position)) return;
        Member member=members.get(position);


        if (holder instanceof CustomViewYesHolder){
            TextView first_name=((CustomViewYesHolder)holder).first_name;
            TextView last_name=((CustomViewYesHolder)holder).last_name;
            first_name.setText(member.getFirstName());
            last_name.setText(member.getLastName());
        }
        if (holder instanceof CustomViewNotHolder){
            TextView first_name=((CustomViewNotHolder)holder).first_name;
            TextView last_name=((CustomViewNotHolder)holder).last_name;
            first_name.setText("This first name is not available");
            last_name.setText("This last name is not available");
        }

    }

public class CustomViewHeaderHolder extends RecyclerView.ViewHolder{
        public View view;
        public CustomViewHeaderHolder(View v){
            super(v);
            view=v;
        }
}



    public class CustomViewYesHolder extends RecyclerView.ViewHolder{
        public  View view;
        public TextView first_name,last_name;
        public CustomViewYesHolder(View v){
            super(v);
            view=v;
            first_name=view.findViewById(R.id.first_name);
            last_name=view.findViewById(R.id.last_name);
        }
    }
    public class CustomViewNotHolder extends RecyclerView.ViewHolder{
        public  View view;
        public TextView first_name,last_name;
        public CustomViewNotHolder(View v){
            super(v);
            view=v;
            first_name=view.findViewById(R.id.first_name);
            last_name=view.findViewById(R.id.last_name);
        }
    }
    public class CustomViewFooterHolder extends RecyclerView.ViewHolder{
        public View view;
        public CustomViewFooterHolder(View v){
            super(v);
            view=v;
        }
    }
}
