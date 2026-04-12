package com.nicos.datastoreencryption.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesFileSerializer
import androidx.datastore.tink.AeadSerializer
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplate
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.PredefinedAeadParameters
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesDataStoreEncryption @Inject constructor(@ApplicationContext context: Context) {

    private val keysetHandle =
        AndroidKeysetManager.Builder()
            .withSharedPref(applicationContext, "keyset", "keyset_prefs")
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
}