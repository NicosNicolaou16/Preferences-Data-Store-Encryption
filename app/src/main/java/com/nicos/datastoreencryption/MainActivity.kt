package com.nicos.datastoreencryption

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.core.stringPreferencesKey
import com.nicos.datastoreencryption.data_store.PreferencesDataStoreEncryption
import com.nicos.datastoreencryption.ui.theme.DataStoreEncryptionTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesDataStoreEncryption: PreferencesDataStoreEncryption

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStoreEncryptionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        preferencesDataStoreEncryption = preferencesDataStoreEncryption,
                        innerPadding = innerPadding,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    preferencesDataStoreEncryption: PreferencesDataStoreEncryption,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {

    var encryptedDataStoreValue by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        // save the value
        preferencesDataStoreEncryption.saveStringValue(
            value = "Hello Encrypted Data Store!",
            key = stringPreferencesKey(name = "randomKey")
        )
        // read the value
        preferencesDataStoreEncryption.getStringValueFlow(
            key = stringPreferencesKey(name = "randomKey")
        ).collect {
            encryptedDataStoreValue = it ?: ""
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = encryptedDataStoreValue,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataStoreEncryptionTheme {
        val context = LocalContext.current

        val mockDataStore = remember { PreferencesDataStoreEncryption(context) }

        Scaffold { innerPadding ->
            Greeting(
                preferencesDataStoreEncryption = mockDataStore,
                innerPadding = innerPadding,
                modifier = Modifier
            )
        }
    }
}