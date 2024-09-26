# Dagger Graph Visualization Library

## Overview

This library provides a way to visualize Dagger dependency graphs in your Android project. It generates graphical representations, in SVG format, showing how Dagger components are interconnected.

## Prerequisites

- **Graphviz:** To use this library, ensure you have Graphviz installed. You can download it from [here](https://graphviz.org/download/).

## Installation

Include the library in your `build.gradle` file:

### Step 1: Add Dependency

Add the following dependency to the `dependencies` section in your module-level `build.gradle` file:

dependencies {
    implementation 'your.group.id:dagger-graph-visualization:1.0.0'
}


### Step 2: Sync Project

After adding the dependency, sync your project with Gradle files.

## Usage

- Build your project after including the library. During the build process, SVGs representing the Dagger dependency graphs will be automatically generated.
  
- You can find the generated SVG files in the following directory of your module:
module-x/
 └── build/
     └── generated/
         └── ksp/
             └── resources/
                 └── dagger/
                     └── graph/
                         ├── component1.svg
                         ├── component2.svg
                         └── ...

  
## Contributing

Feel free to open issues or submit pull requests for improvements and bug fixes. Contributions are always welcome!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

Thanks to the Dagger and Graphviz teams for their powerful tools that make dependency management and visualization easier and more intuitive.
