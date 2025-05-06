package com.example.mobilelegendcharacterlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilelegendcharacterlist.ui.theme.MobileLegendCharacterListTheme
import com.example.mobilelegendcharacterlist.heroListML.heroList
import android.content.Intent
import android.net.Uri
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileLegendCharacterListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "heroList"
                    ) {
                        composable("heroList") {
                            HeroList(navController)
                        }
                        composable("penjelasan/{description}/{image}",
                            arguments = listOf(
                                navArgument("description") { type = NavType.StringType },
                                navArgument("image") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val description = backStackEntry.arguments?.getString("description") ?: ""
                            val image = backStackEntry.arguments?.getInt("image") ?: 0
                            PenjelasanScreen(description, image)
                        }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun HeroList(navController: NavHostController) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            items(heroList.size) { DataHero ->
                val heroes = heroList[DataHero]
                HeroItem(
                    name = heroes.name,
                    image = heroes.image,
                    url = heroes.url,
                    description = heroes.description,
                    navController = navController
                )
            }
        }
    }

    @Composable
    fun HeroItem(name: String, image: Int, url: String, description: String, navController: NavHostController) {
        val context = LocalContext.current
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 100.dp, height = 120.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start),
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            },
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier.defaultMinSize(minWidth = 1.dp)
                        ) {
                            Text("Detail", fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val encodedDesc = Uri.encode(description)
                                navController.navigate("penjelasan/${Uri.encode(description)}/$image")
                            },
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier.defaultMinSize(minWidth = 1.dp)
                        ) {
                            Text("Penjelasan", fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun PenjelasanScreen(description: String, image: Int) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(WindowInsets.statusBars.asPaddingValues()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                fontSize = 16.sp
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MobileLegendCharacterListTheme {
            Text("Preview List Hero")
        }
}