package com.example.novelapp.book.view.popupWindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.novelapp.R;
import com.example.novelapp.book.model.Book;

import java.util.List;



public class ContentPopup extends BasePopupWindow {
    private RecyclerView mRecyclerView;
    private LinearLayout mLinearLayout;
    private Book mBook;

    private OnContentSelectedListener mListener;

    @Override
    protected View createConvertView() {
        return LayoutInflater.from(mContext)
                .inflate(R.layout.popup_content_layout, null);
    }


    public interface OnContentSelectedListener {
        void OnContentClicked(int paraIndex);
    }

    public void setOnContentClicked(OnContentSelectedListener listener) {
        mListener = listener;
    }

    public ContentPopup(Context context, Book book) {
        super(context);
        mBook = book;
        mLinearLayout = (LinearLayout) mConvertView.findViewById(R.id.pop_content_linear_layout);
        mRecyclerView = (RecyclerView) mConvertView.findViewById(R.id.pop_contents_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new ContentsAdapter(mBook.getBookContents()));

    }


    private class ContentsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private int mPosition;

        public ContentsHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
            itemView.setOnClickListener(this);
        }

        public void bind(String content, int position) {
            mPosition = position;
            mTextView.setText(content);
        }

        @Override
        public void onClick(View v) {

            if (mListener != null)
                mListener.OnContentClicked(mBook.getContentParaIndexs().get(mPosition));

        }

    }

    private class ContentsAdapter extends RecyclerView.Adapter<ContentsHolder> {
        private List<String> mBookContents;

        public ContentsAdapter(List<String> bookContents) {
            mBookContents = bookContents;
        }


        @Override
        public ContentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ContentsHolder(view);
        }

        @Override
        public void onBindViewHolder(ContentsHolder holder, int position) {
            holder.bind(mBookContents.get(position), position);

        }

        @Override
        public int getItemCount() {
            return mBookContents.size();
        }
    }


    public void setBackgroundColor(int color) {
        mLinearLayout.setBackgroundColor(color);

    }


}
