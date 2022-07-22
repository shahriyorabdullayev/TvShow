package com.shahriyor.android_imperative.repository

import com.shahriyor.android_imperative.data.local.dao.TVShowDao
import com.shahriyor.android_imperative.model.TVShow
import com.shahriyor.android_imperative.data.remote.service.TvShowService
import javax.inject.Inject

class TVShowRepository @Inject constructor(
    private val tvShowService: TvShowService,
    private val tvShowDao: TVShowDao,
) {

    /**
     * Retrofit Related
     */

    suspend fun apiTVShowPopular(page: Int) = tvShowService.apiTVShowPopular(page)
    suspend fun apiTVShowDetails(q: Int) = tvShowService.apiTVShowDetails(q)


    /**
     * Room Related
     */

    suspend fun getTvShowsFromDD() = tvShowDao.getTVShowsFromDB()
    suspend fun insertTvShowToDb(tvShow: TVShow) = tvShowDao.insertTVShowToDB(tvShow)
    suspend fun deleteTvShowsFromDb() = tvShowDao.deleteTvShowsFromDB()
}