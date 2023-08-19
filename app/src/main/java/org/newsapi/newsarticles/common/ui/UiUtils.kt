package org.newsapi.newsarticles.common.ui

import org.jsoup.Jsoup
import javax.inject.Inject

class UiUtils @Inject constructor() {

    fun parseHtml(bodyText: String?): String {
        return bodyText?.let { Jsoup.parse(it).text() }.toString()
    }


    fun checkNullableApiResponse(apiResponseField: String?): String {
        return if (apiResponseField == null) {
            "-"
        } else if (apiResponseField == "null") {
            "-"
        } else {
            apiResponseField
        }
    }


}