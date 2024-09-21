package com.andrejaziv.di.components

import com.andrejaziv.di.annotations.FeatureDScope
import dagger.Component

@Component(dependencies = [FeatureAComponent::class, FeatureBComponent::class])
@FeatureDScope
interface FeatureDComponent