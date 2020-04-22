package com.jarchie.customview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jarchie.customview.R;
import com.jarchie.customview.bean.MainBean;
import com.jarchie.customview.databinding.ItemCardBinding;
import com.jarchie.customview.viewmodel.ItemViewModel;

import java.util.List;

/**
 * 作者：created by Jarchie
 * 时间：2020/4/22 11:07:17
 * 邮箱：jarchie520@gmail.com
 * 说明：主页面列表适配器
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private Context mContext;
    private List<MainBean> mList;

    public MainAdapter(Context context, List<MainBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_card, parent, false);
        return new MainHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        MainBean bean = mList.get(position);
        bean.setPosition(position);
        holder.bindData(bean);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {
        ItemCardBinding binding;

        MainHolder(ItemCardBinding binding) {
            super(binding.mAllLayout);
            this.binding = binding;
        }

        void bindData(MainBean bean) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemViewModel(mContext, bean));
            } else {
                binding.getViewModel().setData(bean);
            }
        }
    }
}
