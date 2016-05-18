package com.wm.remusic.fragmentnet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wm.remusic.R;
import com.wm.remusic.activity.SelectActivity;
import com.wm.remusic.fragment.MoreFragment;
import com.wm.remusic.info.MusicInfo;
import com.wm.remusic.json.SearchSongInfo;
import com.wm.remusic.service.MusicPlayer;
import com.wm.remusic.uitl.IConstants;
import com.wm.remusic.uitl.PreferencesUtility;
import com.wm.remusic.widget.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by wm on 2016/5/18.
 */
public class SearchMusicFragment extends Fragment {

    private MusicAdapter mAdapter;
    private ArrayList<SearchSongInfo> songInfos;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;


    public static SearchMusicFragment newInstance(ArrayList<SearchSongInfo> list){
        SearchMusicFragment fragment = new SearchMusicFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("searchMusic",list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recylerview, container, false);
        if (getArguments() != null) {
            songInfos = getArguments().getParcelableArrayList("searchMusic");
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MusicAdapter(songInfos);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        return view;
    }


    public class MusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        final static int FIRST_ITEM = 0;
        final static int ITEM = 1;
        private ArrayList<SearchSongInfo> mList;

        public MusicAdapter(ArrayList<SearchSongInfo> list) {
//            if (list == null) {
//                throw new IllegalArgumentException("model Data must not be null");
//            }
            mList = list;
        }

        //更新adpter的数据
        public void updateDataSet(ArrayList<SearchSongInfo> list) {
            this.mList = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            if (viewType == FIRST_ITEM)
                return new CommonItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.common_item, viewGroup, false));

            else {
                return new ListItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frament_musci_common_item, viewGroup, false));
            }
        }

        //判断布局类型
        @Override
        public int getItemViewType(int position) {
            return position == FIRST_ITEM ? FIRST_ITEM : ITEM;

        }

        //将数据与界面进行绑定
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            SearchSongInfo model = null;
            if (position > 0) {
                model = mList.get(position - 1);
            }
            if (holder instanceof ListItemViewHolder) {

                ((ListItemViewHolder) holder).mainTitle.setText(model.getTitle());
                ((ListItemViewHolder) holder).title.setText(model.getAuthor());

            } else if (holder instanceof CommonItemViewHolder) {


            }
        }

        @Override
        public int getItemCount() {
            return (null != mList ? mList.size() + 1 : 0);
        }


        public class CommonItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView textView;
            ImageView select;

            CommonItemViewHolder(View view) {
                super(view);
                this.textView = (TextView) view.findViewById(R.id.play_all_number);
                this.select = (ImageView) view.findViewById(R.id.select);
                view.setOnClickListener(this);
            }

            public void onClick(View v) {


            }

        }


        public class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            //ViewHolder
            ImageView moreOverflow, playState;
            TextView mainTitle, title;

            ListItemViewHolder(View view) {
                super(view);
                this.mainTitle = (TextView) view.findViewById(R.id.viewpager_list_toptext);
                this.title = (TextView) view.findViewById(R.id.viewpager_list_bottom_text);
                this.playState = (ImageView) view.findViewById(R.id.play_state);
                this.moreOverflow = (ImageView) view.findViewById(R.id.viewpager_list_button);

                moreOverflow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                view.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {


            }

        }
    }



}
