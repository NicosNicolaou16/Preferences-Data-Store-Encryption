# Preference Data Store Encryption

[![Linktree](https://img.shields.io/badge/linktree-1de9b6?style=for-the-badge&logo=linktree&logoColor=white)](https://linktr.ee/nicos_nicolaou)
[![Site](https://img.shields.io/badge/Site-blue?style=for-the-badge&label=Web)](https://nicosnicolaou16.github.io/)
[![X](https://img.shields.io/badge/X-%23000000.svg?style=for-the-badge&logo=X&logoColor=white)](https://twitter.com/nicolaou_nicos)
[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/nicos-nicolaou-a16720aa)
[![Medium](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/@nicosnicolaou)
[![Mastodon](https://img.shields.io/badge/-MASTODON-%232B90D9?style=for-the-badge&logo=mastodon&logoColor=white)](https://androiddev.social/@nicolaou_nicos)
[![Bluesky](https://img.shields.io/badge/Bluesky-0285FF?style=for-the-badge&logo=Bluesky&logoColor=white)](https://bsky.app/profile/nicolaounicos.bsky.social)
[![Dev.to blog](https://img.shields.io/badge/dev.to-0A0A0A?style=for-the-badge&logo=dev.to&logoColor=white)](https://dev.to/nicosnicolaou16)
[![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?style=for-the-badge&logo=YouTube&logoColor=white)](https://www.youtube.com/@nicosnicolaou16)
[![Google Developer Profile](https://img.shields.io/badge/Developer_Profile-blue?style=for-the-badge&label=Google)](https://g.dev/nicolaou_nicos)

A robust Android demonstration project showcasing how to implement secure, encrypted data storage using **Jetpack DataStore** and **AES encryption**. This project highlights best practices for protecting sensitive user information at rest.

## ✨ Features

*   **Secure Storage:** Implements **Jetpack DataStore (Proto)** for typed data storage with an added layer of encryption.
*   **AES Encryption:** Utilizes the **Android Keystore System** to manage cryptographic keys and perform AES-GCM encryption/decryption.
*   **Modern UI:** Built with **Jetpack Compose**, featuring a clean interface for testing real-time data encryption and retrieval.
*   **Dependency Injection:** Uses **Hilt** for clean architecture and easy management of encryption components and DataStore instances.

## 🛠️ Tech Stack & Libraries

- **UI:** [Jetpack Compose](https://developer.android.com/develop/ui/compose)
- **Storage:** [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) (Preferences DataStore)
- **Security:** [Android Keystore System](https://developer.android.com/training/articles/keystore), AES-GCM Encryption
- **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)
- **Asynchronicity:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)

## 🔧 Versioning

- **Target SDK:** **36**
- **Minimum SDK:** **28**
- **Kotlin Version:** **2.3.20**
- **Gradle Version:** **9.1.0**

## 📚 References & Resources

- **Official Documentation:** [DataStore Guide](https://developer.android.com/topic/libraries/architecture/datastore)
- **Security Best Practices:** [EncryptedSharedPreferences vs DataStore](https://developer.android.com/training/articles/keystore)
- **Inspiration:** Following Google's modern Android development (MAD) recommendations.

## ⭐ Stargazers

If you find this security implementation helpful, please give it a star!
[Stargazers on GitHub](https://github.com/NicosNicolaou16/DataStoreEncryption/stargazers)

## 🙏 Support & Contributions

This project is actively maintained to reflect the latest security standards. Feedback, bug reports, and feature requests are welcome! Please feel free to **open an issue** or submit a **pull request**.