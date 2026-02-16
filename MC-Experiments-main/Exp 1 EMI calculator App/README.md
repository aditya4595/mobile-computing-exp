# Experiment No. 1: EMI Calculator App

## Aim
To design and develop an Android application that functions as both a **Scientific Calculator** and an **EMI (Equated Monthly Installment) Calculator** using Kotlin, following the MVVM architecture pattern.

## Objective
- Build a fully functional scientific calculator with standard and advanced mathematical operations.
- Implement an EMI calculator that computes monthly installments using the standard EMI formula.
- Provide a comparison feature to evaluate two EMI scenarios side-by-side.
- Apply modern Android development practices including MVVM, Dependency Injection, Data Binding, and Room Database.

## Theory

### EMI Formula
The EMI is calculated using the standard formula:

$$EMI = \frac{P \times r \times (1+r)^n}{(1+r)^n - 1}$$

Where:
- **P** = Principal loan amount
- **r** = Monthly interest rate (annual rate / 12 / 100)
- **n** = Number of monthly installments (loan tenure in months)

### Architecture — MVVM (Model-View-ViewModel)
The app follows the **MVVM** pattern to ensure a clean separation of concerns:
- **Model** — Room database entity (`PreviousOperation`) and repository layer for persisting calculation history.
- **View** — Fragments and XML layouts with Data Binding for reactive UI updates.
- **ViewModel** — `CalculatorViewModel` and `EMIViewModel` manage UI state using Kotlin `StateFlow` and coroutines.

## Tech Stack

| Component | Technology |
|---|---|
| Language | Kotlin |
| Architecture | MVVM |
| DI Framework | Dagger Hilt |
| Database | Room (SQLite) |
| Navigation | Jetpack Navigation Component with SafeArgs |
| Math Engine | mXparser |
| UI | Material Design, ConstraintLayout, Data Binding |
| Async | Kotlin Coroutines + StateFlow |
| Responsive Sizing | SDP / SSP libraries |

## SDK Configuration

| Property | Value |
|---|---|
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |
| Compile SDK | 34 |
| Kotlin | 1.9.24 |

## Features

### 1. Scientific Calculator
- Basic arithmetic: addition, subtraction, multiplication, division, percentage, brackets.
- Scientific functions (landscape mode): sin, cos, tan, log, ln, √, ∛, powers, factorial, hyperbolic functions, inverse trigonometric functions.
- Portrait mode for normal calculator; landscape mode for scientific calculator.
- Persistent calculation history stored in Room database with clear option.

### 2. EMI Calculator
- Input fields: Loan Amount, Annual Interest Rate, Number of Installments.
- Displays: Monthly EMI, Total Payment, Principal, and Total Interest.
- **Compare** feature: calculate and compare two EMI scenarios in a side-by-side table.
- **Share** results via Android's share intent.

### 3. Additional Features
- Dark mode support.
- Navigation between screens using Jetpack Navigation.
- Dependency injection with Hilt for testability and modularity.
- Data Binding adapters for seamless ViewModel-to-UI binding.

## App Screens

| Screen | Description |
|---|---|
| **Calculator** | Main screen with number pad, operators, history panel, and scientific toggle. |
| **EMI Calculator** | Input form for loan amount, interest rate, and installments. |
| **EMI Result** | Displays computed EMI, total payment, principal, and interest breakdown. |
| **Compare** | Side-by-side comparison table of two EMI calculations. |

## Project Structure

```
com.yassineabou.calculator
├── CalculatorApplication.kt              # @HiltAndroidApp entry point
├── data/
│   ├── local/
│   │   ├── PreviousOperationDao.kt       # Room DAO
│   │   └── PreviousOperationDatabase.kt  # Room Database
│   ├── model/
│   │   └── PreviousOperation.kt          # Room Entity
│   └── repository/
│       └── PreviousOperationRepository.kt
├── di/
│   └── DatabaseModule.kt                 # Hilt DI Module
├── ui/
│   ├── MainActivity.kt                   # Single Activity host
│   ├── calculator/
│   │   ├── CalculatorFragment.kt
│   │   ├── CalculatorViewModel.kt
│   │   └── ListPreviousOperationsAdapter.kt
│   └── emi/
│       ├── CompareFragment.kt
│       ├── EmiCalculationFragment.kt
│       ├── EmiCalculatorFragment.kt
│       └── EmiViewModel.kt
└── util/
    ├── Extensions.kt                     # Binding adapters & helpers
    └── ViewBinding.kt                    # ViewBinding delegate
```

## How to Run

1. Open the project in **Android Studio** (Hedgehog or later).
2. Sync Gradle and let dependencies download.
3. Connect a physical device or start an emulator (API 24+).
4. Click **Run ▶** to build and install the app.

## Navigation Flow

```
CalculatorFragment
    ├──→ EmiCalculatorFragment
    │        ├──→ EmiCalculationFragment (single result)
    │        │        └──→ CompareFragment (side-by-side)
    │        └──→ CompareFragment (direct compare)
    └── (History panel within calculator)
```

## Conclusion

The experiment demonstrates the development of a modern Android application combining a scientific calculator with an EMI calculator. Key learnings include implementing the **MVVM architecture**, using **Dagger Hilt** for dependency injection, persisting data with **Room**, reactive UI updates with **Data Binding** and **StateFlow**, and multi-screen navigation using the **Jetpack Navigation Component**. The app successfully computes EMI values using the standard formula and supports comparison of multiple loan scenarios.
