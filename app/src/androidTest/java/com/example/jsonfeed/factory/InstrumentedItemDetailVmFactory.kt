package com.example.jsonfeed.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm

import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.mocks.MockProvider2

import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

import javax.inject.Inject
import javax.inject.Singleton

import kotlin.reflect.KClass

@Singleton
class InstrumentedItemDetailVmFactory @Inject constructor(
    val viewModel: ItemDetailVm
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MockProvider2.provideMockViewModel() as T
}

//@Target(
//    AnnotationTarget.FUNCTION,
//    AnnotationTarget.PROPERTY_GETTER,
//    AnnotationTarget.PROPERTY_SETTER
//)
//@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
//@MapKey
//internal annotation class ViewModelKey2(val value: KClass<out ViewModel>)

@Module
abstract class InstrumentedItemDetailVmModule {

//    @Binds
//    internal abstract fun bindViewModelFactory2(factory: InstrumentedItemDetailVmFactory):
//            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailVm::class)
    internal abstract fun itemDetailModel(viewModel: ItemDetailVm): ViewModel

}