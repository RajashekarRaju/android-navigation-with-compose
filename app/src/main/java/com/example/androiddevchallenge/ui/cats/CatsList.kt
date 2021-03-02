package com.example.androiddevchallenge.ui.cats

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PuppyAdoptionRepo
import com.example.androiddevchallenge.model.Cats
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.CatCompanionColor
import com.example.androiddevchallenge.ui.theme.PuppyAdoptionTheme
import com.example.androiddevchallenge.utils.gradientWidth
import com.example.androiddevchallenge.utils.offsetGradientBackground
import dev.chrisbanes.accompanist.insets.statusBarsPadding

// Start of the CatsList UI
@ExperimentalAnimationApi
@Composable
fun CatsList(
    selectedCat: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_puppy_adoption_icon),
                    contentDescription = stringResource(R.string.content_desc_app_icon),
                    modifier = Modifier
                        .size(112.dp)
                        .padding(top = 16.dp)
                        .statusBarsPadding(),
                )
                Text(
                    text = stringResource(id = R.string.app_bar_title),
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .statusBarsPadding()
                )
            }
        }
    ) {
        CatsListBody(selectedCat)
    }
}

// Start of List
@ExperimentalAnimationApi
@Composable
private fun CatsListBody(
    selectedCat: (Int) -> Unit
) {
    // Save the scroll state of cats list
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    val cats: List<Cats> = PuppyAdoptionRepo.getCatsList(context)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(16.dp)
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
@ExperimentalAnimationApi
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
                initiallyVisible = false, visible = true
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

@ExperimentalAnimationApi
@Preview("List Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun ListDarkPreview() {
    PuppyAdoptionTheme(darkTheme = true) {
        CatsList(selectedCat = { })
    }
}

@ExperimentalAnimationApi
@Preview("List Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun ListLightPreview() {
    PuppyAdoptionTheme {
        CatsList(selectedCat = { })
    }
}