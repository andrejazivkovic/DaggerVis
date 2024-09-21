package com.andrejaziv.di.modules

import dagger.Module
import dagger.Provides

@Module
class FeatureBModule {
    @Provides
    fun provideFeatureBService() = emptySet<Int>()
}