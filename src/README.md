# CS211E — `m01_streams` Run Guide

## Quick run (from repo root)
```bash
./gradlew :m01_streams:run --args="--input src/main/resources/world_cities.csv"
```

## What this is
- A Gradle multi-module class project.
- Module **`m01_streams`** streams `world_cities.csv` and processes rows with Java Streams (Jackson CSV → POJO → Stream ops).

## Requirements
- **JDK 21** on PATH.
- Gradle **wrapper** included (`./gradlew`), no separate Gradle install.

## Variations
- If your `Main` takes **no args**:
  ```bash
  ./gradlew :m01_streams:run
  ```
- Use a **different CSV**:
  ```bash
  ./gradlew :m01_streams:run --args="--input path/to/your.csv"
  ```
- List available **modules**:
  ```bash
  ./gradlew projects
  ```

## Notes
- Run commands from the **repo root** so the relative CSV path resolves.
- If Gradle says the project/task isn’t found, verify the module is named `m01_streams` and included in `settings.gradle`.
