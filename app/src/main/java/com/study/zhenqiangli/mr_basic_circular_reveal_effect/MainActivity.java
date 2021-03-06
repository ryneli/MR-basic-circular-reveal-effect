package com.study.zhenqiangli.mr_basic_circular_reveal_effect;

import android.animation.Animator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static String title = "Fairy Tale";
    static String content = "The flowers are flooding the garden with longing and ballads.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_content);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerView.Adapter<ViewHolder>(){

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.list_item, parent, false));
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.titleTextView.setText(title);
                holder.contentTextView.setText(content);
            }

            @Override
            public int getItemCount() {
                return 20;
            }
        });
    }

    private static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        TextView contentTextView;

        boolean flag = false;

        private ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            contentTextView = (TextView) itemView.findViewById((R.id.content));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int finalRadius = (int)Math.hypot(v.getWidth()/2, v.getHeight()/2);
            if (flag == false) {
                Animator anim = ViewAnimationUtils
                        .createCircularReveal(v, v.getWidth()/2, v.getHeight()/2, 0, finalRadius);
                v.setBackgroundColor(v.getContext().getColor(R.color.green));
                anim.start();
                flag = true;
            } else {
                v.setBackgroundColor(v.getContext().getColor(R.color.white));
                flag = false;
            }
        }
    }
}
