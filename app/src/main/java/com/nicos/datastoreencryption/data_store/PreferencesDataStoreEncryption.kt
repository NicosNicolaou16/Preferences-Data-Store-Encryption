package com.nicos.datastoreencryption.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesFileSerializer
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.tink.AeadSerializer
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplate
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.PredefinedAeadParameters
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferencesDataStoreEncryption @Inject constructor(private val context: Context) {

    private val keysetHandle =
        AndroidKeysetManager.Builder()
            .withSharedPref(context, "keyset", "keyset_prefs")
            .withKeyTemplate(KeyTemplate.createFrom(PredefinedAeadParameters.AES256_GCM))
            .withMasterKeyUri("android-keystore://master_key")
            .build()
            .keysetHandle

    private val aeadSerializer = AeadSerializer(
        // Use tink APIs to create an Aead object to encrypt/decrypt data.
        aead =
            keysetHandle.getPrimitive(
                RegistryConfiguration.get(),
                Aead::class.java,
            ),
        // AeadSerializer can wrap an existing serializer.
        wrappedSerializer = PreferencesFileSerializer,
        // Specify a unique name to prevent a ciphertext swapping attack.
        associatedData = "settings.json".encodeToByteArray(),
    )

    private val Context.encryptedPrefsDataStore: DataStore<Preferences> by dataStore(
        fileName = "user_preferences.json",
        serializer = aeadSerializer
    )

    internal suspend fun saveStringValue(
        value: String,
        key: Preferences.Key<String>,
        context: Context
    ) {
        context.encryptedPrefsDataStore.edit { saveData ->
            saveData[key] = value
        }
    }

    internal fun getStringValueFlow(key: Preferences.Key<String>, context: Context): Flow<String?> =
        context.encryptedPrefsDataStore.data
            .catch { exception ->
                when (exception) {
                    is IOException -> emit(emptyPreferences())
                    else -> throw exception
                }
            }.map { readData ->
                readData[key]
            }
}