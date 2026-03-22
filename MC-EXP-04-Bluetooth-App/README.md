# Exp 2 Bluetooth File Transfer App

This project is a simple Android application designed to demonstrate Bluetooth file transfer capabilities. It allows users to select a file from their device storage and send it to another paired Bluetooth device using the Android system's native Bluetooth sharing mechanism.

## Features

- **File Selection**: Integration with the system file picker to select any type of file (`*/*`).
- **Bluetooth Sharing**: Uses Android's `Intent.ACTION_SEND` to initiate file transfer via the native Bluetooth app.
- **Permission Handling**: Automatically handles runtime permissions for Android 12+ (API 31+), including `BLUETOOTH_CONNECT` and `BLUETOOTH_SCAN`.
- **Bluetooth State Management**: Checks if Bluetooth is enabled and prompts the user to enable it if necessary.
- **User Feedback**: Toast messages provide feedback on success, errors, or missing permissions.

## Getting Started

### Prerequisites

- Android SDK installed.
- Android device or emulator with Bluetooth support.

### Installation

1. Clone or download this repository.
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Run the application on an Android device.

## Usage

1. **Launch the App**: Open the app on your Android device.
2. **Grant Permissions**: If prompted, allow Bluetooth permissions (especially on Android 12+).
3. **Select File**:
   - Tap the **"Select File"** button.
   - Choose a file from your device's storage.
   - The text field will update to show "File selected ✓".
4. **Send via Bluetooth**:
   - Tap the **"Send"** button.
   - If Bluetooth is off, the app will ask to turn it on.
   - The system Bluetooth chooser will appear; select the target device to send the file.

## Code Structure

- **MainActivity.java**: Contains the core logic for the UI, file picker result handling, permission requests, and the intent to share the file.
- **AndroidManifest.xml**: Defines the necessary permissions (`BLUETOOTH`, `BLUETOOTH_ADMIN`, `BLUETOOTH_CONNECT`, `BLUETOOTH_SCAN`) and declares the main activity.

## Troubleshooting

- **"Bluetooth app not found"**: This error may occur on emulators that do not have the standard Bluetooth application installed. Test on a physical device.
- **Permission Denied**: Ensure you grant the necessary permissions when the app first launches. You can also manually enable them in the App Settings.

