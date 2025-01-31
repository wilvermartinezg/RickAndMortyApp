package com.wilvermartinez.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wilvermartinez.rickandmortyapp.models.CharacterModel
import com.wilvermartinez.rickandmortyapp.services.CharacterPagingSource
import com.wilvermartinez.rickandmortyapp.services.RetrofitInstance
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModel : ViewModel() {
    private val searchQuery = MutableStateFlow("")

    val characterList: Flow<PagingData<CharacterModel>> = searchQuery
        .flatMapLatest { query ->
            Pager(
                config = PagingConfig(pageSize = 20, prefetchDistance = 5),
                pagingSourceFactory = { CharacterPagingSource(RetrofitInstance.api, query) }
            ).flow
        }
        .cachedIn(viewModelScope)

    fun searchCharacters(query: String) {
        viewModelScope.launch {
            searchQuery.emit(query)
        }
    }
}
