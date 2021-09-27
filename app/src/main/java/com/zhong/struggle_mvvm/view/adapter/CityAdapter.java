package com.zhong.struggle_mvvm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.bean.FirstNode;
import com.zhong.struggle_mvvm.bean.SecondNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/9/24 9:37
 * @Description TODO
 */
public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Object> beans;
    private final Context context;
    private final int PROVINCE = 0x9;//省
    private final int CITY = 0x10;//城市
    private final int DISTRICT = 0x11;//区县
    private final List<Object> expansions = new ArrayList<>();

    public CityAdapter(Context context, List<FirstNode> beans) {
        this.context = context;
        this.beans = new ArrayList<>(beans);

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = viewType == PROVINCE
                ? R.layout.item_province
                : viewType == CITY
                ? R.layout.item_city
                : R.layout.item_district_county;

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        return viewType == PROVINCE
                ? new ProvinceViewHolder(view)
                : viewType == CITY
                ? new CityViewHolder(view)
                : new DistrictViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProvinceViewHolder) {
            //todo 一级列表
            FirstNode bean = (FirstNode) beans.get(position);
            {
                ProvinceViewHolder hd = (ProvinceViewHolder) holder;
                hd.tvProvince.setText(bean.getName());
                hd.tvProvince.setOnClickListener(v -> {
                    int adapterPosition = holder.getAdapterPosition();
                    for (Object o : expansions) {
                        if (beans.indexOf(o) != adapterPosition) {
                            if (o instanceof SecondNode) {
                                handSecondFolded(beans.indexOf(o), true);
                            }
                        }
                    }
                    for (Object o : expansions) {
                        if (beans.indexOf(o) != adapterPosition) {
                            if (o instanceof FirstNode) {
                                handFirstFolded(beans.indexOf(o), true);
                            }
                        }
                    }
                    handFirstFolded(adapterPosition, expansions.contains(beans.get(holder.getAdapterPosition())));
                });
            }
        } else if (holder instanceof CityViewHolder) {
            //todo 二级列表
            SecondNode bean = (SecondNode) beans.get(position);
            {
                CityViewHolder cityViewHolder = (CityViewHolder) holder;
                cityViewHolder.tvCity.setText(bean.getName());
                cityViewHolder.tvCity.setOnClickListener(v -> {
                    int adapterPosition = holder.getAdapterPosition();
                    Iterator<Object> iterator = expansions.iterator();
                    while (iterator.hasNext()) {
                        Object o = iterator.next();
                        if (beans.indexOf(o) != adapterPosition && o instanceof SecondNode) {
                            handSecondFolded(beans.indexOf(o), true);
                        }
                    }
                    handSecondFolded(adapterPosition, expansions.contains(beans.get(holder.getAdapterPosition())));
                });
            }
        } else {
            //todo 三级列表
            String name = (String) beans.get(position);
            {
                DistrictViewHolder cityViewHolder = (DistrictViewHolder) holder;
                cityViewHolder.tvDistrictCounty.setText(name);
                cityViewHolder.tvDistrictCounty.setOnClickListener(v -> Toast.makeText(context, name, Toast.LENGTH_SHORT).show());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object = beans.get(position);
        if (object instanceof FirstNode) {
            return PROVINCE;
        } else if (object instanceof SecondNode) {
            return CITY;
        } else {
            return DISTRICT;
        }
    }

    @Override
    public int getItemCount() {
        return beans != null ? beans.size() : 0;
    }

    /**
     * 处理一级折叠/收起
     *
     * @param position
     */
    private void handFirstFolded(int position, boolean isFolded) {
        FirstNode item = (FirstNode) beans.get(position);
        item.setFolded(isFolded);

        List<SecondNode> districts = new ArrayList<>();
        for (SecondNode secondNode : item.getCity()) {
            districts.add(secondNode);
        }
        if (item.isFolded()) {
            expansions.remove(item);
            beans.removeAll(districts);
            notifyItemRangeRemoved(position + 1, item.getCity().size());
        } else {
            expansions.add(item);
            beans.addAll(position + 1, districts);
            notifyItemRangeInserted(position + 1, item.getCity().size());
        }
    }

    /**
     * 处理二级折叠/收起
     *
     * @param position
     */
    private void handSecondFolded(int position, boolean isFolded) {
        SecondNode item = (SecondNode) beans.get(position);
        item.setFolded(isFolded);

        List<String> districts = new ArrayList<>();
        for (String name : item.getArea()) {
            districts.add(name);
        }
        if (item.isFolded()) {
            expansions.remove(item);
            beans.removeAll(districts);
            notifyItemRangeRemoved(position + 1, item.getArea().size());
        } else {
            expansions.add(item);
            beans.addAll(position + 1, districts);
            notifyItemRangeInserted(position + 1, item.getArea().size());
        }
    }

    /******省ViewHolder*****/
    public class ProvinceViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProvince;

        public ProvinceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProvince = itemView.findViewById(R.id.tvProvince);
        }
    }

    /******城市ViewHolder*****/
    public class CityViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCity;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCity = itemView.findViewById(R.id.tvCity);
        }
    }

    /*****区县ViewHolder*****/
    public class DistrictViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvDistrictCounty;

        public DistrictViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDistrictCounty = itemView.findViewById(R.id.tvDistrictCounty);
        }
    }
}
