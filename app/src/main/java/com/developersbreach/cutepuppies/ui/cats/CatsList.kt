package com.developersbreach.cutepuppies.ui.cats

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersbreach.cutepuppies.R
import com.developersbreach.cutepuppies.data.PuppyRepo
import com.developersbreach.cutepuppies.model.Cats
import com.developersbreach.cutepuppies.ui.theme.CatCompanionColor
import com.developersbreach.cutepuppies.ui.theme.PuppyTheme
import com.developersbreach.cutepuppies.utils.gradientWidth
import com.developersbreach.cutepuppies.utils.offsetGradientBackground


// Start of the CatsList UI
@Composable
fun CatsList(
    selectedCat: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_puppy_icon),
                    contentDescription = stringResource(R.string.content_desc_app_icon),
                    modifier = Modifier
                        .size(88.dp)
                        .padding(top = 16.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_bar_title),
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    ) { paddingValues ->
        CatsListBody(selectedCat, paddingValues)
    }
}

// Start of List
@Composable
private fun CatsListBody(
    selectedCat: (Int) -> Unit,
    paddingValues: PaddingValues
) {
    // Save the scroll state of cats list
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    val cats: List<Cats> = PuppyRepo.getCatsList(context)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(16.dp)
            .padding(paddingValues)
            .fillMaxSize(),
        state = scrollState,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(cats) { cat ->
            CatRow(cat, selectedCat)
        }
    }
}

// Item row for one cat.
@Composable
private fun CatRow(
    cat: Cats,
    selectedCat: (Int) -> Unit
) {
    var arrowExpanded by remember { mutableStateOf(false) }

    Card(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = { selectedCat(cat.catId) }),
        shape = MaterialTheme.shapes.large
    ) {
        val colorList = listOf(cat.catHairColor, CatCompanionColor)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .offsetGradientBackground(colorList, gradientWidth)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            AnimatedVisibility(
                visible = true
            ) {
                Image(
                    painter = painterResource(id = cat.catImage),
                    contentDescription = stringResource(R.string.content_desc_row_cat_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(240.dp)
                        .clip(CircleShape)
                        .border(2.dp, cat.catHairColor, CircleShape),
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = cat.catName,
                style = MaterialTheme.typography.h4,
                color = cat.catHairColor
            )

            Spacer(Modifier.height(4.dp))

            IconButton(onClick = { arrowExpanded = !arrowExpanded }) {
                Icon(
                    painter = painterResource(getIconState(arrowExpanded)),
                    contentDescription = stringResource(R.string.content_desc_expand_cat_row_desc),
                    modifier = Modifier.size(32.dp),
                    tint = cat.catEyeColor
                )
            }

            AnimatedVisibility(arrowExpanded) {
                Text(
                    text = cat.catDescription,
                    style = MaterialTheme.typography.h6,
                    color = cat.catEyeColor,
                    modifier = Modifier.width(240.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// For animation visibility, change icon based on state remembered
// Composable is reused in CatDetails.kt
@Composable
fun getIconState(
    arrowExpanded: Boolean
): Int = if (arrowExpanded) {
    R.drawable.ic_arrow_up
} else {
    R.drawable.ic_arrow_down
}

@Preview("List Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun ListDarkPreview() {
    PuppyTheme(darkTheme = true) {
        CatsList(selectedCat = { })
    }
}

@Preview("List Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun ListLightPreview() {
    PuppyTheme(darkTheme = false) {
        CatsList(selectedCat = { })
    }
}
