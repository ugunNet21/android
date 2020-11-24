package com.foodproject.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.foodproject.R;
import com.foodproject.Utils.AndroidUtil;
import com.foodproject.adapter.ExtrasAdapter;
import com.foodproject.model.Extra;

public class ItemDetailsActivity extends AppCompatActivity implements ExtrasAdapter.OnExtraClickListener {

    private static final String TAG = "ItemDetailsActivity";

    private RelativeLayout mBack, mOrder;
    private LinearLayout mPlace;
    private RecyclerView mExtrasRecycler;
    private ExtrasAdapter mAdapter;

    /**
     * Slider
     */
    private ViewPager viewPager;
    private LinearLayout layout_dots;
    private AdapterImageSlider adapterImageSlider;
    private Runnable runnable = null;
    private Handler handler = new Handler();

    private static String[] array_imgs = new String[] {
            "https://flipcar-server.liveonsolutions.com/images/volkswagen1.png",
            "https://flipcar-server.liveonsolutions.com/images/bmw1.png",
            "https://flipcar-server.liveonsolutions.com/images/citroen1.png"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        initComponents();
        setupWidgets();
    }

    private void initComponents() {
        mBack = findViewById(R.id.back);
        mPlace = findViewById(R.id.lnl_restaurant);
        mOrder = findViewById(R.id.lnl_whatsapp);
        mExtrasRecycler = findViewById(R.id.recycler);

        layout_dots = findViewById(R.id.layout_dots);
        viewPager = findViewById(R.id.pager);
        adapterImageSlider = new AdapterImageSlider(this, array_imgs);

        adapterImageSlider.setItems(array_imgs);
        viewPager.setAdapter(adapterImageSlider);

        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        startAutoSlider(adapterImageSlider.getCount());
    }

    private void setupWidgets() {
        Window window = getWindow();
        AndroidUtil.statusBarColorTransparentWithKeyboard(this, window);

        LinearLayoutManager llmPlace = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mExtrasRecycler.setLayoutManager(llmPlace);
        mAdapter = new ExtrasAdapter(this);
        mExtrasRecycler.setAdapter(mAdapter);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemDetailsActivity.this, PlaceActivity.class);
                startActivity(i);
            }
        });

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formattedNumber = ("5514981707923");

                try {
                    Intent sendIntent = new Intent();
                    sendIntent.setPackage("com.whatsapp");
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.ask_for_food_begin) + " Cheeseburger " + getResources().getString(R.string.ask_for_food_extras) + "\n" + "- Queijo e\n- Bacon");
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra("jid", formattedNumber +"@s.whatsapp.net");
                    startActivity(sendIntent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Você não possui o WhatsApp instalado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onExtraClickListener(Extra extra) {
        mAdapter.addExtra(extra.getExtraId());
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 25;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(ContextCompat.getColor(this, R.color.overlay_dark_10), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private static class AdapterImageSlider extends PagerAdapter {

        private Activity act;
        private String[] items;

        // constructor
        private AdapterImageSlider(Activity activity, String[] items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.length;
        }

        public void setItems(String[] items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView imageView = v.findViewById(R.id.image);
            AndroidUtil.displayImageIntoSlider(act, imageView, array_imgs, position);

            (container).addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((RelativeLayout) object);
        }

    }

    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }

}
