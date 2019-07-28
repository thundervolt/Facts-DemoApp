package com.samples.factsdemoapp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.filters.MediumTest
import com.samples.factsdemoapp.data.model.FactDetails
import com.samples.factsdemoapp.data.model.FactsList
import com.samples.factsdemoapp.network.NetworkService
import com.samples.factsdemoapp.ui.viewmodel.FactsListViewModel
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 *
 *
 * @author AkashG
 * @since 28-07-2019.
 */
@RunWith(RobolectricTestRunner::class)
@MediumTest
class FactsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var networkService: NetworkService

    lateinit var applicationMock: Application

    lateinit var factsListViewModel: FactsListViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        applicationMock = RuntimeEnvironment.application
        factsListViewModel = FactsListViewModel(applicationMock)
        networkService = NetworkService.getApiService(applicationMock.applicationContext)
    }

    @Test
    fun testNull() {
        Mockito.`when`(factsListViewModel.getFactsList()).thenAnswer {
            return@thenAnswer null
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<FactsList>
        this.factsListViewModel.factsMutableLiveData.observeForever(observer)

        this.factsListViewModel.getFactsList()

        Assert.assertNotNull(this.factsListViewModel.factsMutableLiveData.value)
    }

    @Test
    fun testFetchFactsListSuccess() {
        Mockito.`when`(factsListViewModel.getFactsList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<FactDetails>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<FactsList>
        this.factsListViewModel.factsMutableLiveData.observeForever(observer)

        this.factsListViewModel.getFactsList()

        Assert.assertNotNull(this.factsListViewModel.factsMutableLiveData.value)
    }
}