package com.example.carolinechen.fragment_practice;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {
    private AccountHeader headerResult = null;
    private Drawer result = null;

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultFragment();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final IProfile profile = new ProfileDrawerItem().withName("Trial").withEmail("trial@dcard.com.tw").withIcon(getResources().getDrawable(R.drawable.profile));

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withSelectionListEnabled(false)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        FragmentTransaction ft = fm.beginTransaction();

                        Profile user = new Profile();
                        ft.replace(R.id.MainActivity, user);
                        ft.addToBackStack(null);
                        ft.commit();

                        return false;
                    }
                })
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.Chat).withIcon(R.drawable.chat).withIdentifier(1).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.Today).withIcon(R.drawable.today).withIdentifier(2).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.Friend).withIcon(R.drawable.friend).withIdentifier(3).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.Mail).withIcon(R.drawable.mail).withIdentifier(4).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.Settings).withIcon(R.drawable.settings).withIdentifier(5).withCheckable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        FragmentTransaction ft = fm.beginTransaction();

                        if (drawerItem != null) {
                            switch (drawerItem.getIdentifier()) {
                                case 1:
                                    Chat chat = new Chat();
                                    ft.replace(R.id.MainActivity, chat);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                    break;
                                case 2:
                                    Today today = new Today();
                                    ft.replace(R.id.MainActivity, today);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                    break;
                                case 3:
                                    Friend friend = new Friend();
                                    ft.replace(R.id.MainActivity, friend);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                    break;
                                case 4:
                                    Mail mail = new Mail();
                                    ft.replace(R.id.MainActivity, mail);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                    break;
                                case 5:
                                    Settings settings = new Settings();
                                    ft.replace(R.id.MainActivity, settings);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                    break;
                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void setDefaultFragment() {
        FragmentTransaction ft = fm.beginTransaction();

        Chat fa = new Chat();
        ft.add(R.id.MainActivity, fa);
        ft.commit();
    }

    private void OnBackPressed() {
        if (!fm.getFragments().isEmpty())
            fm.popBackStack();
        super.onBackPressed();
    }
}
