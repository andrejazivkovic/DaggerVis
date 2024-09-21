package com.andrejaziv.di.modules

import dagger.Module
import dagger.Provides

@Module
class FeatureCModule {
    @Provides
    fun provideFeatureCService() = emptySet<Int>()
}