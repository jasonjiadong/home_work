package com.example.booksys2.bean
 data class BaseResponse<T>(
    val code: Int,
    val message: String,
    val result: Result<T>,
    val success: Boolean,
    val timestamp: Long
)

data class Result<T>(
    val countId: Any,
    val current: Int,
    val maxLimit: Any,
    val optimizeCountSql: Boolean,
    val orders: List<Any>,
    val pages: Int,
    val records: List<T>,
    val searchCount: Boolean,
    val size: Int,
    val total: Int
)

//{
//    "success": true,
//    "message": "",
//    "code": 200,
//    "result": {
//    "records": [
//    {
//        "bookId": 4,
//        "title": "时间简史",
//        "author": "斯蒂芬·霍金",
//        "publisher": "湖南科学技术出版社",
//        "publicationDate": "2010-04-01",
//        "category": "科学",
//        "price": 45,
//        "stockQuantity": 5,
//        "coverImage": "cover_image4.jpg",
//        "description": "探索宇宙奥秘的科普巨著。",
//        "rating": 5,
//        "createBy": null,
//        "createTime": null,
//        "updateBy": null,
//        "updateTime": null
//    },
//    ],
//    "total": 8,
//    "size": 3,
//    "current": 2,
//    "orders": [],
//    "optimizeCountSql": true,
//    "searchCount": true,
//    "maxLimit": null,
//    "countId": null,
//    "pages": 3
//},
//    "timestamp": 1710473374256
//}