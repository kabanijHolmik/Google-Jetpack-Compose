package com.example.googlecourse2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.googlecourse2.ui.theme.GoogleCourse2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MyApp(windowSizeClass)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
            contentDescription = null)
                      },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.palceholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        )
}

@Composable
fun AlignYourBodyElement(@DrawableRes drawable: Int, @StringRes string: Int){
    Surface(color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Image(painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,)
            Text(text = stringResource(id = string),
                modifier = Modifier.padding(bottom = 8.dp, top = 24.dp),
                style = MaterialTheme.typography.bodyMedium)
        }
    }

}

@Composable
fun AlignYourBodyRow(rowCards: List<RowDataClass>, modifier: Modifier = Modifier){
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier){
            items(rowCards){
                item: RowDataClass ->  AlignYourBodyElement(drawable = item.drawable, string = item.string)
            }
    }
}

@Composable
fun FavoriteCollectionElement(@DrawableRes drawable: Int, @StringRes string: Int, modifier: Modifier = Modifier){
    Surface(shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier) {
        Row(modifier = Modifier
            .width(255.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop)
            Text(text = stringResource(id = string),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(gridCards: List<GridDataClass>,modifier: Modifier = Modifier){
    LazyHorizontalGrid(rows = GridCells.Fixed(2),
        modifier = modifier.height(168.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ){
        items(gridCards){
            item ->  FavoriteCollectionElement(drawable = item.drawable, string = item.string, modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun HomeSection(@StringRes title: Int,
                modifier: Modifier = Modifier,
                content: @Composable () -> Unit){
    Column(modifier) {
        Text(text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp))
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar()
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow(rowCards = SampleData.RowData)
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid(gridCards = SampleData.GridData)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SootheBottomNavigation(modifier: Modifier = Modifier){
    NavigationBar(modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(selected = true,
            onClick = { /*TODO*/ }, icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null)},
            label = {
                Text(text = stringResource(id = R.string.navigation_home))
            })
        NavigationBarItem(selected = false,
            onClick = { /*TODO*/ }, icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)},
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            })
    }
}

@Composable
fun SootheNavigationRail(modifier: Modifier = Modifier){
    NavigationRail(modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()) {
            NavigationRailItem(selected = true, onClick = { /*TODO*/ }, icon = { Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null
            )},
                label = {
                    Text(text = stringResource(id = R.string.navigation_home))
                })
            
            Spacer(modifier = Modifier.height(8.dp))
            
            NavigationRailItem(selected = false, onClick = { /*TODO*/ }, icon = { Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
            )},
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_profile))
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortraitScreen(){
    GoogleCourse2Theme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun HorizontalScreen(){
    GoogleCourse2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                SootheNavigationRail()
                HomeScreen()
            }
        }

    }
}

@Composable
fun MyApp(windowSize: WindowSizeClass){
    when(windowSize.widthSizeClass){
        WindowWidthSizeClass.Compact ->{
            PortraitScreen()
        }
        WindowWidthSizeClass.Medium ->{
            HorizontalScreen()
        }
        WindowWidthSizeClass.Expanded ->{
            HorizontalScreen()
        }
    }
}

@Preview
@Composable
fun SootheNavigationRailPreview(){
    SootheNavigationRail()
}

@Preview(showBackground = true)
@Composable
fun SootheBottomNavigationPreview(){
    SootheBottomNavigation()
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    GoogleCourse2Theme {
        HomeScreen()
    }
}

@Preview
@Composable
fun HomeSectionPreview(){
    GoogleCourse2Theme {
        HomeSection(R.string.align_your_body){
            AlignYourBodyRow(rowCards = SampleData.RowData)
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid(gridCards = SampleData.GridData)
        }
    }
}

@Preview(showBackground = true,
    name = "Light theme")
@Composable
fun FavoriteCollectionsGridPreview(){
    FavoriteCollectionsGrid(gridCards = SampleData.GridData)
}

@Preview(showBackground = true,
    name = "Light theme")
@Composable
fun AlignYourBodyRowPreview(){
    AlignYourBodyRow(rowCards = SampleData.RowData)
}

@Preview(showBackground = true,
    name = "Light theme")
@Composable
fun FavoriteCollectionElementPreview(){
    FavoriteCollectionElement(drawable = R.drawable.plant5, string = R.string.card_title, Modifier.padding(8.dp))
}

@Preview(showBackground = true,
    name = "Light theme")
@Composable
fun AlignYourBodyElementPreview(){
    AlignYourBodyElement(drawable = R.drawable.yoga1, string = R.string.yoga_title)
}


@Preview(showBackground = true,
    name = "Light theme")
@Composable
fun SearchBarPreview(){
    SearchBar()
}