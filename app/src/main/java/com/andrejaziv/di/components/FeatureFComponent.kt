package com.andrejaziv.di.components

import com.andrejaziv.di.annotations.FeatureFScope
import dagger.Component

@Component(dependencies = [FeatureDComponent::class])
@FeatureFScope
interface FeatureFComponent