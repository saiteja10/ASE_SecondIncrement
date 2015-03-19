package com.example.FrontEnd_PG4;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.FrontEnd_PG4.adapters.CategoryAdapter;
import com.example.FrontEnd_PG4.adapters.ShoppingCartAdapter;
import com.example.FrontEnd_PG4.beans.CategoryList;
import com.example.FrontEnd_PG4.database.DBHelper;


public class ShoppingCartActivity extends ActionBarActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cartactivity);
        listView = (ListView) findViewById(R.id.orderList);
        ActionBar actionBar = getSupportActionBar();
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
        theview.setLayoutParams(layoutParams);
        actionBar.setCustomView(theview);
        DBTask dbTask = new DBTask(this);
        dbTask.execute("");
    }

    class DBTask extends AsyncTask<String, String, String> {
        private Context context;
        private ProgressDialog progressDialog;
        private CategoryList categoryList;

        public DBTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            DBHelper dbHelper = new DBHelper(context);
            categoryList = dbHelper.getItems();
            dbHelper.close();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            updateView(categoryList);
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "", "please wait....", true);
        }
    }

    public void onShoppingBag(View v) {
    }

    public void updateView(CategoryList categoryList) {
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(this, categoryList);
        listView.setAdapter(shoppingCartAdapter);
//        listView.setOnItemClickListener(this);
    }

    public void onPayment(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
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
}
