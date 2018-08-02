package com.namkit.namki.teamnova2.Menu2_Daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.namkit.namki.teamnova2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Daily_Adapter_List extends BaseAdapter {
    /* final static int NAME_ASC = 0;
     final static int TYPE_ASC = 1;*/
    private Boolean INIT = false;
    TextView title;
    TextView category;
    TextView timer;

    Boolean CHECK_STATUS = false; //데이터 삭제시 참거짓 값
    private Context mContext;
    private List<Daily_MainItem> mData;
    private ArrayList<Daily_MainItem> arrlist = new ArrayList<>();

   /* Comparator<Daily_MainItem> nameAsc = new Comparator<Daily_MainItem>() {
        @Override
        public int compare(Daily_MainItem t1, Daily_MainItem t2) {
            return t1.getCategory().compareTo(t2.getCategory());
        }
    };
    Comparator<Daily_MainItem> typeAsc = new Comparator<Daily_MainItem>() {
        @Override
        public int compare(Daily_MainItem t1, Daily_MainItem t2) {
            return t1.getTime().compareTo(t2.getTime());
        }
    };*/

    public Daily_Adapter_List(Context context, List<Daily_MainItem> data) {                                                    //Context를 받아야한다 그래야 리소스 접근가능 이게 없으면 그냥 단순 클래스가 된다
        mContext = context;
        mData = data;
    }

    //아이템의 갯수
    @Override
    public int getCount() {
        return mData.size();
    }

    //해당 포지션에 있는 아이템 리턴해줌
    @Override
    public Object getItem(int i) {                                                     //return 타입이 Object로 모든타입이 리턴가능
        return mData.get(i);                                                    //해당 위치의 데이터를 돌려준다(동작은 내부적으로 첫번째 칸이라고 하면 해당하는것이 올라온다
    }

    //해당 포지션에 있는 아이템의 ID(고유)
    @Override
    public long getItemId(int i) {
        return i;
    }

    //아이템 하나를 표현하는 방법을 정의
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) { //완성된 애를 반환해준다(convertView)만약 같은포지션이면 리턴한 값이 다시 convertView로 들어간다
        View v = convertView; //뷰홀더 사용위해 뷰객체 생성
        if (v == null) {
            //최초 로드할때(새로만든 daily_item 의 그 하나의 리스트를 가져와서 getView가 호출할때 리턴되도록 한다
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ViewHolder holder = new ViewHolder();
            v = inflater.inflate(R.layout.daily_item, null);  // inflater 한 것은 findViewById랑 역할이 같다(보여지는 액티비티가 아닌데 리소스가져오고 싶으면 항상 이렇게)

            v.setTag(holder);
        }
        ViewHolder holder = (ViewHolder)v.getTag();
        holder.title = (TextView) v.findViewById(R.id.title_textview);
        holder.category = (TextView) v.findViewById(R.id.category_textview);
        holder.timer = (TextView) v.findViewById(R.id.time_textview);
        holder.checkBox = (CheckBox) v.findViewById(R.id.checkBox_dailyitem);
        final int checkposition = position;

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mData.get(checkposition).setChecked(isChecked);
                    }
                });
                if (CHECK_STATUS) holder.checkBox.setVisibility(View.VISIBLE);
                else holder.checkBox.setVisibility(View.GONE);
            if (mData.get(position).getTitle() == "") {
                holder.title.setText("[제목] : " + "내용없음");
                holder.category.setText("[분류] : " + mData.get(position).getCategory());
                holder.timer.setText(mData.get(position).getTime());
            } else if (mData.get(position).getCategory() == "" || mData.get(position).getCategory() == null) {
                holder.title.setText("[제목] : " + mData.get(position).getTitle());
                holder.category.setText("[분류] : " + "없음");
                holder.timer.setText(mData.get(position).getTime());
            } else {
                holder.title.setText("[제목] : " + mData.get(position).getTitle());
                holder.category.setText("[분류] : " + mData.get(position).getCategory());
                holder.timer.setText(mData.get(position).getTime());
            }
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_left); //에니메이션 적용
            v.startAnimation(animation); //애니메이션 적용
        return v;
    }
static class ViewHolder{
    TextView title;
    TextView category;
    TextView timer;
    CheckBox checkBox;
}

   /* public void setSort(int sortType) {
        if (sortType == NAME_ASC) {
            Collections.sort(mData, nameAsc);
        } else {
            Collections.sort(mData, typeAsc);
        }
        this.notifyDataSetChanged();
    }*/

    public void searchList(String s, int wall) {
        if (!INIT) {
            arrlist.addAll(mData);
            INIT = true;
        }

        mData.clear();
        if (s.length() == 0) {
            mData.addAll(arrlist);
        } else {
            for (int i = 0; i < arrlist.size(); i++) {
                if (wall == 1) {
                    if (arrlist.get(i).getCategory().contains(s)) {
                        mData.add(arrlist.get(i));
                    }
                } else {
                    if (arrlist.get(i).getTitle().contains(s)) {
                        mData.add(arrlist.get(i));
                    }
                }
            }
            this.notifyDataSetChanged();
        }
    }
    public void setCheckBox(Boolean b) {
        this.CHECK_STATUS = b;
        this.notifyDataSetChanged();
    }
    public void deleteList() {
        ArrayList<Daily_MainItem> temp = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getChecked()) {
                temp.add(mData.get(i));
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            mData.remove(temp.get(i));
            arrlist.remove(temp.get(i));
        }
        this.notifyDataSetChanged();
    }
}