package com.wilvermartinez.rickandmortyapp.services

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wilvermartinez.rickandmortyapp.models.CharacterModel

class CharacterPagingSource(
    private val rickAndMortyHttpService: RickAndMortyHttpService,
    private val query: String?
) : PagingSource<Int, CharacterModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = rickAndMortyHttpService.getCharacters(currentPage, query)

            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.results.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
