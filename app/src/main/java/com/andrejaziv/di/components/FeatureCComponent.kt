package com.andrejaziv.di.components

import com.andrejaziv.di.annotations.FeatureCScope
import dagger.Component

@Component(dependencies = [FeatureAComponent::class])
@FeatureCScope
interface FeatureCComponent