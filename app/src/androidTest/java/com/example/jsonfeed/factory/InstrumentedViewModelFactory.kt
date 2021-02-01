package com.example.jsonfeed.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm

import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.mocks.MockProvider

import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

import kotlin.reflect.KClass

@Singleton
class InstrumentedViewModelFactory @Inject constructor(
    val viewModel: HomeVm
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MockProvider.provideMockViewModel() as T
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class InstrumentedViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InstrumentedViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeVm::class)
    internal abstract fun homeViewModel(viewModel: HomeVm): ViewModel

}