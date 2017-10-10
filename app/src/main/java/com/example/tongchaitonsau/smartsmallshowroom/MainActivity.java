package com.example.tongchaitonsau.smartsmallshowroom;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import prefs.UserInfo;
import prefs.UserSession;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    private ViewStub stubGrid;
    private List<Product> productList;
    private UserInfo userInfo;
    private UserSession userSession;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfo        = new UserInfo(this);
        userSession     = new UserSession(this);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);
        stubGrid.inflate();
        gridView = (GridView) findViewById(R.id.my_grid);

        gridView.setOnItemClickListener(onitemclick);
        stubGrid.setVisibility(View.VISIBLE);


        getProductList();
        gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
        gridView.setAdapter(gridViewAdapter);


    }

    public List<Product> getProductList(){
        productList = new ArrayList<>();

        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_1","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_2","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_3","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_4","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_5","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_6","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_7","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_8","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_9","","200"));

        return productList;
    }

    AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            //Toast.makeText(getApplicationContext(),productList.get(i).getName(), Toast.LENGTH_SHORT).show();
            Intent goPurchase = new Intent(MainActivity.this,PurchaseActivity.class);
            goPurchase.putExtra("PASS_NAME",productList.get(i).getName());
            startActivity(goPurchase);


        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_update:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_update,null);
                final EditText mPassword = (EditText) mView.findViewById(R.id.password_updated);
                Button mLogin = (Button) mView.findViewById(R.id.button_updated);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                final String password = userInfo.getKeyPassword();

                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mPassword.getText().toString().equals(password)){
                            Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_SHORT).show();
                            mPassword.setText("");
                        }
                    }
                });
                return true;
            case R.id.action_logout:
                //Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();
                userSession.setLoggedin(false);
                userInfo.clearUserInfo();
                Intent logout = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(logout);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
