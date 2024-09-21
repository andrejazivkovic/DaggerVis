package com.andrejaziv.di.components

import com.andrejaziv.di.annotations.FeatureIScope
import dagger.Component

@Component(dependencies = [FeatureFComponent::class])
@FeatureIScope
interface FeatureIComponent