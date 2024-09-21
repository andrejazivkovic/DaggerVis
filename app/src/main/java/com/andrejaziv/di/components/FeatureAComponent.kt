package com.andrejaziv.di.components

import com.andrejaziv.di.annotations.FeatureAScope
import dagger.Component


@Component(dependencies = [AppComponent::class])
@FeatureAScope
interface FeatureAComponent