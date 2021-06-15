package com.example.wlobbyapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wlobbyapp.data.search.multiSearch.MultiSearchModel
import com.example.wlobbyapp.data.search.multiSearch.Results
import com.example.wlobbyapp.data.service.ApiService

class SearchPagingSource(
    private val text: String,
    private val service: ApiService,
) : PagingSource<Int, Results>() {

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(-1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val nextPageNumber = params.key ?: 1

        val response = service.multiSearchPaging(query = text, page = nextPageNumber)
        val resultData = response.results as ArrayList<Results>

        /*val temp = ArrayList<Results>()
        temp.add(Results(title = "deneme"))
        val tempData = temp.toList()*/
        var nextpageNum:Int?=response.page?.plus(1)

        if(nextpageNum!=null&& response.total_pages!! >=nextpageNum)
            nextpageNum=response.page?.plus(1)
        else
            nextpageNum=null

        Log.d("testNumberTAG", nextPageNumber.toString())
        Log.d("testNumberTAG(2)", response.page.toString())

        return LoadResult.Page(
            data = resultData,
            prevKey = null,
            nextKey =nextpageNum
        )
    }


}

