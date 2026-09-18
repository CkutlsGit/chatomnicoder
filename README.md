# ChatOmniCoder

Console-based AI chat application built with pure Java and raw HTTP, connecting to a local LLM via LM Studio.

📖 In Russian: [перевод на русский](https://github.com/CkutlsGit/chatomnicoder/blob/main/README.ru.md)

## 📋 Overview

ChatOmniCoder is a console application that lets you chat with a locally running AI model through LM Studio's HTTP API. The project uses only Java's standard library — specifically java.net.HttpURLConnection — with no external frameworks. It supports persistent conversation context.

## 🚀 Technology Stack

### Backend
- **Java 21** - Core language
- **java.net.HttpURLConnection** - Raw HTTP communication (built into the JDK)

### AI
- **LM Studio** - Local LLM server with OpenAI-compatible REST API

## ✨ Features

- 💬 **Console Chat**
  - Simple, fast, dependency-light

- 🤖 **Local AI Communication**
  - Talks directly to LM Studio over HTTP
  - No cloud APIs, fully offline-capable

- 🧠 **Context Persistence**
  - The AI ​​remembers the context.

- 🔧 **Minimal Dependencies**
  - Pure Java standard library
  - No Spring, no HTTP clients, no bloat

## 🛠️ Quick Start

### Prerequisites
- **Java 21+** (JDK)
- **LM Studio**
- A downloaded LLM model

### Installation & Running

1. **Clone the repository**
```bash
git clone https://github.com/CkutlsGit/chatomnicoder
cd chatomnicoder
```

2. **Set up LM Studio**
   - Open LM Studio and download a model of your choice
   - Go to the **Developer** tab (or **Local Server**)
   - Load your model and click **Start Server**
   - By default it runs at: `http://localhost:1234/v1`

3. **Compile the project**
```bash
javac -d out src/main/java/**/*.java
```

4. **Run the application**
```bash
java -cp out Main
```

## 📦 Project Structure

```
src/main/java/
├── Main.java              # Entry point
├── ai/                    # AI integration logic
├── client/                # HttpClient wrapper for LM Studio
├── model/                 # Data models
├── server/                # Server-side handling / request building
└── utils/                 # Helpers (JSON parsing)
```

## 🔒 Privacy

- **100% local** — no data leaves your machine
- **No telemetry**, no API keys, no accounts

Note: An educational project for reinforcing Java skills and working with threads.
