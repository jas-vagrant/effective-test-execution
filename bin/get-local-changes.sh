#!/bin/bash

# Step 1: Get file name changes using git status
file_changes=$(git status --porcelain | awk '$1 == "M" || $1 == "A" {print $2}')

# Step 2: Loop through the changed files
for file in $file_changes; do
    # Check if the file is a source code file in src/main/java/com/example
    if [[ $file == src/main/java/com/example/* ]]; then
        # Step 3: Get the git diff for the file
        diff_output=$(git diff --unified=0 "$file")

        # Step 4: Extract method names containing the changed lines
        while IFS= read -r line; do
            # Check if the line is a modified line
            if [[ $line =~ ^[@ ]+[-+][0-9] ]]; then
                # Extract the line number
                line_number=$(echo "$line" | sed -n 's/[@ ]\{1,\}[-+]\([0-9]\{1,\}\).*/\1/p')

                # Extract the method name containing the line
                method_name=$(sed -n "${line_number},/${line_number}/{/^[[:space:]]*.*(/s/^[[:space:]]*\(.*\) (.*/\1/p}" "$file")

                # Print the method name
                echo "File: $file, Method: $method_name"
            fi
        done <<< "$diff_output"
    fi
done
