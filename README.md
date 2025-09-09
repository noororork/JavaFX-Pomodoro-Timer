# ⏳ Pomodoro Timer (JavaFX + FSM)

A **Pomodoro Timer application** built with **JavaFX** and a **Finite State Machine (FSM)** architecture.  
This project implements the classic [Pomodoro Technique](https://en.wikipedia.org/wiki/Pomodoro_Technique), something that I use constantly while studying. I wanted to create an application that I would actually use, and implement features that I would look for in such an app.

---

## ✨ Features

- 🎛️ **Finite State Machine (FSM)** for managing work/break cycles
- ⏱️ **Configurable Durations** (change work and break times dynamically)
- 🎨 **Styled JavaFX UI** with custom CSS
- 📊 **Round & Cycle Counters** (track sessions and full Pomodoro cycles using Java Timeline)

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
- Applied **JavaFX CSS** for custom fonts, colors, and backgrounds.
- Managed user interactions via `setOnAction` event handlers.
- Used Java **Timeline** to manage timings and avoid conflicts

### 📐 Software Engineering Practices
- Clean separation of **business logic** (FSM + States) and **UI logic** (JavaFX layouts, CSS).
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

### To compile and run:
javac --module-path "<javafx_path>" --add-modules=javafx.controls src/*.java
java --module-path "<javafx_path>"  --add-modules=javafx.controls -cp src/Main
