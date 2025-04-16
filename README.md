# OSRS Scripts

A collection of automation scripts for Old School RuneScape (OSRS) skills, developed using the OSMB API.

## Overview

This project contains scripts for automating various skilling activities in OSRS, organized by skill category. The scripts are designed to work with the OSMB client and API.

## Features

- **Modular Design**: Each skill has its own module with specific implementations
- **GUI Interface**: User-friendly interface for configuring script options
- **Multiple Skill Support**: Scripts for various OSRS skills

### Other Skills (In Development)

- **Agility**: Automation for agility courses
- **Fishing**: Fishing spot automation
- **Magic**: Magic training automation
- **Mining**: Mining automation

## Requirements

- OSMB Client
- Java Runtime Environment
- OSMB API (included in Lib/API.jar)

## Usage

1. Load the script in the OSMB client
2. Select the desired skill script
3. Configure options in the GUI
4. Click "Start" to begin automation

## Development

Each skill module follows a similar pattern:
1. Main script class extending `Script` from the OSMB API
2. GUI class for configuration
3. Implementation classes for specific activities

## License

This project is for educational purposes only. Use at your own risk.

## Author

tezkidd