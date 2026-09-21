# Image Encryption & Decryption Tool

A lightweight, cross-platform Java desktop application that encrypts and decrypts image files using key-based bitwise manipulation. Built with Java Swing, this tool provides a clean GUI, file selection filtering, and asynchronous background processing to keep the user interface responsive.

---

## Features

* **Symmetric Processing**: Encryption and decryption use the same algorithm—processing an encrypted image with the same secret key restores the original file.
* **Graphical User Interface (GUI)**: Clean Java Swing interface styled with the host OS system look-and-feel.
* **Asynchronous Execution**: Uses `SwingWorker` to handle file I/O in the background, preventing the UI from freezing on large images.
* **File Filtering**: Built-in support for standard image formats (`.jpg`, `.jpeg`, `.png`, `.bmp`).
* **Safe NIO Stream Handling**: Employs `java.nio.file.Files` for atomic and memory-safe binary read/write operations.

---

## How It Works

The tool applies a multi-byte cyclic **XOR (Exclusive OR)** transformation over the raw binary payload of the image file:

$$\text{CipherByte}_i = \text{ImageByte}_i \oplus \text{KeyByte}_{i \pmod L}$$

Where $L$ is the byte length of the passphrase. 

Because XOR is reversible:
$$(\text{ImageByte}_i \oplus \text{KeyByte}_k) \oplus \text{KeyByte}_k = \text{ImageByte}_i$$

Re-running the file through the application with the exact same passphrase strips the mask and restores the original image structure.

---

## Prerequisites

* **Java Development Kit (JDK)**: Version 11 or higher recommended (JDK 8+ compatible).
* No external third-party dependencies required.

---

## Installation & Running

1. **Clone or Download the Repository**:
   ```bash
   git clone [https://github.com/your-username/image-encryption-tool.git](https://github.com/your-username/image-encryption-tool.git)
   cd image-encryption-tool

   Compile the Java File:
   javac ImageOperation.java
   Run the Application:
   java ImageOperation


Usage Guide
Launch Application: Open the app using java ImageOperation.

Enter Secret Key: Input your secret key or passphrase in the input field.

Select Image: Click Select Image & Process.

Choose File: Browse and select the target image (.png, .jpg, .bmp, etc.).

Encrypt / Decrypt:

To Encrypt: Select a plain image and enter your key. The file will be obfuscated.

To Decrypt: Select the encrypted file and enter the exact same key. The image will be fully restored.


File Structure

├── ImageOperation.java   # Main application source code (Swing UI + Operations)
└── README.md             # Project documentation

Security Disclaimer
Note: This project uses XOR-based stream masking designed for educational purposes, rapid prototyping, and lightweight file obfuscation. XOR encryption is susceptible to known-plaintext attacks if fixed file headers are analyzed.

For production-grade security requiring protection against cryptographic analysis or tampering, upgrade to AES-256-GCM with PBKDF2 key derivation.


License
This project is open-source and available under the MIT License.
