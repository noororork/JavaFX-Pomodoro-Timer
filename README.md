# ⏳ Pomodoro Timer (JavaFX + FSM)

A **Pomodoro Timer application** built with **JavaFX** and a **Finite State Machine (FSM)** architecture.  
This project implements the classic [Pomodoro Technique](https://en.wikipedia.org/wiki/Pomodoro_Technique), allowing users to focus on productivity while learning advanced Java design patterns and UI development.

---

## ✨ Features

- 🎛️ **Finite State Machine (FSM)** for managing work/break cycles
- 🔄 **Custom State Classes** (`Work`, `ShortBreak`, `LongBreak`)
- ⏱️ **Configurable Durations** (change work and break times dynamically)
- 🎨 **Styled JavaFX UI** with custom CSS
- 📐 **Overlay Settings Panel** (no extra stages, uses `StackPane` layering)
- 📊 **Round & Cycle Counters** (track sessions and full Pomodoro cycles)

---

## 🛠️ Skills Demonstrated

### 🧩 Object-Oriented Programming (OOP)
- **Abstraction** → Defined an abstract `State` class that enforces a common contract (`getName`, `getTime`, `nextState`, `setTime`) for all Pomodoro states.
- **Inheritance** → Implemented concrete states (`Work`, `ShortBreak`, `LongBreak`) by extending `State` and customizing their behavior.
- **Polymorphism** → `FSM` interacts with all states through the `State` interface, enabling flexible transitions without needing to know the concrete class.
- **Encapsulation** → State transitions, round counters, and full cycle logic are managed entirely within `FSM`, keeping responsibilities separated and data protected.
- **Design Patterns** → Applied the **State Pattern** to model the Pomodoro workflow, improving code maintainability and extensibility.

### 🎨 JavaFX Development
- Built responsive UIs with `VBox`, `StackPane`, and alignment controls.
- Created **overlay panels** (settings menu) inside a single stage using visibility toggling rather than separate windows.
- Applied **JavaFX CSS** for custom fonts, colors, and backgrounds.
- Managed user interactions via `setOnAction` event handlers.

### 📐 Software Engineering Practices
- Clean separation of **business logic** (FSM + States) and **UI logic** (JavaFX layouts, CSS).
- Debugged and solved UI issues such as **transparent overlays** and **node alignment**.
- Wrote extensible, maintainable, and modular code following OOP principles.

## 📂 Project Structure

```plaintext
src/
 ├── Main.java          # JavaFX application entry point
 ├── FSM.java           # Finite State Machine for Pomodoro flow
 ├── State.java         # Abstract state class (contract)
 ├── Work.java          # Work state implementation
 ├── ShortBreak.java    # Short break state implementation
 ├── LongBreak.java     # Long break state implementation
 └── styles.css         # JavaFX styling
```

## 🚀 Getting Started
### Prerequisites:
Java 21+
JavaFX SDK installed and configured

To compile and run:
javac --module-path "<javafx_path>" --add-modules=javafx.controls src/*.java
java --module-path "<javafx_path>"  --add-modules=javafx.controls Main
