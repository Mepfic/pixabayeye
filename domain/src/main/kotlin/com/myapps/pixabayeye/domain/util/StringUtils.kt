package com.myapps.pixabayeye.domain.util

fun String.tagsToList() : List<String> = split(", ").toList()