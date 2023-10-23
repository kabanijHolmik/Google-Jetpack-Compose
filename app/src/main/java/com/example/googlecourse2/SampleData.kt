package com.example.googlecourse2

object SampleData {
    val RowData = listOf<RowDataClass>(
        RowDataClass(drawable = R.drawable.yoga1, string = R.string.yoga_title),
        RowDataClass(drawable = R.drawable.yoga2, string = R.string.yoga_title),
        RowDataClass(drawable = R.drawable.yoga3, string = R.string.yoga_title),
        RowDataClass(drawable = R.drawable.yoga4, string = R.string.yoga_title),
        RowDataClass(drawable = R.drawable.yoga5, string = R.string.yoga_title),
        RowDataClass(drawable = R.drawable.yoga6, string = R.string.yoga_title),
        RowDataClass(drawable = R.drawable.yoga7, string = R.string.yoga_title)
    )

    val GridData = listOf<GridDataClass>(
        GridDataClass(drawable = R.drawable.forest, string = R.string.card_title),
        GridDataClass(drawable = R.drawable.tundra, string = R.string.card_title),
        GridDataClass(drawable = R.drawable.sand, string = R.string.card_title),
        GridDataClass(drawable = R.drawable.rock, string = R.string.card_title),
        GridDataClass(drawable = R.drawable.tr_forest, string = R.string.card_title),
        GridDataClass(drawable = R.drawable.plant5, string = R.string.card_title)
    )
}