package com.andrejaziv.di.components

import com.andrejaziv.di.annotations.FeatureBScope
import dagger.Component

@Component(dependencies = [AppComponent::class])
@FeatureBScope
interface FeatureBComponent