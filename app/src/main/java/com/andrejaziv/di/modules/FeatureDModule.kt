package com.andrejaziv.di.modules

import dagger.Module
import dagger.Provides

@Module
class FeatureDModule {
    @Provides
    fun provideFeatureDService() = emptySet<Int>()
}