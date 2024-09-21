package com.andrejaziv.di.components

import com.andrejaziv.di.annotations.FeatureEScope
import dagger.Component

@Component(dependencies = [FeatureBComponent::class])
@FeatureEScope
interface FeatureEComponent