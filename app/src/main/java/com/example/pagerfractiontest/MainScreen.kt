package com.example.pagerfractiontest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pagerfractiontest.ui.theme.PagerFractionTestTheme

object MainScreen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun HorizontalPagerFractionTest(
        modifier: Modifier = Modifier,
        pagerSize: Int
    ) {
        val pagerState = rememberPagerState(
            pageCount = {
                pagerSize
            }
        )
        LazyColumn {
            item {
                HorizontalPager(
                    modifier = modifier
                        .height(96.dp)
                        .background(color = Color.Black),
                    state = pagerState
                ) { page ->
                    PagerItem(
                        modifier = Modifier.fillMaxSize(),
                        text = "page : $page"
                    )
                }
            }

            item {
                Text(text = "pagerState.currentPage : ${pagerState.currentPage}")
                Text(text = "pagerState.currentPageOffsetFraction : %.5f".format(pagerState.currentPageOffsetFraction))
            }

            items(count = pagerSize) { index ->
                Text(
                    text = "page $index : %.5f".format(
                        pagerState.currentPage - index + pagerState.currentPageOffsetFraction
                    )
                )
            }
        }
    }

    @Composable
    private fun PagerItem(
        modifier: Modifier = Modifier,
        text: String
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PagerFractionTestTheme {
        MainScreen.HorizontalPagerFractionTest(pagerSize = 10)
    }
}