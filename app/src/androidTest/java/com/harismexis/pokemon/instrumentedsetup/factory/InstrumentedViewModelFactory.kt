package com.harismexis.pokemon.instrumentedsetup.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.pokemon.presentation.detail.viewmodel.ItemDetailVm
import com.harismexis.pokemon.presentation.home.viewmodel.HomeVm
import dagger.Binds
import dagger.Module
import io.mockk.mockk
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

val mockHomeVm: HomeVm = mockk(relaxed = true)
val mockItemDetailVm: ItemDetailVm = mockk(relaxed = true)

fun getMockVmMap(): MutableMap<Class<out ViewModel>, Provider<ViewModel>> {
    val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>> = mutableMapOf()
    viewModels[HomeVm::class.java] = Provider { mockHomeVm }
    viewModels[ItemDetailVm::class.java] = Provider { mockItemDetailVm }
    return viewModels
}

@Singleton
class InstrumentedViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        getMockVmMap()[modelClass]?.get() as T
}

@Module
abstract class InstrumentedViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InstrumentedViewModelFactory)
            : ViewModelProvider.Factory
}