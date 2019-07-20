package com.samples.factsdemoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samples.factsdemoapp.data.model.FactsList
import com.samples.factsdemoapp.helpers.ListState
import com.samples.factsdemoapp.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * FactsListViewModel
 *
 * @author AkashG
 * @since 19-07-2019.
 */
class FactsListViewModel : ViewModel() {

    private val networkService = NetworkService.getApiService()
    private val compositeDisposable = CompositeDisposable()
    var factsMutableLiveData: MutableLiveData<FactsList> = MutableLiveData()
    var stateMutableLiveData: MutableLiveData<ListState> = MutableLiveData()

    /**
     * Fetch facts list api call
     */
    fun getFactsList(): LiveData<FactsList> {
        updateState(ListState.LOADING)
        compositeDisposable.add(
            networkService.getFactsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        updateState(ListState.DONE)
                        factsMutableLiveData.postValue(response)
                    },
                    {
                        updateState(ListState.ERROR)
                    }
                )
        )
        return factsMutableLiveData
    }

    /**
     * update list ui state
     */
    fun updateState(state: ListState) {
        stateMutableLiveData.postValue(state)
    }

    fun listIsEmpty(): Boolean {
        return factsMutableLiveData.value?.factsList?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}

