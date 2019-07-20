package com.samples.factsdemoapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.samples.factsdemoapp.R
import com.samples.factsdemoapp.helpers.ListState
import com.samples.factsdemoapp.ui.adapter.FactsListAdapter
import com.samples.factsdemoapp.ui.viewmodel.FactsListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var factsListViewModel: FactsListViewModel
    private lateinit var factsListAdapter: FactsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        factsListViewModel = ViewModelProviders.of(this).get(FactsListViewModel::class.java)

        initAdapter()
        initState()

        factsListViewModel.getFactsList().observe(this, Observer { factsListResponse ->
            factsListAdapter.updateFactsList(factsListResponse.factsList)
            supportActionBar?.title = factsListResponse.title
        })
    }

    /**
     * Initialise listView & adapter
     */
    private fun initAdapter() {
        factsListAdapter = FactsListAdapter()
        rvFactsList.layoutManager = LinearLayoutManager(this)
        rvFactsList.setHasFixedSize(true)
        rvFactsList.adapter = factsListAdapter
    }

    /**
     * Initialise list ui state
     */
    private fun initState() {
        txt_error.setOnClickListener { factsListViewModel.getFactsList() }
        factsListViewModel.stateMutableLiveData.observe(this, Observer { listState ->
            progress_bar.visibility =
                if (factsListViewModel.listIsEmpty() && listState == ListState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility =
                if (factsListViewModel.listIsEmpty() && listState == ListState.ERROR) {
                    rvFactsList.visibility = View.GONE
                    View.VISIBLE
                } else {
                    rvFactsList.visibility = View.VISIBLE
                    View.GONE
                }
        })
    }
}
