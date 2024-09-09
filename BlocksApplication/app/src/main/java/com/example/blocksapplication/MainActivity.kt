package com.example.blocksapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blocksapplication.ui.theme.BlocksApplicationTheme
import com.example.blocksapplication.utils.IConnectivityObserver
import com.example.blocksapplication.utils.NetworkConnectivityObserver
import com.example.blocksapplication.view.BlockError
import com.example.blocksapplication.view.BlockList
import com.example.blocksapplication.view.BlocksViewModel
import com.example.blocksapplication.view.CircularProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: IConnectivityObserver

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        connectivityObserver = NetworkConnectivityObserver(application = application)
        setContent {
            BlocksApplicationTheme {
                Scaffold(
                    // in scaffold we are specifying top bar.
                    topBar = {
                        // inside top bar we are specifying background color.
                        TopAppBar(
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                scrolledContainerColor = MaterialTheme.colorScheme.surface,
                            ),
                            // along with that we are specifying title for our top bar.
                            title = {
                                // in the top bar we are specifying tile as a text
                                Text(
                                    // on below line we are specifying
                                    // text to display in top app bar.
                                    text = "Block Images",

                                    // on below line we are specifying
                                    // modifier to fill max width.
                                    modifier = Modifier.fillMaxWidth(),

                                    // on below line we are
                                    // specifying text alignment.
                                    textAlign = TextAlign.Center,

                                    // on below line we are
                                    // specifying color for our text.
                                    color = Color.White
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        val status by connectivityObserver.observe()
                            .collectAsState(initial = connectivityObserver.isConnected())

                        if (IConnectivityObserver.Status.Available == status) {
                            val viewModel: BlocksViewModel = hiltViewModel()
                            BlockList(viewModel)
                            CircularProgressBar(viewModel)
                            BlockError(viewModel)
                        } else {
                            Retry()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Retry() {
    Box(modifier = Modifier.fillMaxSize().padding(8.dp).border(1.dp, Color.LightGray), contentAlignment = Alignment.Center){
        Text(
            text = "No Internet Connection! Please turn on Wifi/Mobile Data",
            modifier = Modifier.fillMaxSize().size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlocksApplicationTheme {
        Retry()
    }
}