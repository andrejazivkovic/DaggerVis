<div style="display: flex; align-items: center;">
    <h1 style="margin-right: 10px;">Dagger Graph Visualization Library (KSP)</h1>
    <img src="https://github.com/andrejazivkovic/DaggerVis/blob/master/app/src/main/res/drawable/dagger_graph_vis.jpeg?raw=true" 
         alt="Profile Picture" 
         style="border-radius: 30%; width: 200px; height: 200px; object-fit: cover;"/>
</div>



## Overview

This library provides a way to visualize Dagger dependency graphs in your Android project. It generates graphical representations, in SVG format, showing how Dagger components are interconnected.

## Prerequisites

- **Graphviz:** To use this library, ensure you have Graphviz installed. You can download it from [here](https://graphviz.org/download/).
- **Java Version:** This library operates on Java 21. Ensure that your project is configured to use this version or higher.
- **Dagger version:** Make sure you are using Dagger 2.48 (or above) that is KSP supported.

## Installation

Include the library in your `build.gradle` file:

### Step 1: Add Dependency

Add the following dependency to the `dependencies` section in your module-level `build.gradle` file:

`ksp("com.github.andrejazivkovic:DaggerVis:1.0.16")`

## Usage
- After adding the dependency, sync your project with Gradle files.
  
- Build your project after including the library. During the build process, SVGs representing the Dagger dependency graphs will be automatically generated.
  
- You can find the generated SVG files in the following directory of your module:

module-x/  
&nbsp;&nbsp;&nbsp;&nbsp; └── build/  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── generated/  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── ksp/  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── resources/  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── dagger/  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── graph/  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── Dagger-Graph-(component_x).svg  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── Dagger-Graph-(component_y).svg  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── ...

- Example from the test app:
     
<img src="https://github.com/andrejazivkovic/DaggerVis/blob/master/app/src/main/res/drawable/dagger_graph_main.png" 
         alt="Profile Picture" 
         style="border-radius: 30%; width: 300px; height: 250px; object-fit: cover;"/>
         
## License

[![MIT License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
<div style="text-align: center;">
    <a href="https://www.linkedin.com/in/andreja-zivkovic-80015a157">
        <img src="https://upload.wikimedia.org/wikipedia/commons/8/81/LinkedIn_icon.svg" alt="LinkedIn Icon" width="50"/>
    </a>
</div>
