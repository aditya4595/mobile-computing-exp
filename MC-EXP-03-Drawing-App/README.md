# Experiment No. 3: Drawing App

## Aim
To design and develop an Android application that provides a **freehand drawing canvas** with tools for selecting colors, adjusting stroke width, and sharing the created artwork as an image.

## Objective
- Build a custom drawing canvas using Android's `Canvas`, `Path`, and `Paint` APIs.
- Allow the user to draw freehand on the screen using touch events.
- Provide a color picker for changing both the stroke color and the canvas background color.
- Implement a stroke-width selector using a `SeekBar` dialog.
- Support **Undo** and **Redo** operations for drawn paths.
- Allow the user to **clear** the entire canvas.
- Enable **sharing** the drawing as a PNG image via Android's share intent.
- Handle runtime storage permissions for saving the image to external storage.

## Theory

### Custom View Drawing
Android's `View` class can be extended to create a custom drawing surface. The key components used are:

- **`Canvas`** — The surface on which the drawing operations are performed.
- **`Paint`** — Defines the style, color, and stroke width for each drawing operation.
- **`Path`** — Records a series of connected lines and curves that represent the user's finger movement.
- **`Bitmap`** — An off-screen buffer that holds the final rendered image for saving/sharing.

### Touch Event Handling
Touch events in `onTouchEvent()` are mapped to drawing operations:

| Event | Action |
|---|---|
| `ACTION_DOWN` | Begin a new `Path` at the touch point |
| `ACTION_MOVE` | Extend the `Path` to follow the finger |
| `ACTION_UP` | Finalize the current `Path` and store it |

### Undo / Redo Mechanism
Two lists maintain the drawing history:
- **`mPaths`** — Active paths currently displayed on the canvas.
- **`mUndonePaths`** — Paths removed by undo, available for redo.

Undo removes the last path from `mPaths` and pushes it onto `mUndonePaths`. Redo reverses the operation.

### File Saving & Sharing
The completed drawing is rendered to a `Bitmap`, compressed to PNG format, and saved to external storage. A `FileProvider` URI is used to securely share the image via `Intent.ACTION_SEND`.

## Tech Stack

| Component | Technology |
|---|---|
| Language | Java |
| Min SDK | 21 (Android 5.0 Lollipop) |
| Target SDK | 34 (Android 14) |
| Compile SDK | 34 |
| UI | Custom View, LinearLayout |
| Color Picker | xdty ColorPickerDialog library |
| File Sharing | FileProvider + Intent.ACTION_SEND |
| Build System | Gradle |

## Features

### 1. Freehand Drawing
- Smooth freehand drawing on a full-screen canvas.
- Anti-aliased strokes with round joins and caps for a natural drawing feel.
- Real-time rendering using `invalidate()` on each touch event.

### 2. Color Selection
- **Stroke Color Picker** — Choose the drawing color from a color palette dialog.
- **Background Color Picker** — Change the canvas background color.

### 3. Stroke Width Adjustment
- A `SeekBar`-based dialog allows selecting stroke width from 1 to 50 pixels.
- Changes apply immediately to subsequent strokes.

### 4. Undo & Redo
- **Undo** — Removes the last drawn path from the canvas.
- **Redo** — Restores the last undone path.

### 5. Clear Canvas
- Clears all paths and resets the canvas with a confirmation dialog.

### 6. Share Drawing
- Saves the current canvas as a PNG image to external storage.
- Opens the Android share sheet to send the image via any installed app (WhatsApp, Email, etc.).

## App Screen

| Screen | Description |
|---|---|
| **Main Canvas** | Full-screen drawing area with a bottom toolbar containing Fill, Color, Stroke, Undo, and Redo buttons. |
| **Color Picker Dialog** | Grid-based color palette for selecting stroke or background color. |
| **Stroke Selector Dialog** | SeekBar-based dialog for adjusting stroke width. |
| **Share Chooser** | System share sheet for sending the saved drawing image. |

## Project Structure

```
co.martinbaciga.drawingtest
├── domain/
│   ├── manager/
│   │   ├── FileManager.java              # Saves bitmap to external storage & returns URI
│   │   └── PermissionManager.java         # Handles runtime WRITE_EXTERNAL_STORAGE permission
│   └── provider/
│       └── GenericFileProvider.java        # FileProvider for secure file sharing
├── ui/
│   ├── activity/
│   │   └── MainActivity.java              # Main activity with toolbar actions & click listeners
│   ├── component/
│   │   └── DrawingView.java               # Custom View handling drawing, undo/redo, and canvas ops
│   └── dialog/
│       └── StrokeSelectorDialog.java      # DialogFragment with SeekBar for stroke width selection
└── res/
    ├── layout/
    │   ├── activity_main.xml              # Main layout with DrawingView and bottom toolbar
    │   └── fragment_dialog_stroke_selector.xml  # Stroke selector dialog layout
    ├── menu/
    │   └── menu_main.xml                  # Options menu with Share and Clear actions
    └── xml/
        └── provider_paths.xml             # FileProvider path configuration
```

## How to Run

1. Open the project in **Android Studio**.
2. Sync Gradle and let dependencies download.
3. Connect a physical device or start an emulator (API 21+).
4. Click **Run ▶** to build and install the app.

## Permissions

| Permission | Purpose |
|---|---|
| `READ_EXTERNAL_STORAGE` | Read files from storage |
| `WRITE_EXTERNAL_STORAGE` | Save drawing as PNG to external storage |

## Conclusion

The experiment demonstrates the development of a drawing application on Android using custom `View` rendering with `Canvas` and `Paint`. Key learnings include handling **touch events** for freehand drawing, implementing an **undo/redo** stack using `ArrayList`-based path history, using a **color picker library** for intuitive color selection, adjusting **stroke width** via a dialog, and **sharing images** securely using `FileProvider`. The app showcases how Android's 2D graphics APIs can be leveraged to build interactive, creative applications.
