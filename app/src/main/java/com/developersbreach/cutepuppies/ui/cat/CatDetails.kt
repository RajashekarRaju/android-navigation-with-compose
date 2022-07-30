package com.developersbreach.cutepuppies.ui.cat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
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
import com.developersbreach.cutepuppies.ui.cats.getIconState
import com.developersbreach.cutepuppies.ui.theme.PuppyTheme


// Start of the Detail screen for each cat.
@Composable
fun CatDetails(
    catId: Int,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    val cat: Cats = remember(catId) {
        PuppyRepo.getCat(
            catId, context
        )
    }

    AnimatedVisibility(
        visible = true,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            initialHeight = { 0 }
        )
    ) {
        SetCatDetails(cat, navigateUp)
    }
}

// This UI starts with AnimatedVisibility
@Composable
private fun SetCatDetails(
    cat: Cats,
    navigateUp: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                item { CatHeader(cat.catImage, navigateUp) }
                item { CatInfoHeader(cat) }
                item { CatDetailBody(cat) }
                item { CatDetailAbout(cat) }
            }
        }
    }
}

// Show up button, banner of the cat, custom app bar.
@Composable
private fun CatHeader(
    catImage: Int,
    navigateUp: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(id = catImage),
            contentDescription = stringResource(R.string.content_desc_cat_detail_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White
        ) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.content_desc_up_navigate_detail)
                )
            }
        }
    }
}

// Below banner image lazy row with 4 items show descriptive cat information.
@Composable
private fun CatInfoHeader(
    cat: Cats
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        item { HairColor(cat.catHairColor) }
        item { GenderInfo(cat.catGender) }
        item { EyeColor(cat.catEyeColor) }
        item { AgeInfo(cat.catAge) }
    }
}

// First item in lazy row, card with circle canvas and text in column
@Composable
private fun HairColor(
    catHairColor: Color
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularCanvasCatInfoHeader(catHairColor)
        SubtitleForCatInfoHeader(stringResource(R.string.cat_hair_color_subtitle))
    }
}

// Second item in lazy row, card with circle canvas and text in column
@Composable
private fun GenderInfo(
    gender: String
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            Modifier.clip(CircleShape),
            backgroundColor = MaterialTheme.colors.background
        ) {
            Image(
                painter = findCatGenderAndPaint(gender),
                contentDescription = stringResource(R.string.content_desc_cat_gender_picture),
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
            )
        }
        SubtitleForCatInfoHeader(stringResource(R.string.cat_gender_subtitle))
    }
}

// Third item in lazy row, card with circle canvas and text in column
@Composable
private fun EyeColor(
    catEyeColor: Color
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularCanvasCatInfoHeader(catEyeColor)
        SubtitleForCatInfoHeader(stringResource(R.string.cat_eye_color_subtitle))
    }
}

// Fourth item in lazy row, card with circle canvas and text in column
@Composable
private fun AgeInfo(
    catAge: Int
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            Modifier.clip(CircleShape),
            backgroundColor = MaterialTheme.colors.background
        ) {
            Text(
                text = catAge.toString(),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )
        }
        SubtitleForCatInfoHeader(stringResource(R.string.cat_age_subtitle))
    }
}

// Horizontal divider
@Composable
private fun HeaderInfoDivider() {
    Divider(
        color = MaterialTheme.colors.secondary,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}

// Card with animated visibility.
// Initially shows title and subtitle with arrow button icon.
// Animates rest of the contents on clicking the button icon.
// Save state with remember and change current icon state.
// Card -> Column -> Row -> Boxes(weight 8.5f and 1.5f)
@Composable
private fun CatDetailBody(
    cat: Cats
) {
    var arrowExpanded by remember { mutableStateOf(false) }

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp)
            .animateContentSize()
    ) {
        Column {
            Row {

                Box(
                    Modifier.weight(8.5f)
                ) {
                    Column {
                        Text(
                            text = cat.catName,
                            modifier = Modifier.padding(top = 4.dp, start = 16.dp),
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = cat.catDescription,
                            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp),
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }

                Box(
                    Modifier
                        .weight(1.5f)
                        .padding(top = 12.dp),
                ) {
                    IconButton(onClick = { arrowExpanded = !arrowExpanded }) {
                        Icon(
                            painter = painterResource(getIconState(arrowExpanded)),
                            contentDescription = stringResource(id = R.string.content_desc_expand_cat_row_desc),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            AnimatedVisibility(arrowExpanded) {
                Column {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_cake),
                            contentDescription = stringResource(id = R.string.content_desc_expand_cat_row_desc),
                            modifier = Modifier
                                .padding(16.dp)
                                .size(40.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                        )
                        Text(
                            text = cat.catDateOfBirth,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }
                }
            }
        }
    }
}

// Card with animated visibility.
// Initially shows image title and subtitle.
// Animates rest of the contents on clicking the button icon.
// Card -> Column -> Row -> Child's
@Composable
private fun CatDetailAbout(
    cat: Cats
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(8.dp)
        ) {

            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_paw),
                    contentDescription = stringResource(R.string.content_desc_picture_paw_puppy),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp),
                    colorFilter = ColorFilter.tint(cat.catEyeColor)
                )

                Text(
                    text = stringResource(R.string.cat_breed_header),
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface
                )
            }

            Text(
                text = cat.catBreed,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, bottom = 8.dp),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface
            )

            AnimatedVisibility(expanded) {
                HeaderInfoDivider()
                Text(
                    text = cat.catAbout,
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

// Reusable composable for two lazy row items shows canvas
@Composable
private fun CircularCanvasCatInfoHeader(
    canvasColor: Color
) {
    Card(
        Modifier.clip(CircleShape),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Canvas(
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp),
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawCircle(
                color = canvasColor,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = size.minDimension / 2
            )
        }
    }
}

// Reusable composable for all four lazy row items
@Composable
private fun SubtitleForCatInfoHeader(
    subtitle: String
) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

// Finds the gender of the cat using string and applies correct image resource.
@Composable
private fun findCatGenderAndPaint(
    gender: String
): Painter = if (gender == stringResource(id = R.string.cat_gender_male)) {
    painterResource(id = R.drawable.ic_male)
} else {
    painterResource(id = R.drawable.ic_female)
}

@Preview("Detail Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DetailLightPreview() {
    PuppyTheme(darkTheme = false) {
        CatDetails(catId = 1, navigateUp = { })
    }
}

@Preview("Detail Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DetailDarkPreview() {
    PuppyTheme(darkTheme = true) {
        CatDetails(catId = 1, navigateUp = { })
    }
}
