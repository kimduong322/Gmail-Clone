package com.duongdk.hust.it4785.gmailclone

data class EmailModel(
    var sender: String,
    var title: String,
    var content: String,
    var timestamp: Long
)