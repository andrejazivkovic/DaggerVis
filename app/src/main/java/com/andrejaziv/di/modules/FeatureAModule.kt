package com.andrejaziv.di.modules

import dagger.Module
import dagger.Provides

@Module
class FeatureAModule {
    @Provides
    fun provideFeatureAService() = emptySet<Int>()
}