package com.example.moviesapp.ui.moduleone.fragment

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.base.BaseApplication
import com.example.moviesapp.base.BaseFragment
import com.example.moviesapp.databinding.FragmentMovieListBinding
import com.example.moviesapp.ni.remote.response.Results
import com.example.moviesapp.ui.moduleone.adapters.MoviesAdapter
import com.example.moviesapp.ui.moduleone.fragment.viewmodel.IMovieItemListener
import com.example.moviesapp.ui.moduleone.fragment.viewmodel.IMovieListObserver
import com.example.moviesapp.ui.moduleone.fragment.viewmodel.MovieListViewModel
import com.example.moviesapp.util.constants.Constants
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Darshan Patel
 * Usage: use to display list of movies
 */
class MovieListFragment : BaseFragment<FragmentMovieListBinding, MovieListViewModel>(),
    IMovieListObserver, IMovieItemListener {

    override val viewModel: MovieListViewModel by viewModel()

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_movie_list

    lateinit var movieListBinding: FragmentMovieListBinding
    //for sorting movie list
    var sort: String = Constants.ApiFields.SORT_BY_POPULARITY
    //for pagination
    var page: Int = 1
    lateinit var moviesAdapter: MoviesAdapter
    var moviesList: ArrayList<Results> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieListBinding = this.viewDataBinding!!
        setRecyclerView()
        addPagination()
    }

    override fun onResume() {
        super.onResume()
        //when user resumes to movie list fragment setting the filter which was selected
        //check for internet connection
        //populating the data present if there is
        //if no data present call api
        checkForSelectedFilter()
        if (!BaseApplication.hasNetwork()) {
            showConnectivityAlert()
        } else {
            if (moviesList.size > 0) {
                refreshData(moviesList)
            }else{
                callAPi()
            }
        }
    }

    @SuppressLint("NewApi")
    private fun checkForSelectedFilter() {
        if(sort.equals(Constants.ApiFields.SORT_BY_POPULARITY)){
            txt_filter_popularity.setTextColor(context!!.getColorStateList(R.color.colorAccent))
            txt_filter_rating.setTextColor(context!!.getColorStateList(R.color.colorWhite))
        }else{
            txt_filter_popularity.setTextColor(context!!.getColorStateList(R.color.colorWhite))
            txt_filter_rating.setTextColor(context!!.getColorStateList(R.color.colorAccent))
        }
    }

    private fun setRecyclerView() {
        rv_movies.layoutManager = GridLayoutManager(context, 3)
        moviesAdapter = MoviesAdapter(this)
        rv_movies.adapter = moviesAdapter
    }

    //pagination logic with recyclerview onscroll listener to detect last item of the grid
    private fun addPagination() {
        rv_movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = rv_movies.layoutManager as GridLayoutManager
                    val visibleItemCount = layoutManager.findLastCompletelyVisibleItemPosition() + 1
                    if (visibleItemCount == layoutManager.itemCount) {
                        //Load more data
                        page++
                        callAPi()
                    }
                }
            }
        })
    }

    private fun callAPi() {
        if (BaseApplication.hasNetwork()) {
            viewModel.moviesAPi(sort, page.toString()).observe(viewLifecycleOwner, Observer {
                moviesList.addAll(it.results)
                refreshData(moviesList)
            })
        } else {
            //connect to internet dialog
            showConnectivityAlert()
        }
    }

    private fun showConnectivityAlert() {
        val dialogBuilder = AlertDialog.Builder(activity!!)
        dialogBuilder.setMessage(context!!.resources.getString(R.string.str_connectivity_alert_message))
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton(
                context!!.resources.getString(R.string.str_connectivity_alert_open_setting),
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                    openSetting()
                })
            .setNegativeButton(
                context!!.resources.getString(R.string.str_connectivity_alert_dismiss),
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()

                })
        val alert = dialogBuilder.create()
        alert.setTitle(context!!.resources.getString(R.string.str_connectivity_alert_title))
        alert.show()
    }

    private fun openSetting() {
        try {
            val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun refreshData(list: ArrayList<Results>) {
        moviesAdapter.setMoviesList(list)
    }

    override fun onMovieItemClick(result: Results) {
        val action = MovieListFragmentDirections.openDetailFragment(result)
        navController.navigate(action)
    }

    @SuppressLint("NewApi")
    override fun filterByPopularity() {
        sort = Constants.ApiFields.SORT_BY_POPULARITY
        page = 1
        moviesList.clear()
        checkForSelectedFilter()
        callAPi()
    }

    @SuppressLint("NewApi")
    override fun filterByRating() {
        sort = Constants.ApiFields.SORT_BY_RATING
        page = 1
        moviesList.clear()
        checkForSelectedFilter()
        callAPi()
    }
}