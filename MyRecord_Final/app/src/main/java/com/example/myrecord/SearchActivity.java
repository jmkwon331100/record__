package com.example.myrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    ListView listview = null ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1) ;
        listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        adapter.add("대파") ;
        adapter.add("계란") ;
        adapter.add("돼지고기 100g") ;
        adapter.add("소고기 100g") ;
        adapter.add("닭고리 100g") ;
        adapter.add("고추장") ;
        adapter.add("된장") ;
        adapter.add("너겟") ;
        adapter.add("참외") ;
        adapter.add("바나나") ;
        adapter.add("잼") ;
        adapter.add("땅콩버터") ;
        adapter.add("아이스크림") ;
        adapter.add("떡") ;
        adapter.add("요거트") ;





        String getString = getIntent().getStringExtra(AddingActivity.USER_ADD);
        adapter.add(AddingActivity.USER_ADD);



        EditText editTextFilter = (EditText)findViewById(R.id.editTextFilter) ;


        editTextFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                if (filterText.length() > 0) {
                    listview.setFilterText(filterText) ;
                } else {
                    listview.clearTextFilter() ;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


        }) ;


        Button floatbtn = findViewById(R.id.floatbtn);
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, AddingActivity.class);
                startActivity(intent);
            }
        });
    }


}