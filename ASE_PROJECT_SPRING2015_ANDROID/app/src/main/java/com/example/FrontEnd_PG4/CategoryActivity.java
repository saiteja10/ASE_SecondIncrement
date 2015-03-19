package com.example.FrontEnd_PG4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.FrontEnd_PG4.adapters.CategoryAdapter;
import com.example.FrontEnd_PG4.beans.Category;
import com.example.FrontEnd_PG4.beans.CategoryList;
import com.example.FrontEnd_PG4.request.CategoryRequest;
import com.example.FrontEnd_PG4.request.JsonHandler;
import com.example.FrontEnd_PG4.util.CountHolder;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryActivity extends ActionBarActivity implements JsonHandler, AdapterView.OnItemClickListener {
    private ListView listView;
    private CategoryList categoryList;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);
        listView = (ListView) findViewById(R.id.itemList);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setCustomView(setImageText(actionBar, CountHolder.getCount(this)));
        LayoutInflater li = LayoutInflater.from(this);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#343635")));
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.END
                | Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin = 40;
        View theview = li.inflate(R.layout.img, null);
        TextView textView = (TextView) theview.findViewById(R.id.myImageViewText);
        textView.setText("0");
        theview.setLayoutParams(layoutParams);
        actionBar.setCustomView(theview);
        EditText editText = (EditText) findViewById(R.id.searchTxt);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (categoryAdapter != null) {
                    categoryAdapter.getFilter().filter(s);
                }
            }
        });
        CategoryRequest categoryRequest = new CategoryRequest(this, this);
        categoryRequest.execute();
    }

    /*private View setImageText(ActionBar actionBar, int count) {
        LayoutInflater li = LayoutInflater.from(this);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#343635")));
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.END
                | Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin = 40;
        View theview = li.inflate(R.layout.img, null);
        TextView textView = (TextView) theview.findViewById(R.id.myImageViewText);
        textView.setText(count + "");
        theview.setLayoutParams(layoutParams);
        return theview;
    }

    @Override
    protected void onResume() {
        getSupportActionBar().setCustomView(setImageText(getSupportActionBar(), CountHolder.getCount(this)));
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void parseJson(String jsonResult) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONArray array = jsonObject.getJSONArray("categoryBeans");
            categoryList = new CategoryList();
            for (int i = 0; i < array.length(); i++) {
                JSONObject categoryBean = array.getJSONObject(i);
                Category category = new Category();
                category.setDescription(categoryBean.getString("description"));
                category.setName(categoryBean.getString("name"));
                category.setId(categoryBean.getLong("id"));
                categoryList.getCategories().add(category);
            }
            categoryAdapter = new CategoryAdapter(this, categoryList);
            listView.setAdapter(categoryAdapter);
            listView.setOnItemClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onShoppingBag(View v) {
        try {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, SubCategoryActivity.class);
        intent.putExtra("categoryId", categoryList.getCategories().get(position).getId());
        startActivity(intent);
    }
}
