package com.intermedia.challenge.di

import com.intermedia.challenge.data.repositories.CharactersRepository
import com.intermedia.challenge.data.repositories.EventRepository
import com.intermedia.challenge.ui.characters.CharactersViewModel
import com.intermedia.challenge.ui.events.EventsViewmodel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {

    viewModel { CharactersViewModel(get()) }
    viewModel { EventsViewmodel(get()) }

    single { CharactersRepository(get()) }
    single { EventRepository(get()) }
}