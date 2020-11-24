package com.ubp.student.kumpulanhaditsshahih.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.ubp.student.kumpulanhaditsshahih.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SumberActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumber);
        ButterKnife.bind(this);
        setTitle("Sumber");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String text = "Izin terbuka untuk menyebarluaskan dalam rangka da’wah<br>Sumber konten dari: <a href='http://app.lidwa.com/'>http://app.lidwa.com/</a>";
        tvText.setText(Html.fromHtml(text));
        tvText.setClickable(true);
        tvText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
