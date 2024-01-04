# Command Bus

A command bus is a design pattern used in software architecture to decouple the sender and receiver of a command. It enables the separation of concerns by providing a mechanism to send commands from one part of the system to another without requiring direct dependencies between them.

## Introduction

This repository implements a command bus, providing a lightweight and flexible solution for handling commands within your application. It allows you to encapsulate business logic in command objects and send them to handlers, promoting clean code and maintainability.

## Features

- **Decoupling**: Separate the command sender from the command handler, reducing dependencies and improving modularity.
- **Flexibility**: Easily extend or modify command handling logic without impacting the rest of the system.
- **Command Objects**: Encapsulate behavior in command objects, promoting a clean and organized codebase.
