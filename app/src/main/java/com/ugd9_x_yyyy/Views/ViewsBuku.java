package com.ugd9_x_yyyy.Views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ugd9_x_yyyy.API.BukuAPI;
import com.ugd9_x_yyyy.Adapters.AdapterBuku;
import com.ugd9_x_yyyy.Models.Buku;
import com.ugd9_x_yyyy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class ViewsBuku extends Fragment{

    private RecyclerView recyclerView;
    private AdapterBuku adapter;
    private List<Buku> listBuku;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_views_buku, container, false);

        loadDaftarBuku();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bar_buku, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem searchItem = menu.findItem(R.id.btnSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.btnSearch).setVisible(true);
        menu.findItem(R.id.btnAdd).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnAdd) {
            Bundle data = new Bundle();
            data.putString("status", "tambah");
            TambahEditBuku tambahEditBuku = new TambahEditBuku();
            tambahEditBuku.setArguments(data);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager .beginTransaction()
                    .replace(R.id.frame_view_buku, tambahEditBuku)
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadDaftarBuku(){
        setAdapter();
        getBuku();
    }

    public void setAdapter(){
        getActivity().setTitle("Data Buku");
        /*Buat tampilan untuk adapter jika potrait menampilkan 2 data dalam 1 baris,
        sedangakan untuk landscape 4 data dalam 1 baris*/
    }

    public void getBuku() {
        //Tambahkan tampil buku disini
    }
}