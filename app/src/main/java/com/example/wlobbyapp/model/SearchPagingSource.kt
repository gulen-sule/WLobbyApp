package com.example.wlobbyapp.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wlobbyapp.model.search.multiSearch.MultiSearchResult
import com.example.wlobbyapp.model.service.ApiService

class SearchPagingSource(
    private val text: String,
    private val service: ApiService,
) : PagingSource<Int, MultiSearchResult>() {

    override fun getRefreshKey(state: PagingState<Int, MultiSearchResult>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(-1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MultiSearchResult> {
        val nextPageNumber = params.key ?: 1
        val resultData = ArrayList<MultiSearchResult>()

        if (nextPageNumber == 1) {
            val collectionResponse = service.collectionSearch(query = text, page = 1)
            if (collectionResponse.isSuccessful) {
                val collectionData = collectionResponse.body()
                if (collectionData?.results?.size!! >= 3) {
                    for (i in 0..2) {
                        resultData.add(
                            MultiSearchResult(
                                media_type = "collection",
                                title = collectionData.results?.get(i)?.name,
                                poster_path = collectionData.results?.get(i)?.poster_path,
                                id = collectionData.results?.get(i)?.id
                            )
                        )
                    }
                }
            }
        }

        val response = service.multiSearchPaging(query = text, page = nextPageNumber)
        var nextpageNum: Int? = null
        if (response.isSuccessful) {
            val searchData = response.body()
            searchData?.let {
                resultData.addAll(searchData.results as ArrayList<MultiSearchResult>)

                nextpageNum = searchData.page?.plus(1)

                nextpageNum = if (nextpageNum != null && searchData.total_pages!! >= nextpageNum!!)
                    searchData.page?.plus(1)
                else
                    null

                Log.d("testNumberTAG", nextPageNumber.toString())
                Log.d("testNumberTAG(2)", searchData.page.toString())
            }
        }
        return LoadResult.Page(
            data = resultData,
            prevKey = null,
            nextKey = nextpageNum
        )

    }


}

