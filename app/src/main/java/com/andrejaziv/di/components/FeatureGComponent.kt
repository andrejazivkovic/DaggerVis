package com.andrejaziv.di.components

import com.andrejaziv.di.annotations.FeatureGScope
import dagger.Component

@Component(dependencies = [FeatureEComponent::class])
@FeatureGScope
interface FeatureGComponent