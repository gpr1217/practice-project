package com.practiceex.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.practiceex.R;

/**
 * Created by Pratyu on 12/15/2017.
 */

public class CustomMenuActivity extends AppCompatActivity {

    private EditText search_action_bar;
    private MenuItem action_search,menu_overflow;
    private boolean showMenu = false;
    private boolean showSearch = false;


    public void showMenus(boolean search, boolean menu){
        this.showSearch = search;
        this.showMenu = menu;
    }

    public void showMenus(boolean menu){
        this.showMenu = menu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar,menu);
        action_search = menu.findItem(R.id.action_search);
        menu_overflow = menu.findItem(R.id.menu_overflow);
        if (showMenu){
            if(showSearch){
                action_search.setVisible(true);
            }else{
                action_search.setVisible(false);
            }
            menu_overflow.setVisible(true);
        }else{
            action_search.setVisible(false);
            menu_overflow.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                handleMenuSearch();
                break;
            case R.id.action_settings:
                Toast.makeText(this,"Action Settings",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_logout:
                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
                finish();
                break;
            case android.R.id.home:
                //Toast.makeText(this,"Action HOME",Toast.LENGTH_LONG).show();
                NavUtils.navigateUpFromSameTask(this);
                break;
            default:
                break;
        }
        return true;
    }

    private boolean isSearchOpened = false;

    public void handleMenuSearch(){

        ActionBar action = getSupportActionBar();
        if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(search_action_bar.getWindowToken(), 0);

            //add the search icon in the action bar
            action_search.setIcon(getResources().getDrawable(R.drawable.ic_action_search));

            isSearchOpened = false;
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            search_action_bar = (EditText)action.getCustomView().findViewById(R.id.search_action_bar); //the text editor

            //this is a listener to do a search when the user clicks on search button
            search_action_bar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        //doSearch();
                        return true;
                    }
                    return false;
                }
            });


            search_action_bar.requestFocus();

            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(search_action_bar, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            action_search.setIcon(getResources().getDrawable(R.drawable.ic_action_cancel));

            isSearchOpened = true;
        }
    }
}
