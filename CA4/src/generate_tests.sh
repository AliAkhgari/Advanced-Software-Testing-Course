#!/bin/bash

# Set the base directory where your classes are compiled
BASE_DIR="../target/classes"

# Set the package path
PACKAGE_PATH="ir/ramtung/impl2"

# Find all .class files in the package path
find "$BASE_DIR/$PACKAGE_PATH" -name "*.class" | while read classFile; do
    # Get the fully qualified class name by removing the base directory and replacing slashes with dots
    className=$(echo "$classFile" | sed "s|$BASE_DIR/||" | sed "s|/|.|g" | sed "s|.class||")
    
    # Run EvoSuite for the class
    java -jar evosuite-1.2.0.jar -generateTests -class "$className" -projectCP "$BASE_DIR"
done
