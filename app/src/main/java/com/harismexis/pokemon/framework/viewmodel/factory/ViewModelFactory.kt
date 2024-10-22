package com.harismexis.pokemon.framework.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.pokemon.presentation.detail.viewmodel.ItemDetailVm

import com.harismexis.pokemon.presentation.home.viewmodel.HomeVm

import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
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
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeVm::class)
    internal abstract fun homeViewModel(viewModel: HomeVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailVm::class)
    internal abstract fun itemDetailVm(viewModel: ItemDetailVm): ViewModel
}