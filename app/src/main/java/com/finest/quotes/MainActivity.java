package com.finest.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.finest.quotes.adapters.MainGridViewAdapter;
import com.finest.quotes.models.QuotesCategory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private GridView category_gv;
    private ArrayList<QuotesCategory> categoryList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category_gv = (GridView) findViewById(R.id.simpleGridView);
        categoryList.add(new QuotesCategory(R.drawable.ic_general_quote,"General"));
        categoryList.add(new QuotesCategory(R.drawable.ic_attitude, "Attitude"));
        categoryList.add(new QuotesCategory(R.drawable.ic_beauty, "Beauty"));
        categoryList.add(new QuotesCategory(R.drawable.ic_best,"Best"));
        categoryList.add(new QuotesCategory(R.drawable.ic_couple,"Marriage"));
        categoryList.add(new QuotesCategory(R.drawable.ic_medical,"Medical"));
        categoryList.add(new QuotesCategory(R.drawable.ic_men,"Men"));
        categoryList.add(new QuotesCategory(R.drawable.ic_mom,"Mom"));
        categoryList.add(new QuotesCategory(R.drawable.ic_money,"Money"));
        categoryList.add(new QuotesCategory(R.drawable.ic_morning,"Morning"));
        categoryList.add(new QuotesCategory(R.drawable.ic_motivational,"Motivational"));
        categoryList.add(new QuotesCategory(R.drawable.ic_movie,"Movies"));
        categoryList.add(new QuotesCategory(R.drawable.ic_music,"Music"));
        categoryList.add(new QuotesCategory(R.drawable.ic_nature,"Nature"));
        categoryList.add(new QuotesCategory(R.drawable.ic_parenting,"Parenting"));
        categoryList.add(new QuotesCategory(R.drawable.ic_patience,"Patience"));
        categoryList.add(new QuotesCategory(R.drawable.ic_patriotism,"Patriotism"));
        categoryList.add(new QuotesCategory(R.drawable.ic_peace,"Peace"));

        MainGridViewAdapter myAdapter=new MainGridViewAdapter(this,R.layout.main_category_item_gv,categoryList);
        category_gv.setAdapter(myAdapter);

        category_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, QuotesActivity.class);
                switch (position)
                {
                    case 0:
                        intent.putExtra("category","general");
                        break;
                    case 1:
                        intent.putExtra("category","attitude");
                        break;
                    case 2:
                        intent.putExtra("category","beauty");
                        break;
                    case 3:
                        intent.putExtra("category","best");
                        break;
                    case 4:
                        intent.putExtra("category","marriage");
                        break;
                    case 5:
                        intent.putExtra("category","medical");
                        break;
                    case 6:
                        intent.putExtra("category","men");
                        break;
                    case 7:
                        intent.putExtra("category","mom");
                        break;
                    case 8:
                        intent.putExtra("category","money");
                        break;
                    case 9:
                        intent.putExtra("category","morning");
                        break;
                    case 10:
                        intent.putExtra("category","motivational");
                        break;
                    case 11:
                        intent.putExtra("category","movies");
                        break;
                    case 12:
                        intent.putExtra("category","music");
                        break;
                    case 13:
                        intent.putExtra("category","nature");
                        break;
                    case 14:
                        intent.putExtra("category","parenting");
                        break;
                    case 15:
                        intent.putExtra("category","patience");
                        break;
                    case 16:
                        intent.putExtra("category","patriotism");
                        break;
                    case 17:
                        intent.putExtra("category","peace");
                        break;
                }
                startActivity(intent);
            }

        });
    }
}